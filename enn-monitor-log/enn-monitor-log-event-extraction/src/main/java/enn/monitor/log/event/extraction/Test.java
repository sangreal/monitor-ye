package enn.monitor.log.event.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import enn.monitor.framework.log.proto.ESLog;
import enn.monitor.log.event.extraction.event.Event;
import org.apache.commons.io.IOUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.ml.feature.RegexTokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import enn.monitor.log.event.extraction.pattern.Pattern;
import scala.Tuple2;

/**
 * Created by weize on 18-6-25.
 */
public class Test {
    private static Map<String, enn.monitor.log.event.extraction.pattern.Dictionary> dicts;

    public static void main(String[] args) throws IOException {
        String logJson = "{\"createTime\":1534731892837,\"clusterName\":\"shanghai\",\"nodeName\":\"10.19.140.4\",\"nameSpace\":\"kube-system\",\"podName\":\"csi-xfshostpath-attacher-1\",\"log\":\"I0820 02:24:52.751302       1 csi_handler.go:346] CSIHandler: processing PV \\\\\\\"jjy2-lids-opentsdbutils-pv\\\\\\\": no deletion timestamp, ignoring\\\\n\",\"token\":\"4\"}";
        Gson gson = new Gson();
        ESLog esLog = gson.fromJson(logJson, ESLog.class);
        System.out.println(esLog.getLog());
    }

    public static void main2(String[] args) throws IOException {
        dicts = loadEventPatterns();
        SparkConf conf;
        conf = new SparkConf().setMaster("local[8]").setAppName("Monitor-log-event-extraction-offline");
        SparkSession spark = SparkSession
                .builder()
                .config(conf)
                .getOrCreate();
        List<StructField> fields = new ArrayList<>();
        StructField logField = DataTypes.createStructField("log", DataTypes.StringType, true);
        fields.add(logField);
        StructType schema = DataTypes.createStructType(fields);

        List<Row> tests = new ArrayList<>();
        tests.add(RowFactory.create("2018-06-16 00:20:38.477235 7f448163e700  0 log_channel(cluster) log [DBG] : scrub ok on 0,1,2,3,4: ScrubResult(keys {auth=100} crc {auth=449952705})\\n"));
        Dataset<Row> ds = spark.createDataFrame(tests, schema);

        RegexTokenizer tokenizer = new RegexTokenizer()
                .setInputCol("log")
                .setOutputCol("words")
                .setGaps(false)
                .setPattern("(?U)\\b[a-zA-Z][\\w-'_]+\\b");
        ds = tokenizer.transform(ds);
//        ds.select("words")
        Dataset<Event> eventDS = ds.map((MapFunction<Row, Event>) row -> {
            List<String> tokens = row.getList(1);
            Map<Pattern, Long> patternFill = new HashMap<>();
            for (String token : tokens) {
                Set<Pattern> hitPatterns = dicts.get("ceph").getPattern(token);
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
                if (score >= 0.8f) {
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
            for (Tuple2<Pattern, Float> tuple2 : patternScores) {
                System.out.println(tuple2._1().getEventType() + ":" + tuple2._2());
            }
            if (patternScores.size() > 0) {
                Event e = new Event();
                e.setRawLog(row.getString(0));
                e.setType(patternScores.get(0)._1().getEventType());
                return e;
            } else {
                return null;
            }
        }, Encoders.bean(Event.class));
        eventDS.show();
    }

    private static enn.monitor.log.event.extraction.pattern.Dictionary loadNSPatterns(String nsPatternFile) throws IOException {
        enn.monitor.log.event.extraction.pattern.Dictionary rtn = new enn.monitor.log.event.extraction.pattern.Dictionary();
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

    private static Map<String, enn.monitor.log.event.extraction.pattern.Dictionary> loadEventPatterns() throws IOException {
        Map<String, enn.monitor.log.event.extraction.pattern.Dictionary> nsDicts = new HashMap<>();
        nsDicts.put("shared-hdfs", loadNSPatterns("hdfs-patterns.txt"));
        nsDicts.put("cc-dev", loadNSPatterns("cc-dev-patterns.txt"));
        nsDicts.put("opentsdb", loadNSPatterns("opentsdb-patterns.txt"));
        nsDicts.put("ceph", loadNSPatterns("ceph-patterns.txt"));
        return nsDicts;
    }
}
