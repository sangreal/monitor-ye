package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.service.ServiceResourceHistogram;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceResourceHistogramResponse extends Response {
    private ServiceResourceHistogram data;
    private String status;
    private String message;

    public ServiceResourceHistogramResponse(ServiceResourceHistogram data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ServiceResourceHistogram getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
