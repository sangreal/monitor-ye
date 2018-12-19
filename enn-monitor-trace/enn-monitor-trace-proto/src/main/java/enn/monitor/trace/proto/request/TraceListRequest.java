package enn.monitor.trace.proto.request;


public class TraceListRequest {
    private String bizLine         = null;
    private String serviceName     = null;
    private String resource        = null;
    private String startDateTime   = null;
    private String endDateTime     = null;
    private double repoTimeFrom    = -1.0;
    private double repoTimeTo      = -1.0;
    private String sortBy          = null;      // default traceTimeStamp, future support traceTimeStamp, spanDuration and traceDuration
    private int    requestTraceNum = 10;
    private int    pageNumber      = 1;


    public String getBizLine() {
        return bizLine;
    }

    public void setBizLine(String bizLine) {
        this.bizLine = bizLine;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public double getRepoTimeFrom() {
        return repoTimeFrom;
    }

    public void setRepoTimeFrom(double repoTimeFrom) {
        this.repoTimeFrom = repoTimeFrom;
    }

    public double getRepoTimeTo() {
        return repoTimeTo;
    }

    public void setRepoTimeTo(double repoTimeTo) {
        this.repoTimeTo = repoTimeTo;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getRequestTraceNum() {
        return requestTraceNum;
    }

    public void setRequestTraceNum(int requestTraceNum) {
        this.requestTraceNum = requestTraceNum;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
