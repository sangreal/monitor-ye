package enn.monitor.log.anomaly.detect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by weize on 18-8-10.
 */
public class EventsCache {
    private static final int TIME_STEPS = 10;
    Map<String, TreeMap<Long, double[]>> eventVectors = new ConcurrentHashMap<>();
    private String url = "http://localhost:9001/v1/models/ceph_anomaly:predict";

    private static EventsCache instance;
    public static EventsCache getInstance() {
        if (instance == null) {
            synchronized (EventsCache.class) {
                if (instance == null) {
                    instance = new EventsCache();
                }
            }
        }
        return instance;
    }

    public void addEventVec(String token, long minute, double[] vector) {
        TreeMap<Long, double[]> vecs = eventVectors.get(token);
        vecs.put(minute, vector);
        if (vecs.size() >= TIME_STEPS) {
            if (vecs.size() > TIME_STEPS) {
                vecs.pollLastEntry();
            }
            ModelCaller.getInstance().predictAsync(url, token, minute + 60, new ArrayList<>(vecs.values()));
        }
        double[] predicted = PredictCache.getInstance().getPredictedVec(token, minute);
        if (predicted != null) {
            Scorer.getInstance().anomalyScore(token, minute, vector, predicted);
        }
    }
}
