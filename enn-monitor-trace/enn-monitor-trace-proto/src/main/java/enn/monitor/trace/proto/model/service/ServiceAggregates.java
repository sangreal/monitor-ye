package enn.monitor.trace.proto.model.service;

import enn.monitor.trace.proto.model.common.Aggregates;

/**
 * Created by weize on 18-1-12.
 */
public class ServiceAggregates {
    private String bizLine;
    private String service;
    private Aggregates aggregates;
    private long minute;

    public ServiceAggregates(String bizLine, String service,
                                     long minute,
                                     Aggregates aggregates) {
        this.bizLine = bizLine;
        this.service = service;
        this.aggregates = aggregates;
        this.minute = minute;
    }

    public String id() {
        return bizLine + "-" + service + "-" + minute;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getService() {
        return service;
    }


    public Aggregates getAggregates() {
        return aggregates;
    }

    public long getMinute() {
        return minute;
    }

    public ServiceAggregates merge(ServiceAggregates srr) {
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
