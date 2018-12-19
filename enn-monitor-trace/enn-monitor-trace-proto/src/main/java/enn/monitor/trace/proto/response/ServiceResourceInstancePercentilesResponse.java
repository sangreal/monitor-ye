package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceResourceInstancePercentiles;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceResourceInstancePercentilesResponse extends Response {
    private ListData<ServiceResourceInstancePercentiles> data;
    private String status;
    private String message;

    public ServiceResourceInstancePercentilesResponse(
            ListData<ServiceResourceInstancePercentiles> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceResourceInstancePercentiles> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
