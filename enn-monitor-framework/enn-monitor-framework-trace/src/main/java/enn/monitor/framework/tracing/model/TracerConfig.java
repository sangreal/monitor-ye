package enn.monitor.framework.tracing.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by weize on 17-12-14.
 */
public class TracerConfig implements Serializable {
    private String switchOn;
    private String reportServer;
    private String bizLine;
    private String serviceName;
    private String token;
    private String instance;

    public TracerConfig(String switchOn, String reportServer, String bizLine,
                        String serviceName, String token) {
        this.switchOn = switchOn;
        this.reportServer = reportServer;
        this.bizLine = bizLine;
        this.serviceName = serviceName;
        this.token = token;
    }

    public String getSwitchOn() {
        return switchOn;
    }

    public void setSwitchOn(String switchOn) {
        this.switchOn = switchOn;
    }

    public String getReportServer() {
        return reportServer;
    }

    public void setReportServer(String reportServer) {
        this.reportServer = reportServer;
    }

    public String getBizLine() {
        return bizLine;
    }

    public void setBizLine(String bizLine) {
        this.bizLine = bizLine;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
