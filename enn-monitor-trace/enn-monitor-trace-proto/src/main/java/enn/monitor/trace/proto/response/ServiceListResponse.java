package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceSummary;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceListResponse extends Response {
    private ListData<ServiceSummary> data;
    private String status;
    private String message;

    public ServiceListResponse(ListData<ServiceSummary> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceSummary> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
