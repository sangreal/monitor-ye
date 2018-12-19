package enn.monitor.trace.proto.response;

import enn.monitor.trace.proto.model.common.ListData;
import enn.monitor.trace.proto.model.service.ResourceSummary;

/**
 * Created by wst_dreg on 2018-03-22.
 */
public class ResourceSummaryResponse extends Response{
    private ListData<ResourceSummary> data;
    private String status;
    private String message;

    public ResourceSummaryResponse(ListData<ResourceSummary> data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public ListData<ResourceSummary> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
