package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceAggregates;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceAggregatesResponse extends Response {
    private ListData<ServiceAggregates> data;
    private String status;
    private String message;

    public ServiceAggregatesResponse(ListData<ServiceAggregates> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceAggregates> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
