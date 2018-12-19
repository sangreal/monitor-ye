package enn.monitor.trace.proto.model.log;

import java.util.List;

public class LogList {
    private long logNum = 0;
    private List<LogItem> logInfo = null;

    public long getLogNum() {
        return logNum;
    }

    public void setLogNum(long logNum) {
        this.logNum = logNum;
    }

    public List<LogItem> getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(List<LogItem> logInfo) {
        this.logInfo = logInfo;
    }

    public static class LogItem {
        private String bizLine = null;
        private String traceId  = null;
        private String dateTime = null;
        private String service  = null;
        private String instance = null;
        private String resource = null;
        private String log      = null;
        private String logLevel = null;
        private String clusterName = null;
        private String nodeName = null;
        private String nameSpace = null;
        private String podName = null;
        private String position = null;
        private String threadName = null;

        public  LogItem() {}

        public String getBizLine() {
            return bizLine;
        }

        public void setBizLine(String bizLine) {
            this.bizLine = bizLine;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
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

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public String getLogLevel() {
            return logLevel;
        }

        public void setLogLevel(String logLevel) {
            this.logLevel = logLevel;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getNameSpace() {
            return nameSpace;
        }

        public void setNameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
        }

        public String getPodName() {
            return podName;
        }

        public void setPodName(String podName) {
            this.podName = podName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }
    }
}
