package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceResourceAggregates;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceResourceAggregatesResponse extends Response {
    private ListData<ServiceResourceAggregates> data;
    private String status;
    private String message;

    public ServiceResourceAggregatesResponse(ListData<ServiceResourceAggregates> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceResourceAggregates> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
