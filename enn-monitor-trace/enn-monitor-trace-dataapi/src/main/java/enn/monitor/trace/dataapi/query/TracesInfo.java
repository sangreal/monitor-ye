package enn.monitor.trace.dataapi.query;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.trace.dataapi.util.EnnMonitorElasticSearchClientUtil;
import enn.monitor.trace.proto.model.trace.TraceList;
import enn.monitor.trace.proto.model.trace.ennzipkin.Span;
import enn.monitor.trace.proto.request.TraceListRequest;
import enn.monitor.trace.proto.model.trace.TraceSpanList;
import enn.monitor.trace.proto.response.TraceListResponse;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TracesInfo {
    private void parseTraceId(List<String> traceIdList, SearchHit[] searchHits) {
        for (SearchHit searchHit : searchHits) {
            traceIdList.add(searchHit.getSource().get("traceId").toString());
        }
    }

    private InstanseDuration parseDurationInstance(TraceSpanList trace, String serviceName, String name) {
        double durationMax = 0.0;
        String instance = null;

        if (name != null && name.equals("default"))
            name = "";

        for (Span span : trace.getSpans()) {
            if (span.getResource() != null )
                if (span.getBelongToService().equals(serviceName) && span.getResource().equals(name) && span.getDuration() > durationMax) {
                    durationMax = span.getDuration();
                    instance = span.getBelongToInstance();
                }
        }

        return new InstanseDuration(instance, durationMax);
    }

    private void parseTraceList(TraceList response, List<String> traceIdList, String serviceName, String name) throws Exception {

        List<TraceList.TraceInfo> traces = new ArrayList<>();

        List<TraceSpanList> traceList = new Trace().getTraces(traceIdList);

        for (TraceSpanList trace : traceList) {
            try {
                TraceList.TraceInfo traceInfo = new TraceList.TraceInfo();

                InstanseDuration instanseDuration = parseDurationInstance(trace, serviceName, name);
                traceInfo.setDateTime(EnnDatetimeUtil.convertLongToStringWithDateTime(trace.getTimeStamp() / 1000, EnnTimezoneUtil.getChinaTimeZone()));
                traceInfo.setTraceId(String.valueOf(trace.getSpans().get(0).getTraceId()));
                traceInfo.setNumOfService(trace.getServiceNum());
                traceInfo.setNumOfSpan(trace.getSpans().size());
                traceInfo.setTraceDuration(trace.getDuration());
                traceInfo.setDuration(instanseDuration.getDuration());
                traceInfo.setInstance(instanseDuration.getInstance());

                traces.add(traceInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Collections.sort(traces);
        response.setTracesInfo(traces);
    }

    private SearchRequest generatorSearchRequest(TraceListRequest request) throws Exception {
        List<String> indices = new ArrayList<>();
        long startMills = 0l;
        long endMills = 0l;
        int pageNum = 1;
        int pageSize = 10;
        double durationFrom = 0.0;
        double durationTo   = 0.0;
        SearchRequest searchRequest = null;
        SearchSourceBuilder sourceBuilder = null;

        if (request.getBizLine() == null || request.getServiceName() == null || request.getResource() == null ||
                request.getStartDateTime() == null || request.getEndDateTime() == null) {
            throw new Exception("bizLine or serviceName or endpoint or startDateTime or endDateTime is null");
        }

        request.setStartDateTime(request.getStartDateTime().concat(":00"));
        request.setEndDateTime(request.getEndDateTime().concat(":00"));

        startMills = EnnDatetimeUtil.convertStringToLongWithDateTime(request.getStartDateTime(), EnnTimezoneUtil.getChinaTimeZone());
        endMills = EnnDatetimeUtil.convertStringToLongWithDateTime(request.getEndDateTime(), EnnTimezoneUtil.getChinaTimeZone());

        List<String> dates = EnnDatetimeUtil.getDatesBetween(startMills / 1000, endMills / 1000);

        for (String str : dates) {
            indices.add("zipkin:span-" + str);
        }

        searchRequest = new SearchRequest(indices.toArray(new String[indices.size()]));
        searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));

        if (request.getPageNumber() > 0) {
            pageNum = request.getPageNumber();
        }
        if (request.getRequestTraceNum() > 0) {
            pageSize = request.getRequestTraceNum();
        }

        sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        String resource = request.getResource();

        if (resource.equals("default"))
            boolQueryBuilder.mustNot(QueryBuilders.existsQuery("tags.resource"));
        else
            boolQueryBuilder.must(QueryBuilders.termQuery("tags.resource", request.getResource()));

        if (request.getRepoTimeFrom() > 0 && request.getRepoTimeTo() > request.getRepoTimeFrom()) {
            durationFrom = request.getRepoTimeFrom() * 1000;
            durationTo   = request.getRepoTimeTo() * 1000;

            boolQueryBuilder.must(QueryBuilders.termQuery("localEndpoint.serviceName", request.getServiceName()))
                    .must(QueryBuilders.termQuery("tags.bizLine", request.getBizLine()))
                    .must(QueryBuilders.rangeQuery("duration").from(durationFrom).to(durationTo))
                    .must(QueryBuilders.rangeQuery("timestamp_millis").from(startMills).to(endMills));
        } else {
            boolQueryBuilder.must(QueryBuilders.termQuery("localEndpoint.serviceName", request.getServiceName()))
                    .must(QueryBuilders.termQuery("tags.bizLine", request.getBizLine()))
                    .must(QueryBuilders.rangeQuery("timestamp_millis").from(startMills).to(endMills));
        }

        sourceBuilder.query(boolQueryBuilder);

        sourceBuilder.collapse(new CollapseBuilder("traceId"))
                .from(pageSize*(pageNum-1))
                .size(pageSize)
                .sort("timestamp_millis", SortOrder.DESC);

        searchRequest.source(sourceBuilder);

        return searchRequest;
    }



    public TraceListResponse getTraceList(TraceListRequest request) {

        SearchRequest  searchRequest  = null;
        SearchResponse searchResponse = null;
        SearchHit[]    searchHits     = null;
        TraceList      traceList      = null;
        TraceListResponse response    = new TraceListResponse();

        try{
            searchRequest = generatorSearchRequest(request);

            // search spans meet the condition
            searchResponse = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient().search(searchRequest);

            searchHits = searchResponse.getHits().getHits();

            if (searchHits.length <= 0) {
                response.setData(null);
                response.setStatus("success");
                response.setMessage("There is no match data!");
                return response;
            }

            List<String> traceIdList = new ArrayList<>();

            // parse the traceId which match condition
            parseTraceId(traceIdList, searchHits);

            traceList = new TraceList();
            traceList.setTraceNum(searchResponse.getHits().totalHits);

            // get the trace info according to the traceId
            parseTraceList(traceList, traceIdList, request.getServiceName(), request.getResource());

            if (traceList != null){
                response.setData(traceList);
                response.setStatus("success");
                response.setMessage("OK");
            }
            else{
                response.setData(null);
                response.setStatus("success");
                response.setMessage("There is no match data!");
            }

            return response;
        }catch (Exception e) {
            e.printStackTrace();
            response.setData(null);
            response.setStatus("success");
            response.setMessage(e.getMessage());
            return response;
        }
    }

    private static final class InstanseDuration {
        private String instance = null;
        private double duration = 0.0;

        public InstanseDuration(String instance, double duration) {
            this.instance = instance;
            this.duration = duration;
        }

        public String getInstance() {
            return instance;
        }

        public void setInstance(String instance) {
            this.instance = instance;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }
    }
}
