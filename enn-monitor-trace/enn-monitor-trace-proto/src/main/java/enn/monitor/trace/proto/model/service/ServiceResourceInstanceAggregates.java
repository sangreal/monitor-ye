package enn.monitor.trace.proto.model.service;

import enn.monitor.trace.proto.model.common.Aggregates;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceResourceInstanceAggregates {
    private String bizLine;
    private String service;
    private String resource;
    private String instance;
    private long minute;
    private Aggregates aggregates;

    public ServiceResourceInstanceAggregates(String bizLine, String service,
                                             String resource, String instance,
                                             long minute,
                                             Aggregates aggregates) {
        this.bizLine = bizLine;
        this.service = service;
        this.resource = resource;
        this.instance = instance;
        this.minute = minute;
        this.aggregates = aggregates;
    }

    public String id() {
        return bizLine + "-" + service + "-" + resource + "-" + instance + "-" + minute;
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

    public String getInstance() {
        return instance;
    }

    public long getMinute() {
        return minute;
    }

    public Aggregates getAggregates() {
        return aggregates;
    }

    public ServiceResourceInstanceAggregates merge(ServiceResourceInstanceAggregates srr) {
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
