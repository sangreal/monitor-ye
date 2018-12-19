package enn.monitor.log.anomaly.detect;

import enn.monitor.streaming.common.proto.Metric;
import enn.monitor.streaming.sink.opentsdb.OpenTSDBSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weize on 18-8-15.
 */
public class Scorer {
    private static Scorer instance;

    public static Scorer getInstance() {
        if (instance == null) {
            synchronized (ModelCaller.class) {
                if (instance == null) {
                    instance = new Scorer();
                }
            }
        }
        return instance;
    }

    public void anomalyScore(String token, long minute, double[] vector, double[] predict) {
        double res = 0.0;
        for (int i = 0; i < vector.length; i ++) {
            res += Math.pow(vector[i] - predict[i], 2);
        }
        res = Math.sqrt(res);

        List<Metric> metrics = new ArrayList<>();
        Metric metric = new Metric();
        metric.setTimestamp(minute);
        metric.setMetric("AnomalyScore");
        Map<String, String> tags = new HashMap<>();
        tags.put("token", token);
        metric.setTags(tags);
        metric.setValue(res);
        metrics.add(metric);

        OpenTSDBSender.getInstance(Detector.parameters.opentsdbUrl).send(metrics);
    }
}
