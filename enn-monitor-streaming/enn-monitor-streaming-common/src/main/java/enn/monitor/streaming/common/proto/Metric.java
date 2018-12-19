package enn.monitor.streaming.common.proto;

import enn.monitor.framework.common.security.MD5Util;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weize on 18-6-4.
 */
public class Metric implements Serializable {
    private String metric; // name of the metric
    private double value;
    private long timestamp;
    private Map<String, String> tags;

    public String getMetric() {
        return metric;
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return tags.get("container_name").equals("/") ? "node" : "pod";
    }

    public String getInstance() {
        return getType().equals("node") ? tags.get("host") : tags.get("podname");
    }

    public String unifiedKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(metric);
        sb.append(':');
        tags.entrySet().forEach(entry -> {
            sb.append(entry.getKey());
            sb.append(':');
            sb.append(entry.getValue());
        });
        sb.append(getInstance());
        return MD5Util.getMD5(sb.toString());
    }
}
