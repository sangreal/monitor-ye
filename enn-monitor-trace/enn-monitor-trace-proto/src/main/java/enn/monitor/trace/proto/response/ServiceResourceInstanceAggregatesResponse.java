package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceResourceInstanceAggregates;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceResourceInstanceAggregatesResponse extends Response {
    private ListData<ServiceResourceInstanceAggregates> data;
    private String status;
    private String message;

    public ServiceResourceInstanceAggregatesResponse(
            ListData<ServiceResourceInstanceAggregates> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceResourceInstanceAggregates> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
