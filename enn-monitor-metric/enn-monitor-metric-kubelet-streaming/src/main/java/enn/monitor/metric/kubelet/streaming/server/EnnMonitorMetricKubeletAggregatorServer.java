package enn.monitor.metric.kubelet.streaming.server;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import enn.monitor.framework.metrics.kubelet.proto.ContainerInfo;
import enn.monitor.metric.kubelet.streaming.parameters.Parameters;
import enn.monitor.metric.kubelet.streaming.util.MetricsConvertor;
import enn.monitor.streaming.common.proto.Metric;
import enn.monitor.streaming.sink.opentsdb.OpenTSDBSender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.io.IOException;
import java.util.*;

/**
 * Created by weize on 17-11-7.
 */
public class EnnMonitorMetricKubeletAggregatorServer {
    static Set<String> aggregateKeys;
    static {
        aggregateKeys = new HashSet<>();
        aggregateKeys.add("cpu_total");
        aggregateKeys.add("memory_usage");
        aggregateKeys.add("memory_working_set");
        aggregateKeys.add("memory_cache");
        aggregateKeys.add("memory_rss");
        aggregateKeys.add("memory_swap");
        aggregateKeys.add("diskio_bytes_Total");
        aggregateKeys.add("diskio_bytes_Read");
        aggregateKeys.add("diskio_bytes_Write");
        aggregateKeys.add("network_rcv_bytes");
        aggregateKeys.add("network_snd_bytes");
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Logger.getRootLogger().setLevel(Level.ERROR);
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        // Create a local StreamingContext with two working thread and batch interval of 1 second
        SparkConf conf;
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[32]").setAppName("Monitor-metrics-etl-agg");
        } else {
            conf = new SparkConf().setAppName("Monitor-metrics-etl-agg");
        }

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", parameters.kafkaBootstrap);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", parameters.kafkaGroupId);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);

        Collection<String> topics = Arrays.asList(parameters.metricsTopic);

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(parameters.batchInterval));

        JavaInputDStream<ConsumerRecord<String, String>> kafkaDirectDStream =
                KafkaUtils.createDirectStream(
                        jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );

        Duration windowSize = new Duration(120000L); // 2min
        Duration slidingInterval = new Duration(120000L); // 2min
        kafkaDirectDStream
                .mapToPair(record -> new Tuple2<>(record.key(), record.value()))
                .map(tuple2 -> {
                    Gson gson = new Gson();
                    ContainerInfo info = gson.fromJson(tuple2._2, ContainerInfo.class);
                    List<Metric> metrics = MetricsConvertor.convertor(info, "test-token");
                    return metrics;
                })
                .flatMap(metrics -> metrics.iterator())
                .filter(metric -> metric.getInstance() != null)
                .filter(metric -> aggregateKeys.contains(metric.getMetric()))
                .mapToPair(metric -> new Tuple2<>(metric.unifiedKey(), metric))
                .mapValues((Function<Metric, Tuple2<Metric, Integer>>) metric -> new Tuple2<Metric, Integer>(metric, 1))
                .reduceByKeyAndWindow((Tuple2<Metric, Integer> x, Tuple2<Metric, Integer> y) ->
                        new Tuple2<Metric, Integer>(mergeMetric(x._1, y._1), x._2 + y._2), windowSize, slidingInterval)
                .mapValues((Function<Tuple2<Metric, Integer>, Metric>) tuple -> {
                    tuple._1.setValue(tuple._1.getValue() / tuple._2);
                    return tuple._1;
                })
                .foreachRDD((rdd, time) -> {
                    rdd.values().foreachPartition(metricIterator -> {
                                OpenTSDBSender sender = OpenTSDBSender.getInstance(parameters.opentsdbUrl);
                                List<Metric> metrics = new ArrayList<>();
                                while(metricIterator.hasNext()) {
                                    Metric metric = metricIterator.next();
                                    System.out.println(metric.getTimestamp()
                                            + "-" + metric.getMetric()
                                            + "-" + metric.getInstance() + " : " + metric.getValue());
                                    metric.setMetric(metric.getMetric() + "-2m-avg");
                                    metrics.add(metric);
                                }
                                sender.send(metrics);
                    });
                });

        jssc.start();              // Start the computation
        jssc.awaitTermination();   // Wait for the computation to terminate
    }

    public static Metric mergeMetric(Metric m1, Metric m2) {
        Metric res = new Metric();
        res.setValue(m1.getValue() + m2.getValue());
        res.setMetric(m1.getMetric());
        res.setTags(m1.getTags());
        res.setTimestamp(Long.max(m1.getTimestamp(), m2.getTimestamp()));
        return res;
    }
}
