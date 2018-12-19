package enn.monitor.trace.proto.model.trace;

import com.google.gson.Gson;
import enn.monitor.trace.proto.model.trace.ennzipkin.Span;

import java.util.List;


public class TraceSpanList {

    private Long   timeStamp  = 0L;
    private double duration   = 0.0;
    private int    serviceNum = 0;
    private List<Span> spans = null;

    public TraceSpanList() {}

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(int serviceNum) {
        this.serviceNum = serviceNum;
    }

    public List<Span> getSpans() {
        return spans;
    }

    public void setSpans(List<Span> spans) {
        this.spans = spans;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
