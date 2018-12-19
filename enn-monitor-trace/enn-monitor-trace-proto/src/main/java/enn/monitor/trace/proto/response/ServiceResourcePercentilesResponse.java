package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceResourcePercentiles;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceResourcePercentilesResponse extends Response {
    private ListData<ServiceResourcePercentiles> data;
    private String status;
    private String message;

    public ServiceResourcePercentilesResponse(
            ListData<ServiceResourcePercentiles> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceResourcePercentiles> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
