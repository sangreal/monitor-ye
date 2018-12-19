package enn.monitor.trace.proto.model.service;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.trace.proto.util.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by weize on 18-1-11.
 */
public class ServiceResourceHistogram implements Serializable {
    private String bizLine;
    private String service;
    private String resource;
    private TreeMap<Long, Long> bucketCounts;
    private long minute;
    private long timestamp; // in ms
    public static final long DEFAULT_INTERVAL = 5000; // 5ms
    private long interval = DEFAULT_INTERVAL; // in us

    public ServiceResourceHistogram(String bizLine, String serviceName,
                                      String resource, TreeMap<Long, Long> bucketCounts,
                                      long minute) {
        this.bizLine = bizLine;
        this.service = serviceName;
        this.bucketCounts = bucketCounts;
        this.resource = resource;
        this.minute = minute;
    }

    public String getESIndex() {
        return "enn-service-resource-histogram-" + day();
    }

    public String timestampedId() {
        String id = String.format("%s--%s--%s--%d", bizLine, service, resource, timestamp);
        return Utils.getMD5(id);
    }

    public String id() {
        String id = String.format("%s--%s--%s--%d", bizLine, service, resource, minute);
        return Utils.getMD5(id);
    }

    public String idWithOutTime() {
        String id = String.format("%s--%s--%s", bizLine, service, resource);
        return Utils.getMD5(id);
    }

    public void putInBuckets(long response) {
        increment(bucketId(response), 1);
    }

    public void putInBuckets(List<Long> responses) {
        responses.forEach(response -> {
            increment(bucketId(response), 1);
        });
    }

    public long bucketId(long response) {
        return response / interval * interval;
    }
    
    public void setBucketCounts(TreeMap<Long, Long> bucketCounts) {
    	this.bucketCounts = bucketCounts;
    }

    public void increment(long bucketId, long count) {
        bucketCounts.put(bucketId, bucketCounts.getOrDefault(bucketId, 0L) + count);
    }

    public ServiceResourceHistogram merge(ServiceResourceHistogram srh) {
        if (!this.idWithOutTime().equals(srh.idWithOutTime())) {
            return null;
        }

        TreeMap<Long, Long> tm = srh.getBucketCounts();

        for (long key : srh.getBucketCounts().keySet()) {
            bucketCounts.put(key, bucketCounts.getOrDefault(key, 0L) + srh.getBucketCounts().get(key));
        }
        return this;
    }

    public String day() {
        String date = EnnDatetimeUtil.convertLongToStringWithDate(
                minute * 1000,
                "-",
                EnnTimezoneUtil.getChinaTimeZone()
        );
        return date;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getService() {
        return service;
    }

    public TreeMap<Long, Long> getBucketCounts() {
        return bucketCounts;
    }

    public String getResource() {
        return resource;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getMinute() {
        return minute;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
