package enn.monitor.log.anomaly.detect;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by weize on 18-8-10.
 */
public class PredictCache {
    private static final int TIME_STEPS = 10;
    private Map<String, TreeMap<Long, double[]>> predictVectors = new ConcurrentHashMap<>();
    private static PredictCache instance;

    public static PredictCache getInstance() {
        if (instance == null) {
            synchronized (ModelCaller.class) {
                if (instance == null) {
                    instance = new PredictCache();
                }
            }
        }
        return instance;
    }

    public void addPredictVec(String token, long minute, double[] vector) {
        TreeMap<Long, double[]> vecs = predictVectors.get(token);
        vecs.put(minute, vector);
        if (vecs.size() >= TIME_STEPS) {
            if (vecs.size() > TIME_STEPS) {
                vecs.pollLastEntry();
            }
        }
    }

    public double[] getPredictedVec(String token, long minute) {
        return predictVectors.get(token).get(minute);
    }

}
