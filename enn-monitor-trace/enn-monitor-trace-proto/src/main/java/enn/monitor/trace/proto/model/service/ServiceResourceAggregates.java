package enn.monitor.trace.proto.model.service;

import enn.monitor.trace.proto.model.common.Aggregates;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceResourceAggregates {
    private String bizLine;
    private String service;
    private String resource;
    private Aggregates aggregates;
    private long minute;

    public ServiceResourceAggregates(String bizLine, String service, String resource,
                                     long minute,
                                     Aggregates aggregates) {
        this.bizLine = bizLine;
        this.service = service;
        this.resource = resource;
        this.aggregates = aggregates;
        this.minute = minute;
    }

    public String id() {
        return bizLine + "-" + service + "-" + resource + "-" + minute;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getService() {
        return service;
    }

    public String getResource() {
        return resource;
    }

    public Aggregates getAggregates() {
        return aggregates;
    }

    public long getMinute() {
        return minute;
    }

    public ServiceResourceAggregates merge(ServiceResourceAggregates srr) {
        if (!id().equals(srr.id())) {
            return null; // merge failed;
        }
        long nCount = aggregates.callCount + srr.aggregates.callCount;
        long nError = aggregates.errorCount + srr.aggregates.errorCount;
        long nAvg = (aggregates.avgLatency * aggregates.callCount
                + srr.aggregates.avgLatency * srr.aggregates.callCount) / nCount;
        this.aggregates = new Aggregates(nCount, nError, nAvg);
        return this;
    }
}
