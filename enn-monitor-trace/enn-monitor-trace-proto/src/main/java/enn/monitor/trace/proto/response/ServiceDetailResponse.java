package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ServiceDetail;

/**
 * Created by wst_dreg on 2018-03-21.
 */
public class ServiceDetailResponse extends Response{
    private ListData<ServiceDetail> data;
    private String status;
    private String message;

    public ServiceDetailResponse(ListData<ServiceDetail> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ServiceDetail> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
