package enn.monitor.trace.proto.model.service;

import com.google.gson.Gson;

/**
 * Created by weize on 18-1-5.
 */
public class ServiceSummary {
    private String bizLine;
    private String serviceName;
    private long requests;

    public ServiceSummary(String bizLine, String serviceName, long requests) {
        this.bizLine = bizLine;
        this.serviceName = serviceName;
        this.requests = requests;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getServiceName() {
        return serviceName;
    }

    public long getRequests() {
        return requests;
    }

    public void setRequests(long requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
