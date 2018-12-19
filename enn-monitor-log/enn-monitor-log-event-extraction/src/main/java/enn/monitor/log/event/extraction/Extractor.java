package enn.monitor.log.event.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.ml.feature.RegexTokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQueryException;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import com.beust.jcommander.JCommander;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.event.extraction.event.Event;
import enn.monitor.log.event.extraction.pattern.Dictionary;
import enn.monitor.log.event.extraction.pattern.Pattern;

/**
 * Created by weize on 18-6-21.
 */
public class Extractor {

    private static float THRES = 0.8f;
    private static Map<String, Dictionary> dicts;

    public static void main(String[] args) throws IOException, InterruptedException, StreamingQueryException {
        dicts = loadEventPatterns();
        Logger.getRootLogger().setLevel(Level.INFO);
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        SparkConf conf;
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[8]").setAppName("Monitor-log-event-extraction-" + parameters.logTopic);
        } else {
            conf = new SparkConf().setAppName("Monitor-log-event-extraction-" + parameters.logTopic);
        }

        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();
        Dataset<Row> ds = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", parameters.kafka)
                .option("key.deserializer", LongDeserializer.class.toString())
                .option("value.deserializer", StringDeserializer.class.toString())
                .option("group.id", parameters.kafkaGroupId)
                .option("auto.offset.reset", "latest")
                .option("enable.auto.commit", true)
                .option("subscribe", parameters.logTopic)
                .load();
        ds
                .writeStream()
//                .outputMode("complete")
                //console,parquet,memory,foreach 四种
                .option("truncate","false")
                .format("console")
                .start()
                .awaitTermination();
//        ds.show(10, false);
//        ds.show(10, false);
    }

    public static void main2(String[] args) throws IOException, InterruptedException {
        dicts = loadEventPatterns();
        Logger.getRootLogger().setLevel(Level.INFO);
        Parameters parameters = new Parameters();
        JCommander jc = new JCommander(parameters, args);
        if (parameters.help) {
            jc.usage();
            return;
        }

        SparkConf conf;
        if (parameters.env.equals("test")) {
            conf = new SparkConf().setMaster("local[8]").setAppName("Monitor-log-event-extraction-" + parameters.logTopic);
        } else {
            conf = new SparkConf().setAppName("Monitor-log-event-extraction-" + parameters.logTopic);
        }


        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(parameters.batchInterval));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", parameters.kafka);
        kafkaParams.put("key.deserializer", LongDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", parameters.kafkaGroupId);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", true);
        Collection<String> topics = Arrays.asList(parameters.logTopic);

        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();
        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );
        RegexTokenizer tokenizer = new RegexTokenizer()
                .setInputCol("log")
                .setOutputCol("words")
                .setGaps(false)
                .setPattern("(?U)\\b[a-zA-Z][\\w-'_]+\\b");
