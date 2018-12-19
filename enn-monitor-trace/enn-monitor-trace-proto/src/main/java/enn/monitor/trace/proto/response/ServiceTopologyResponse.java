package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceTopology;

/**
 * Created by wst_dreg on 2018-03-20.
 */
public class ServiceTopologyResponse extends Response{
    private ListData<ServiceTopology> data;
    private String status;
    private String message;

    public ServiceTopologyResponse(ListData<ServiceTopology> data, String status, String message) {
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
