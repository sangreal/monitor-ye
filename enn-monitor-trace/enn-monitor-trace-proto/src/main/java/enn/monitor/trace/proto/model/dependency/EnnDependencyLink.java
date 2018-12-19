package enn.monitor.trace.proto.model.dependency;

import com.google.gson.Gson;
import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.trace.proto.model.common.Aggregates;
import enn.monitor.trace.proto.util.Utils;

import java.io.Serializable;

/**
 * Created by weize on 18-1-3.
 */
public class EnnDependencyLink implements Serializable {
    private static final long serialVersionUID = 0L;
    private EnnEndpoint caller;
    private EnnEndpoint callee;
//    private long callCount;
//    private long errorCount;
//    private long avgLatency;
    private Aggregates aggregates;
    private long minute;
    private long timestamp;

//    private long startTime; // 单位：微秒
//    private long endTime;   // 单位：微秒

    /** parent service name (caller) */
    public EnnEndpoint caller() {
        return caller;
    }

    /** child service name (callee) */
    public EnnEndpoint callee() {
        return callee;
    }

    /** total traced calls made from {@link #caller} to {@link #callee} */
    public long callCount() {
        return aggregates.callCount;
    }

    /** How many {@link #callCount calls} are known to be errors */
    public long errorCount() {
        return aggregates.errorCount;
    }

    public long avgLatency() {
        return aggregates.avgLatency;
    }

    public Aggregates aggregates() {
        return aggregates;
    }

    /**
     * @return timestamp(seconds) / 60
     */
    public long minute() {
        return minute;
    }

    public String day() {
        String date = EnnDatetimeUtil.convertLongToStringWithDate(minute * 1000, "-", EnnTimezoneUtil.getChinaTimeZone());
        return date;
    }

    public String timestampedId() {
        return Utils.getMD5(caller().id() + "--" + callee().id() + "--" + timestamp);
    }

    public String id() {
        return Utils.getMD5(caller().id() + "--" + callee().id() + "--" + minute());
    }

    public EnnDependencyLink timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public long timestamp() {
        return timestamp;
    }

    @Override public String toString() {
        return new Gson().toJson(this);
    }

    EnnDependencyLink(EnnEndpoint caller, EnnEndpoint callee, Aggregates aggregates, long minute) {
        this.caller = caller;
        this.callee = callee;
        this.aggregates = aggregates;
        this.minute = minute;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private EnnEndpoint caller;
        private EnnEndpoint callee;
        private Aggregates aggregates;
        private long minute;

        public Builder() {
        }

        public Builder caller(EnnEndpoint caller) {
            this.caller = caller;
            return this;
        }

        public Builder callee(EnnEndpoint callee) {
            this.callee = callee;
            return this;
        }

        public Builder aggregates(Aggregates aggregates) {
            this.aggregates = aggregates;
            return this;
        }

        public Builder minute(long minute) {
            this.minute = minute;
            return this;
        }

        public EnnDependencyLink build() {
            return new EnnDependencyLink(caller, callee, aggregates, minute);
        }
    }


}