//                .setPattern("(?u)/?\\b[a-zA-Z_/][\\w/:-]+\\b");
        List<StructField> fields = new ArrayList<>();
        StructField logField = DataTypes.createStructField("log", DataTypes.StringType, true);
        fields.add(logField);
        StructField nsField = DataTypes.createStructField("namespace", DataTypes.StringType, true);
        fields.add(nsField);
        StructField podField = DataTypes.createStructField("pod", DataTypes.StringType, true);
        fields.add(podField);
        StructField tsField = DataTypes.createStructField("timestamp", DataTypes.LongType, true);
        fields.add(tsField);
        StructType schema = DataTypes.createStructType(fields);
        stream
                .mapToPair(record -> new Tuple2<>(record.key(), record.value()))
                .map(tuple2 -> {
                    String tokenAndStr = tuple2._2();
                    int splitIndex = tokenAndStr.indexOf("|");
                    String logJson = tokenAndStr;
                    if (splitIndex != -1) {
                        logJson = tokenAndStr.substring(splitIndex + 1, tokenAndStr.length());
                    }
                    if (logJson == null) {
                        return null;
                    }

                    try {
                        Gson gson = new Gson();
                        ESLog esLog = gson.fromJson(logJson, ESLog.class);
                        if (esLog == null) {
                            return null;
                        }
                        if (esLog.getCreateTime() == 0) {
                            esLog.setCreateTime(Long.parseLong(tuple2._1()));
                        }

                        if (!"cc-dev".equals(esLog.getNameSpace())
                                && !"shared-hdfs".equals(esLog.getNameSpace())
                                && !("monitor-essential-service".equals(esLog.getNameSpace()) && esLog.getPodName() != null && esLog.getPodName().contains("monitoring-opentsdb"))) {
                            return null;
                        }
                        return esLog;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(esLog -> esLog != null && esLog.getLog() != null)
                .foreachRDD(esLogJavaRDD -> {
                    JavaRDD<Row> rowRDD = esLogJavaRDD.map(log -> RowFactory.create(log.getLog().toLowerCase(), log.getNameSpace(), log.getPodName(), log.getCreateTime()));
                    Dataset<Row> ds = spark.createDataFrame(rowRDD, schema);
                    ds = tokenizer.transform(ds);
                    Dataset<Event> eventDS = ds.map((MapFunction<Row, Event>) row -> {
                        String ns = row.getString(1);
                        String key = ns.equals("monitor-essential-service") ? "opentsdb" : ns;

                        List<String> tokens = row.getList(4);
                        Map<Pattern, Long> patternFill = new HashMap<>();
                        for (String token : tokens) {
                            Set<Pattern> hitPatterns = dicts.get(key).getPattern(token);
                            if (hitPatterns == null) {
                                continue;
                            }
                            for (Pattern pattern : hitPatterns) {
                                if (!patternFill.containsKey(pattern)) {
                                    patternFill.put(pattern, 0l);
                                }
                                long bitValue = 1 << pattern.getPos(token);
                                patternFill.put(pattern, patternFill.get(pattern) | bitValue);
                            }
                        }
                        List<Tuple2<Pattern, Float>> patternScores = new ArrayList<>();
                        for (Pattern p : patternFill.keySet()) {
                            long bits = patternFill.get(p);
                            int c = 0;
                            for (; bits > 0; c ++ ) {
                                bits &= (bits - 1);
                            }
                            float score = (float) c / p.getLength();
                            if (score >= THRES) {
                                patternScores.add(new Tuple2<>(p, score));
                            }
                        }
                        Collections.sort(patternScores, (t1, t2) -> {
                            if (t1._2 > t2._2) {
                                return 1;
                            } else if (t1._2() == t2._2()) {
                                return 0;
                            } else {
                                return -1;
                            }
                        });
                        if (patternScores.size() > 0) {
                            Event e = new Event();
                            e.setRawLog(row.getString(0));
                            e.setType(patternScores.get(0)._1().getEventType());
                            e.setToken(key);
                            e.addEntity("namespace", row.getString(1));
                            e.addEntity("pod", row.getString(2));
                            e.setTimestamp(row.getLong(3));
                            return e;
                        } else {
                            return null;
                        }
                    }, Encoders.bean(Event.class));

                    eventDS
                            .filter((FilterFunction<Event>) e -> e != null)
                            .withColumn(
                                    "timestamp",
                                    eventDS.col("timestamp").divide(1000).cast(DataTypes.TimestampType)
                            )
                            .withWatermark("timestamp", "1 minute")
//                            .printSchema();
                            .groupBy(
//                                    functions.window(eventDS.col("timestamp2"), "1 minutes", "1 minutes"),
                                    functions.window(functions.col("timestamp"), "1 minute", "1 minute"),
                                    eventDS.col("type"),
                                    eventDS.col("token")
                            )
                            .count()
                            .show(10, false);
//                            .foreachPartition(iterator -> {
//                                if (iterator == null) return;
//                                while (iterator.hasNext()) {
//                                    Row row = iterator.next();
//
//                                }
//                            });

//                            .foreachPartition(iterator -> {
//                                if (iterator == null) return;
//                                OpenTSDBSender sender = OpenTSDBSender.getInstance(parameters.opentsdbUrl);
//                                Map<String, Metric> metrics = new HashMap<>();
//                                while (iterator.hasNext()) {
//                                    Event e = iterator.next();
//                                    Metric metric = new Metric();
//                                    metric.setMetric("Event");
//                                    e.addEntity("type", e.getType());
//                                    metric.setTags(e.getEntities());
//                                    metric.getTags().put("podname", metric.getTags().get("pod"));
//                                    metric.getTags().put("container_name", metric.getTags().get("pod"));
//                                    metric.setValue(1);
//                                    metric.setTimestamp(e.getTimestamp());
//                                    if (metrics.containsKey(metric.unifiedKey())) {
//                                        metrics.get(metric.unifiedKey()).setValue(metric.getValue() + 1);
//                                    } else {
//                                        metrics.put(metric.unifiedKey(), metric);
//                                    }
//                                }
//                                if (metrics.size() == 0) return;
//                                sender.send(new ArrayList<Metric>(metrics.values()));
//                            });
                });
        jssc.start();
        jssc.awaitTermination();
    }

    private static Dictionary loadNSPatterns(String nsPatternFile) throws IOException {
        Dictionary rtn = new Dictionary();
        Map<String, String> patterns = new HashMap<>();
        InputStream bis = (InputStream) Resources.getResource(nsPatternFile).getContent();
        List<String> lines = IOUtils.readLines(bis);
        for (int i = 1; i < lines.size(); i ++) {
            String[] splits = lines.get(i).split("###");
            if (splits.length == 2) {
                patterns.put(splits[1].toLowerCase(), splits[0]);
            }
        }
        rtn.addPatterns(patterns);
        return rtn;
    }

    private static Map<String, Dictionary> loadEventPatterns() throws IOException {
        Map<String, Dictionary> nsDicts = new HashMap<>();
        nsDicts.put("shared-hdfs", loadNSPatterns("hdfs-patterns.txt"));
        nsDicts.put("cc-dev", loadNSPatterns("cc-dev-patterns.txt"));
        nsDicts.put("opentsdb", loadNSPatterns("opentsdb-patterns.txt"));
        return nsDicts;
    }
}