package enn.monitor.trace.proto.response;

import com.google.gson.GsonBuilder;
import enn.monitor.trace.proto.model.trace.TraceList;


public class TraceListResponse {
    private TraceList data    = null;
    private String    status  = null;
    private String    message = null;

    public TraceList getData() {
        return data;
    }

    public void setData(TraceList data) {
        this.data = data;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
