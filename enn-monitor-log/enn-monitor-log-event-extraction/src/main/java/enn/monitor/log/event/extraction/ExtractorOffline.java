package enn.monitor.log.event.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.ml.feature.RegexTokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import com.beust.jcommander.JCommander;
import com.google.common.io.Resources;

import enn.monitor.log.event.extraction.pattern.Dictionary;
import enn.monitor.log.event.extraction.pattern.Pattern;
import scala.Tuple2;

/**
 * Created by weize on 18-6-27.
 */
public class ExtractorOffline {
    private static float THRES = 0.8f;
    private static Map<String, Dictionary> dicts; // app -> dict

    public static void main(String[] args) throws IOException, InterruptedException {
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
            conf = new SparkConf().setMaster("local[8]").setAppName("Monitor-log-event-extraction-offline");
        } else {
            conf = new SparkConf().setAppName("Monitor-log-event-extraction-offline");
        }
        conf.set("es.nodes", "10.19.140.200");
        conf.set("es.port", "29400");
        conf.set("es.nodes.wan.only", "true");
        conf.set("es.cluster.name","es-log");


        JavaSparkContext jsc = new JavaSparkContext(conf);
        JavaPairRDD<String, Map<String, Object>> esRDD = JavaEsSpark.esRDD(jsc,
                "shanghai-ceph-2018-07-04/log-k8s-pod");
//                "shanghai-monitor-essential-service-2018-06-26/log-k8s-pod");
//                "shanghai-monitor-essential-service-2018-06-25/log-k8s-pod");

        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();
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
        JavaRDD<Row> rowRDD = esRDD
                .filter(stringMapTuple2 -> {
                    String pod = (String) stringMapTuple2._2().get("podName");
                    return pod != null;
//                    return pod != null && pod.contains("monitoring-opentsdb");
                })
                .values()
                .map(esRow -> RowFactory.create(
                        ((String)esRow.get("log")).toLowerCase(),
                        esRow.get("nameSpace"),
                        esRow.get("podName"),
                        esRow.get("dateTime") == null ? ((Date)esRow.get("createTime")).getTime() / 60000 * 60 :
                                ((Date)esRow.get("dateTime")).getTime() / 60000 * 60)
                );

        Dataset<Row> ds = spark.createDataFrame(rowRDD, schema);

        RegexTokenizer tokenizer = new RegexTokenizer()
                .setInputCol("log")
                .setOutputCol("words")
                .setGaps(false)
                .setPattern("(?U)\\b[a-zA-Z][\\w-'_]+\\b");
        ds = tokenizer.transform(ds);
//        ds = ds.drop("log");
        List<StructField> fields2 = new ArrayList<>();
        fields2.add(DataTypes.createStructField("event_type", DataTypes.StringType, true));
        fields2.add(DataTypes.createStructField("timestamp", DataTypes.LongType, true));
        StructType schema2 = DataTypes.createStructType(fields2);
        Dataset<Row> eventDS = ds.map((MapFunction<Row, Row>) row -> {
            List<String> tokens = row.getList(4);
            Map<Pattern, Long> patternFill = new HashMap<>();
            for (String token : tokens) {
                Set<Pattern> hitPatterns = dicts.get("ceph").getPattern(token);
//                Set<Pattern> hitPatterns = dicts.get("opentsdb").getPattern(token);
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
                return RowFactory.create(patternScores.get(0)._1().getEventType(), row.getLong(3));
            } else {
                return RowFactory.create("Unknown", row.getLong(3));
            }
        }, RowEncoder.apply(schema2));
        Dataset<Row> countDS = eventDS.groupBy("event_type", "timestamp").count();
        countDS = countDS
                .groupBy("timestamp")
                .pivot("event_type", new ArrayList<>(dicts.get("ceph").getEventTypes()))
//                .pivot("event_type", new ArrayList<>(dicts.get("opentsdb").getEventTypes()))
                .sum("count")
                .orderBy("timestamp");
        countDS.write()
                .option("header", true)
                .csv("/home/weize/workspace/enn/enn-monitor/enn-monitor-log/enn-monitor-log-event-extraction/data/ceph-2018-07-04");
//                .csv("/home/weize/workspace/enn/enn-monitor/enn-monitor-log/enn-monitor-log-event-extraction/data/opentsdb-2018-06-26");
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
        nsDicts.put("ceph", loadNSPatterns("ceph-patterns.txt"));
        return nsDicts;
    }
}
