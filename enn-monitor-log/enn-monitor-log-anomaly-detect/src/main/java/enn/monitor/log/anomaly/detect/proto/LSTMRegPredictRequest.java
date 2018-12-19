package enn.monitor.log.anomaly.detect.proto;

import java.util.List;

/**
 * Created by weize on 18-8-13.
 */
public class LSTMRegPredictRequest {
    private List<List<double[]>> instances;

    public LSTMRegPredictRequest(List<List<double[]>> instances) {
        this.instances = instances;
    }
}
