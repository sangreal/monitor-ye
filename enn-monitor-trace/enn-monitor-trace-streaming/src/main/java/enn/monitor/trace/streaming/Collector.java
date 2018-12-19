package enn.monitor.trace.streaming;

import com.beust.jcommander.JCommander;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.service.ServiceResourceHistogram;
import enn.monitor.trace.proto.model.service.ServiceResourceInstancePercentiles;
import enn.monitor.trace.proto.model.service.ServiceResourcePercentiles;
import enn.monitor.trace.streaming.dependency.EnnDependencyLinker;
import enn.monitor.trace.streaming.elasticsearch.*;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;
import zipkin.Codec;
import zipkin2.codec.SpanBytesDecoder;
import zipkin2.Span;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by weize on 18-1-2.
 */
public class Collector {
    private static Parameters parameters;
    private static SparkConf conf;

    public static void main(String[] args) throws Exception {
        Logger.getRootLogger().setLevel(Level.ERROR);
        parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        // Create a local StreamingContext with two working thread and batch interval of 1 second
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[8]").setAppName("Monitor-trace-etl");
        } else {
            conf = new SparkConf().setAppName("Monitor-trace-etl");
        }

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", parameters.kafkaBootstrap);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", parameters.kafkaGroupId);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);

        Collection<String> topics = Arrays.asList(parameters.topic);

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(parameters.batchInterval));
//        Map<String, Integer> topicMap = new HashMap<>();
//        topicMap.put(parameters.topic, parameters.kafkaReceiverPerTopic);

        JavaInputDStream<ConsumerRecord<String, String>> kafkaDirectDStream =
                KafkaUtils.createDirectStream(
                        jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

//        JavaPairReceiverInputDStream<String, String> stream =
//                KafkaUtils.createStream(jssc,
//                        parameters.kafkaZk,
//                        parameters.kafkaGroupId, topicMap, StorageLevel.MEMORY_AND_DISK());

        // span提取; 更新index项的缓存
        JavaDStream<Span> spans = kafkaDirectDStream
                .mapToPair(record -> new Tuple2<>(record.key(), record.value()))
                .map(tuple2 -> tuple2._2())
                .map(tokenAndStr -> {
                    int splitIndex = tokenAndStr.indexOf("|");
                    String message = tokenAndStr;
                    String token = "";
                    if (splitIndex != -1) {
                        token = tokenAndStr.substring(0, splitIndex);
                        message = tokenAndStr.substring(splitIndex + 1, tokenAndStr.length());
                    }
                    return new Tuple2<>(token, message);
                })
                .map(tuple -> {
                    byte[] bytes = tuple._2().getBytes();
                    try {
                        if (bytes[0] == '[') {
                            return SpanBytesDecoder.JSON_V2.decodeList(bytes);
                        } else {
                            if (bytes[0] == 12 /* TType.STRUCT */) {
                                return Codec.THRIFT.readSpans(bytes);
                            } else { // historical kafka encoding of single thrift span per message
                                return Collections.singletonList(Codec.THRIFT.readSpan(bytes));
                            }
                        }
                    } catch (RuntimeException e) {
                        return Collections.EMPTY_LIST;
                    }
                })
                .flatMap(list -> list.iterator());

        // 根据traceId归聚span
        JavaPairDStream<String, Iterable<Span>> tracesById = spans
                .mapToPair(s -> new Tuple2<>(s.traceId(), s))
                .groupByKey();

        JavaPairDStream<String, Iterable<Span>> windowedTracesById = spans
                .window(Duration.apply(60 * 1000), Duration.apply(60 * 1000))
                .mapToPair(s -> new Tuple2<>(s.traceId(), s))
                .groupByKey();
//        JavaPairDStream<String, Iterable<Span>> windowedTracesById = tracesById
//                .window(Duration.apply(60 * 1000), Duration.apply(60 * 1000));

        ESPoolFactory factory = new ESPoolFactory(parameters.esTcp, parameters.esClusterName,
                "trace-aggregate",
                100);
        StorageConsumerObjectFactory scFactory = new StorageConsumerObjectFactory(parameters.esRest);

        // 1. 发送trace信息到zipkin服务器；
        sendSpans(tracesById, scFactory);

        // 2. 统计服务依赖及请求数、平均值等聚合值
        calAggregations(windowedTracesById, factory);

//        JavaDStream<EnnDependencyLink> mergedLinksStream = tracesById.map(tuple2 -> {
//                    EnnDependencyLinker linker = new EnnDependencyLinker();
//                    linker.putTrace(tuple2._2().iterator());
//                    List<EnnDependencyLink> links = linker.link();
//                    links = linker.merge(links);
//                    return links;
//                })
//                .reduce((list1, list2) -> {
//                    list1.addAll(list2);
//                    return list1;
//                })
//                .flatMap(list -> list.iterator())
//                .filter(link -> link.avgLatency() > 0);

        JavaDStream<EnnDependencyLink> windowedMergedLinksStream = windowedTracesById.map(tuple2 -> {
            EnnDependencyLinker linker = new EnnDependencyLinker();
            linker.putTrace(tuple2._2().iterator());
            List<EnnDependencyLink> links = linker.link();
            links = linker.merge(links);
            return links;
        })
                .reduce((list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                })
                .flatMap(list -> list.iterator())
                .filter(link -> link.avgLatency() > 0);


        // 3. 服务-resource-instance粒度的分位数聚合
        calPercentilesNew1(windowedMergedLinksStream, factory);

        // 4. 服务-resource粒度的分位数聚合
        calPercentilesNew2(windowedMergedLinksStream, factory);

        // 5. 计算直方图数据
        calHistogram(windowedMergedLinksStream, factory);

        jssc.start();              // Start the computation
        jssc.awaitTermination();   // Wait for the computation to terminate
    }

    private static void sendSpans(JavaPairDStream<String, Iterable<Span>> tracesById, StorageConsumerObjectFactory scFactory) {
        tracesById.foreachRDD(rdd -> {
            rdd.values().foreachPartitionAsync(iterator -> {
                StorageConsumerPool consumerPool = StorageConsumerPool.getInstance(scFactory);
                StorageConsumer consumer = consumerPool.borrowObject();
                while (iterator.hasNext()) {
                    Iterable<Span> spansSharingTraceId = iterator.next();
                    spansSharingTraceId.forEach(span -> {
                        span.tags().put("resource", EnnDependencyLinker.getResourse(span));
                    });
                    consumer.accept(spansSharingTraceId);
                }
                consumerPool.returnObject(consumer);
            });
        });
    }

    private static void calAggregations(JavaPairDStream<String, Iterable<Span>> tracesById, ESPoolFactory factory) {
        tracesById
                .map(tuple2 -> {
                    EnnDependencyLinker linker = new EnnDependencyLinker();
                    linker.putTrace(tuple2._2().iterator());
                    List<EnnDependencyLink> links = linker.link();
                    links = links.stream().filter(link -> link.avgLatency() > 0).collect(Collectors.toList());
                    links = linker.merge(links);
                    return links;
                })
//                .reduce((list1, list2) -> {
//                    EnnDependencyLinker linker = new EnnDependencyLinker();
//                    list1.addAll(list2);
//                    return linker.merge(list1);
//                })
                .flatMap(list -> list.iterator())
                .filter(link -> link.avgLatency() > 0)
                .foreachRDD(rdd -> {
                    rdd.foreachPartitionAsync(ennDependencyLinkIterator -> {
                        List<EnnDependencyLink> list = new ArrayList<>();
                        while (ennDependencyLinkIterator.hasNext()) {
                            EnnDependencyLink link = ennDependencyLinkIterator.next();
                            list.add(link);
                        }
                        EnnDependencyLinker linker = new EnnDependencyLinker();
                        List<EnnDependencyLink> finalList = linker.merge(list);

                        if (finalList.size() > 0) {
                            ESPool pools = ESPool.getInstance(factory);
                            ESSender sender = pools.borrowObject();
                            sender.send(finalList);
                            pools.returnObject(sender);
                        }
                    });
                });
    }

    private static void calPercentilesNew1(JavaDStream<EnnDependencyLink> mergedLinksStream, ESPoolFactory factory) {
        StructType schema1 = DataTypes.createStructType(
                new StructField[] {
                        DataTypes.createStructField("id", DataTypes.StringType, true),
                        DataTypes.createStructField("biz", DataTypes.StringType, true),
                        DataTypes.createStructField("svc", DataTypes.StringType, true),
                        DataTypes.createStructField("res", DataTypes.StringType, true),
                        DataTypes.createStructField("ins", DataTypes.StringType, true),
                        DataTypes.createStructField("avg_latency", DataTypes.LongType, true),
                        DataTypes.createStructField("minute", DataTypes.LongType, true)
                }
        );
        mergedLinksStream
                .mapToPair(link -> new Tuple2<>(link.callee().id(),
                                RowFactory.create(
                                        link.callee().id(),
                                        link.callee().getBizLine(),
                                        link.callee().getServiceName(),
                                        link.callee().getResource(),
                                        link.callee().getInstance(),
                                        link.aggregates().avgLatency,
                                        link.minute()
                                )
                        )
                )
                .groupByKey()
                .foreachRDD((JavaPairRDD<String, Iterable<Row>> rdd) -> {
                    SparkSession spark = JavaSparkSessionSingleton.getInstance(conf);
                    Dataset<Row> dataset1 = spark.createDataFrame(rdd.values().flatMap(iter -> iter.iterator()), schema1);
                    List<Row> idRs = dataset1.select("id").distinct().collectAsList();

                    long minute = TimeStamp.getCurrentTime().getTime() / 1000 / 60 * 60;
                    double[] percentilesPos = new double[]{0, 0.5, 0.75, 0.90, 0.99, 1};
                    for (Row row : idRs) {
                        String id = row.getString(0);
                        ThreadManager.getInstance().runTaskInPool(new Runnable() {
                            @Override
                            public void run() {
                                Dataset<Row> dataset = dataset1.where("id=\"" + id + "\"");
                                Row r = dataset.first();
                                double[] result = dataset.stat().approxQuantile("avg_latency", percentilesPos, 0.1);
                                Map<Double, Double> map = new HashMap<>();
                                for (int i = 0; i < result.length; i++) {
                                    map.put(percentilesPos[i], result[i]);
                                }
                                List<ServiceResourceInstancePercentiles> percentilesList = new ArrayList<>();
                                percentilesList.add(new ServiceResourceInstancePercentiles(r.getString(1),
                                        r.getString(2), r.getString(3), r.getString(4), map, minute - 60));
                                if (percentilesList.size() > 0) {
                                    Logger.getRootLogger().info("sending res-ins percentiles, " + percentilesList.size());
                                    ESPool pools = ESPool.getInstance(factory);
                                    ESSender sender = null;
                                    try {
                                        sender = pools.borrowObject();
                                        sender.send(percentilesList);
                                        pools.returnObject(sender);
                                        Logger.getRootLogger().info("finished sending res-ins percentiles, " + percentilesList.size());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                });
    }

    private static void calPercentilesNew2(JavaDStream<EnnDependencyLink> mergedLinksStream, ESPoolFactory factory) {
        StructType schema2 = DataTypes.createStructType(
                new StructField[] {
                        DataTypes.createStructField("id", DataTypes.StringType, true),
                        DataTypes.createStructField("biz", DataTypes.StringType, true),
                        DataTypes.createStructField("svc", DataTypes.StringType, true),
                        DataTypes.createStructField("res", DataTypes.StringType, true),
                        DataTypes.createStructField("avg_latency", DataTypes.LongType, true),
                        DataTypes.createStructField("minute", DataTypes.LongType, true)
                }
        );
        mergedLinksStream
                .mapToPair(link -> new Tuple2<>(link.callee().getBizLine()
                                + link.callee().getServiceName() + link.callee().getResource(),
                                RowFactory.create(
                                        link.callee().getBizLine()
                                                + link.callee().getServiceName() + link.callee().getResource(),
                                        link.callee().getBizLine(),
                                        link.callee().getServiceName(),
                                        link.callee().getResource(),
                                        link.aggregates().avgLatency,
                                        link.minute()
                                )
                        )
                )
                .groupByKey()
                .foreachRDD(rdd -> {
                    SparkSession spark = JavaSparkSessionSingleton.getInstance(conf);
                    Dataset<Row> dataset1 = spark.createDataFrame(rdd.values().flatMap(iter -> iter.iterator()), schema2);
                    List<Row> idRs = dataset1.select("id").distinct().collectAsList();

                    long minute = TimeStamp.getCurrentTime().getTime() / 1000 / 60 * 60;
                    double[] percentilesPos = new double[]{0, 0.5, 0.75, 0.90, 0.99, 1};
                    for (Row row : idRs) {
                        String id = row.getString(0);
                        ThreadManager.getInstance().runTaskInPool(
                            new Runnable() {
                                @Override
                                public void run() {
                                    Dataset<Row> dataset = dataset1.where("id=\"" + id + "\"");
                                    Row r = dataset.first();
                                    double[] result = dataset.stat().approxQuantile("avg_latency", percentilesPos, 0.1);
                                    Map<Double, Double> map = new HashMap<>();
                                    for (int i = 0; i < result.length; i++) {
                                        map.put(percentilesPos[i], result[i]);
                                    }
                                    List<ServiceResourcePercentiles> percentilesList = new ArrayList<>();
                                    percentilesList.add(new ServiceResourcePercentiles(r.getString(1),
                                            r.getString(2), r.getString(3), map, minute - 60));
                                    if (percentilesList.size() > 0) {
                                        Logger.getRootLogger().info("sending res percentiles, " + percentilesList.size());
                                        ESPool pools = ESPool.getInstance(factory);
                                        ESSender sender = null;
                                        try {
                                            sender = pools.borrowObject();
                                            sender.send(percentilesList);
                                            pools.returnObject(sender);
                                            Logger.getRootLogger().info("finished sending res percentiles, " + percentilesList.size());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                    }
                });
    }

    private static void calHistogram(JavaDStream<EnnDependencyLink> linkStream, ESPoolFactory factory) {
        linkStream
                .map(link -> {
                    ServiceResourceHistogram srh = new ServiceResourceHistogram(
                            link.callee().getBizLine(),
                            link.callee().getServiceName(),
                            link.callee().getResource(),
                            new TreeMap<>(),
                            link.minute()
                            );
                    srh.putInBuckets(link.avgLatency());
                    return srh;
                })
                .mapToPair(srh -> new Tuple2<>(srh.id(), srh))
                .reduceByKey((srh1, srh2) -> srh1.merge(srh2))
                .foreachRDD(rdd -> {
                    rdd.values().foreachPartitionAsync(iter -> {
                        List<ServiceResourceHistogram> histograms = new ArrayList<>();
                        while (iter.hasNext()) {
                            histograms.add(iter.next());
                        }
                        if (histograms.size() > 0) {
                            ESPool pools = ESPool.getInstance(factory);
                            ESSender sender = pools.borrowObject();
                            sender.send(histograms);
                            pools.returnObject(sender);
                        }
                    });
                });
    }

}

class JavaSparkSessionSingleton {
    private static transient SparkSession instance = null;
    public static SparkSession getInstance(SparkConf sparkConf) {
        if (instance == null) {
            instance = SparkSession
                    .builder()
                    .config(sparkConf)
                    .getOrCreate();
        }
        return instance;
    }
}
