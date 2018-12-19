package enn.monitor.trace.dataapi.query;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enn.monitor.trace.dataapi.Config;
import enn.monitor.trace.dataapi.util.DateUtils;
import enn.monitor.trace.dataapi.util.EnnMonitorElasticsearchHttpClient;
import enn.monitor.trace.proto.model.service.ServiceTopology;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wst_dreg on 2018-03-20.
 */
public abstract  class ServiceTopologyQuery extends ESQuery{
    private long start;
    private long end;
    private String calleeBiz = null;
    private String callerSvcAgg = null;
    private String calleeSvcAgg = null;
    private long size = 0;

    public ServiceTopologyQuery(long start, long end, String calleeBiz) {
        this.start = start;
        this.end = end;
        this.calleeBiz = calleeBiz;
        this.callerSvcAgg = "callersvc_aggs";
        this.calleeSvcAgg = "calleesvc_aggs";
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
                 .append("{ \"term\": { \"callee_biz\": \"").append(calleeBiz).append("\" } },")
                 .append("{ \"range\": { \"minute\": { \"gte\": ").append(start).append(", \"lt\": ").append(end)
                 .append("} } } ] } },")
                 .append("\"size\": ").append(size);

        return queryBody.toString();
    }

    @Override
    public String buildAggregationBody() {
        StringBuilder aggregationBody = new StringBuilder();

        aggregationBody.append("\"aggs\": { \"").append(callerSvcAgg).append("\": {")
                       .append("\"terms\": { \"field\": \"caller_svc\" },")
                       .append("\"aggs\": { \"").append(calleeSvcAgg).append("\": {")
                       .append("\"terms\": { \"field\": \"callee_svc\" },")
                       .append("\"aggs\": { \"sum_count\": { \"sum\": { \"field\": \"count\"} },")
                       .append("\"sum_latency\": { \"sum\": { \"script\": { \"lang\": \"expression\",")
                       .append("\"inline\": \"doc[\'count\']*doc[\'avg_latency\']\"")
                       .append("} } } } } } } }");

        return aggregationBody.toString();
    }

//    @Override
//    public List<ServiceTopology> parseResponse(String respBody) {
//        List<ServiceTopology> resp = new ArrayList<>();
//
//        JsonElement je = new JsonParser().parse(respBody);
//        JsonObject jo = je.getAsJsonObject();
//        JsonArray callerSvcJA = jo
//                .getAsJsonObject("aggregations")
//                .getAsJsonObject("callersvc_aggs")
//                .getAsJsonArray("buckets");
//
//        callerSvcJA.forEach(crsJA -> {
//            String callerSvc = crsJA.getAsJsonObject().get("key").getAsString();
//            JsonArray calleeSvcJA = crsJA.getAsJsonObject().getAsJsonObject("calleesvc_aggs").getAsJsonArray("buckets");
//            calleeSvcJA.forEach(cesJA -> {
//                String calleeSvc = cesJA.getAsJsonObject().get("key").getAsString();
//                long count = cesJA.getAsJsonObject().getAsJsonObject("sum_count").get("value").getAsLong();
//                resp.add(new ServiceTopology(callerSvc, calleeSvc, count));
//            });
//        });
//
//        return resp;
//    }
}
