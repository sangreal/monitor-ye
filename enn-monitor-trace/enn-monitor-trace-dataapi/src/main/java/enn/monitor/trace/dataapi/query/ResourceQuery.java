package enn.monitor.trace.dataapi.query;

import com.google.common.base.Strings;
import enn.monitor.trace.dataapi.Config;
import enn.monitor.trace.dataapi.util.DateUtils;
import java.util.List;

/**
 * Created by wst_dreg on 2018-03-22.
 */

public class ResourceQuery extends ESQuery{
    private long start = 0;
    private long end = 0;
    private String callerBiz = null;
    private String calleeBiz = null;
    private String callerSvc = null;
    private String calleeSvc = null;
    private String calleeRes = null;
    private String calleeResAgg = null;
    private int size = 0;

    public ResourceQuery(long start, long end, String callerBiz, String calleeBiz, String callerSvc, String calleeSvc, String calleeRes) {
        this.start = start;
        this.end = end;
        this.callerBiz = callerBiz;
        this.calleeBiz = calleeBiz;
        this.callerSvc = callerSvc;
        this.calleeSvc = calleeSvc;
        this.calleeRes = calleeRes;
        this.calleeResAgg = "calleeres_aggs";
    }

    @Override
    public String buildURL() {
        StringBuilder url = new StringBuilder();

        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indices = indexSB.substring(0, indexSB.length() - 1);

        url.append(Config.esHttpUrl).append(indices).append("/_search?ignore_unavailable=true&allow_no_indices=true");

        return url.toString();
    }

    @Override
    public String buildQueryBody() {
        StringBuilder queryBody = new StringBuilder();

        queryBody.append("\"query\": { \"bool\":{ \"must\": [")
                 .append("{ \"range\": { \"minute\": { \"gte\": ").append(start).append(", \"lt\": ").append(end).append("} } }");

        if (!Strings.isNullOrEmpty(this.callerBiz))
            queryBody.append(",{ \"term\": { \"caller_biz\": \"").append(callerBiz).append("\" } }");

        if (!Strings.isNullOrEmpty(this.calleeBiz))
            queryBody.append(",{ \"term\": { \"callee_biz\": \"").append(calleeBiz).append("\" } }");

        if (!Strings.isNullOrEmpty(this.callerSvc))
            queryBody.append(",{ \"term\": { \"caller_svc\": \"").append(callerSvc).append("\" } }");

        if (!Strings.isNullOrEmpty(this.calleeSvc))
            queryBody.append(",{ \"term\": { \"callee_svc\": \"").append(calleeSvc).append("\" } }");

        if (!Strings.isNullOrEmpty(this.calleeRes))
            queryBody.append(",{ \"wildcard\": { \"callee_res\": \"").append(calleeRes).append("*\" } }");

        queryBody.append("] } },")
                 .append("\"size\": ").append(size);

        return queryBody.toString();
    }

    @Override
    public String buildAggregationBody() {
        StringBuilder aggregationBody = new StringBuilder();

        aggregationBody.append("\"aggs\": { \"").append(calleeResAgg).append("\": {")
                .append("\"terms\": { \"field\": \"callee_res\" },")
                .append("\"aggs\": { \"total_count\": { \"sum\": { \"field\": \"count\"} },")
                .append("\"total_latency\": { \"sum\": { \"script\": { \"lang\": \"expression\",")
                .append("\"inline\": \"doc[\'count\']*doc[\'avg_latency\']\"")
                .append("} } } } } }");

        return aggregationBody.toString();
    }

}
