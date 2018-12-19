package enn.monitor.trace.proto.model.service;

/**
 * Created by wst_dreg on 2018-03-22.
 */
public class ResourceSummary{
    private String resourceName = null;
    private long requestCount = 0;
    private long requestLatency = 0;

    public ResourceSummary(String resourceName, long requestCount, long requestLatency) {
        this.resourceName = resourceName;
        this.requestCount = requestCount;
        this.requestLatency = requestLatency;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getRequestLatency() {
        return requestLatency;
    }

    public void setRequestLatency(long requestLatency) {
        this.requestLatency = requestLatency;
    }

}
