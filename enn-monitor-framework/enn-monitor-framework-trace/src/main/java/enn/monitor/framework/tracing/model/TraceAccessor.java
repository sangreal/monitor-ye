package enn.monitor.framework.tracing.model;

/**
 * Created by weize on 17-11-14.
 */
public interface TraceAccessor {

    TraceInfo getEnnMonitorTraceInfo();

    void setEnnMonitorTraceInfo(TraceInfo traceInfo);
}
