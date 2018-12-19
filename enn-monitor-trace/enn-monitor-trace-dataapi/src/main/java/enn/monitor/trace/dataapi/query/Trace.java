package enn.monitor.trace.dataapi.query;

import enn.monitor.trace.dataapi.trace.SearchResultConverter;
import enn.monitor.trace.dataapi.trace.TraceUtil;
import enn.monitor.trace.dataapi.util.EnnMonitorElasticSearchClientUtil;
import enn.monitor.trace.proto.model.trace.TraceSpanList;
import enn.monitor.trace.proto.model.trace.ennzipkin.internal.CorrectForClockSkew;
import enn.monitor.trace.proto.model.trace.ennzipkin.internal.MergeById;
import enn.monitor.trace.proto.model.trace.ennzipkin.internal.V2SpanConverter;
import enn.monitor.trace.proto.model.trace.ennzipkin2.Span;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import java.util.*;

public class Trace {

    public static List<TraceSpanList> getTraces(List<String> traceIds) throws Exception{
        SearchRequest searchRequest = new SearchRequest("zipkin:span-*");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.termsQuery("traceId", traceIds))
                .from(0)
                .size(1000)
                .sort("timestamp", SortOrder.ASC);

        SearchResponse searchResponse = null;
        searchResponse = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient().search(searchRequest.source(sourceBuilder));

        SearchHit[] searchHits = searchResponse.getHits().getHits();

//        System.out.println(searchResponse.toString());

        if (searchHits.length <= 0) {
            return null;
        }

        // convert the search result to ennzipkin2 spans
        List<Span> span2s = SearchResultConverter.convert(searchHits);

        // convert the ennzipkin2 spans to ennzipkin spans
        List<enn.monitor.trace.proto.model.trace.ennzipkin.Span> span1s = V2SpanConverter.toSpans(span2s);

        Map<String, List<enn.monitor.trace.proto.model.trace.ennzipkin.Span>> traceOriMap = new HashMap<>();

        // group the span by traceId
        TraceUtil.groupSpanByTraceId(span1s, traceOriMap);

        List<TraceSpanList> responseList = new ArrayList<>();
        List<enn.monitor.trace.proto.model.trace.ennzipkin.Span> traceSpan = null;

        for (List<enn.monitor.trace.proto.model.trace.ennzipkin.Span> spanList : traceOriMap.values()) {
            try {
                traceSpan = MergeById.apply(spanList);

                traceSpan = CorrectForClockSkew.apply(traceSpan);

                TraceSpanList response = new TraceSpanList();
                traceSpan = TraceUtil.setStartTime(response, traceSpan);
                traceSpan = TraceUtil.parseDepth(traceSpan);
                response.setSpans(traceSpan);
                TraceUtil.setServiceNumOfTrace(response);
                responseList.add(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseList;
    }
}
