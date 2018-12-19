package enn.monitor.trace.proto.model.service;

import java.util.Map;

/**
 * Created by wst_dreg on 2018-03-21.
 */
public class ServiceDetail {
    private String serviceName = null;
    private long count = 0;
    private Map<String, Long> instanceDetailMap = null;

    public ServiceDetail(String serviceName, long count, Map<String, Long> instanceDetailMap) {
        this.serviceName = serviceName;
        this.count = count;
        this.instanceDetailMap = instanceDetailMap;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Map<String, Long> getInstanceDetailMap() {
        return instanceDetailMap;
    }

    public void setInstanceDetailMap(Map<String, Long> instanceDetailMap) {
        this.instanceDetailMap = instanceDetailMap;
    }
}
