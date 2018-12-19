package enn.monitor.trace.proto.model.service;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weize on 18-1-10.
 */
public class ServicePercentiles implements Serializable {
    private String bizLine;
    private String serviceName;
    private Map<Double, Double> percentiles;
    private long minute; // 开始的分钟

    public ServicePercentiles(String bizLine, String serviceName, Map<Double, Double> percentiles, long minute) {
        this.bizLine = bizLine;
        this.serviceName = serviceName;
        this.percentiles = percentiles;
        this.minute = minute;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Map<Double, Double> getPercentiles() {
        return percentiles;
    }

    public long getMinute() {
        return minute;
    }
}
