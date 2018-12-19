package enn.monitor.trace.proto.model.common;

import java.io.Serializable;

/**
 * Created by weize on 18-1-4.
 */
public class Aggregates implements Serializable {
    public long callCount;
    public long errorCount;
    public long avgLatency;

    public Aggregates(long callCount, long errorCount, long avgLatency) {
        this.callCount = callCount;
        this.errorCount = errorCount;
        this.avgLatency = avgLatency;
    }
}
