package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceTopology;

/**
 * Created by weize on 18-2-5.
 */
public class ServiceDependencyResponse extends Response {
    private ListData<ServiceTopology> data;
    private String status;
    private String message;

    public ServiceDependencyResponse(ListData<ServiceTopology> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceTopology> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
