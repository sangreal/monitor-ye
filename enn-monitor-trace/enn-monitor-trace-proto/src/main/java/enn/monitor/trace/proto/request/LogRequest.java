package enn.monitor.trace.proto.request;


public class LogRequest {
    private String bizLine = null;
    private String service = null;
    private String instance = null;
    private String resource = null;

    private String startDateTime = null;
    private String endDateTime = null;

    private String traceId = null;
    private int    pageNumber = 0;
    private int    requestLogNum = 0;
    private String logKeyword = null;
    private String logLevel = null;

    public String getBizLine() {
        return bizLine;
    }

    public void setBizLine(String bizLine) {
        this.bizLine = bizLine;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRequestLogNum() {
        return requestLogNum;
    }

    public void setRequestLogNum(int requestLogNum) {
        this.requestLogNum = requestLogNum;
    }

    public String getLogKeyword() {
        return logKeyword;
    }

    public void setLogKeyword(String logKeyword) {
        this.logKeyword = logKeyword;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
}
