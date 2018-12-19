package enn.monitor.trace.proto.model.service;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.trace.proto.util.Utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by weize on 18-1-11.
 */
public class ServiceResourceInstancePercentiles implements Serializable {
    private String bizLine;
    private String service;
    private String instance;
    private String resource;
    private Map<Double, Double> percentiles;
    private long minute;

    public ServiceResourceInstancePercentiles(String bizLine, String serviceName,
                                              String resource, String instance,
                                              Map<Double, Double> percentiles,
                                              long minute) {
        this.bizLine = bizLine;
        this.service = serviceName;
        this.percentiles = percentiles;
        this.resource = resource;
        this.instance = instance;
        this.minute = minute;
    }

    public String getESIndex() {
        return "enn-service-resource-instance-percentiles-" + day();
    }

    public String id() {
        String id = String.format("%s--%s--%s--%s--%d", bizLine, service, resource, instance, minute);
        return Utils.getMD5(id);
    }

    public String day() {
        String date = EnnDatetimeUtil.convertLongToStringWithDate(minute * 1000, "-", EnnTimezoneUtil.getChinaTimeZone());
        return date;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getService() {
        return service;
    }

    public Map<Double, Double> getPercentiles() {
        return percentiles;
    }

    public String getInstance() {
        return instance;
    }

    public long getMinute() {
        return minute;
    }

    public String getResource() {
        return resource;
    }
}
