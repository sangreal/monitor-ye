package enn.monitor.log.anomaly.detect.proto;

import java.util.List;

/**
 * Created by weize on 18-8-13.
 */
public class LSTMRegPredictResponse {
    private List<double[]> predictions;

    public List<double[]> getPredictions() {
        return predictions;
    }
}
