package enn.monitor.trace.proto.model.trace;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TraceList {
    private long traceNum = 0;
    private List<TraceInfo> tracesInfo = null;

    public long getTraceNum() {
        return traceNum;
    }

    public void setTraceNum(long traceNum) {
        this.traceNum = traceNum;
    }

    public List<TraceInfo> getTracesInfo() {
        return tracesInfo;
    }

    public void setTracesInfo(List<TraceInfo> tracesInfo) {
        this.tracesInfo = tracesInfo;
    }

    public static class TraceInfo implements Comparable<TraceInfo>{
        private String dateTime = null;
        private double duration = 0.0;
        private double traceDuration = 0.0;
        private String traceId = null;
        private int    numOfService = 0;
        private int    numOfSpan = 0;
        private String instance = null;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getTraceDuration() {
            return traceDuration;
        }

        public void setTraceDuration(double duration) {
            this.traceDuration = duration;
        }

        public int getNumOfService() {
            return numOfService;
        }

        public void setNumOfService(int numOfService) {
            this.numOfService = numOfService;
        }

        public int getNumOfSpan() {
            return numOfSpan;
        }

        public void setNumOfSpan(int numOfSpan) {
            this.numOfSpan = numOfSpan;
        }

        public String getInstance() {
            return instance;
        }

        public void setInstance(String instance) {
            this.instance = instance;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        @Override
        public int compareTo(TraceInfo o) {
            return Long.compare(EnnDatetimeUtil.convertStringToLongWithDateTime(o.dateTime, EnnTimezoneUtil.getChinaTimeZone()),
                                EnnDatetimeUtil.convertStringToLongWithDateTime(this.dateTime, EnnTimezoneUtil.getChinaTimeZone()));
        }
    }
}
