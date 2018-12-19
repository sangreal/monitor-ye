package enn.monitor.trace.dataapi.query;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;
import enn.monitor.trace.dataapi.util.EnnMonitorElasticSearchClientUtil;
import enn.monitor.trace.proto.model.log.LogList;
import enn.monitor.trace.proto.response.LogResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Log {
    private String timeStampToDate(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+0800'");
        Date date = new Date(new Long(timestamp));

        return simpleDateFormat.format(date);
    }

    private void parseSearchHint(SearchHit searchHit, LogList.LogItem logItem) {

        Map<String,Object> source = searchHit.getSource();

        if (source.containsKey("bizLine") == true) logItem.setBizLine(source.get("bizLine").toString());

        if (source.containsKey("traceId") == true) logItem.setTraceId(source.get("traceId").toString());

        if (source.containsKey("service") == true) logItem.setService(source.get("service").toString());

        if (source.containsKey("instance") == true) logItem.setInstance(source.get("instance").toString());

        if (source.containsKey("resource") == true) logItem.setResource(source.get("resource").toString());

        if (source.containsKey("log") == true) logItem.setLog(source.get("log").toString());

        if (source.containsKey("logLevel") == true) logItem.setLogLevel(source.get("logLevel").toString());

        if (source.containsKey("dateTime") == true) logItem.setDateTime(source.get("dateTime").toString());

        if (source.containsKey("clusterName") == true) logItem.setClusterName(source.get("clusterName").toString());

        if (source.containsKey("nodeName") == true) logItem.setNodeName(source.get("nodeName").toString());

        if (source.containsKey("nameSpace") == true) logItem.setNameSpace(source.get("nameSpace").toString());

        if (source.containsKey("podName") == true) logItem.setPodName(source.get("podName").toString());

        if (source.containsKey("position") == true) logItem.setPosition(source.get("position").toString());

        if (source.containsKey("threadName") == true) logItem.setThreadName(source.get("threadName").toString());
    }

    public LogList esQuery(SearchRequest searchRequest) throws Exception {
        LogList.LogItem logItem = null;
        ArrayList<LogList.LogItem> logItemList = null;
        SearchHit[] searchHits = null;
        SearchResponse response = null;

        response = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient().search(searchRequest);

        searchHits = response.getHits().getHits();

        if (searchHits.length <= 0) {
            return null;
        }

        logItemList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            logItem = new LogList.LogItem();
            logItemList.add(logItem);

            parseSearchHint(searchHit, logItem);
        }

        LogList log = new LogList();

        log.setLogNum(response.getHits().totalHits);
        log.setLogInfo(logItemList);
        return log;
    }

    public LogResponse getLogList(HttpServletRequest httpRequest) {
        SearchRequest       searchRequest = null;
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<String> indices = new ArrayList<>();
        LogResponse response = new LogResponse();

        try{
            if (httpRequest.getParameter("traceId") != null && httpRequest.getParameter("traceId").length() > 0
                                                              && !httpRequest.getParameter("traceId").equals("null")) {
                boolQueryBuilder.must(QueryBuilders.termQuery("traceId", httpRequest.getParameter("traceId")));
            }

            if (httpRequest.getParameter("startDateTime") != null && httpRequest.getParameter("startDateTime").length() > 0 &&
                httpRequest.getParameter("endDateTime") != null && httpRequest.getParameter("endDateTime").length() > 0 &&
                !httpRequest.getParameter("startDateTime").equals("null") && !httpRequest.getParameter("endDateTime").equals("null")) {

                String startDateTime = httpRequest.getParameter("startDateTime").concat(":00");
                String endDateTime = httpRequest.getParameter("endDateTime").concat(":00");

                long startMills = EnnDatetimeUtil.convertStringToLongWithDateTime(startDateTime, EnnTimezoneUtil.getChinaTimeZone());
                long endMills   = EnnDatetimeUtil.convertStringToLongWithDateTime(endDateTime, EnnTimezoneUtil.getChinaTimeZone());

                List<String> dates = EnnDatetimeUtil.getDatesBetween(startMills / 1000, endMills / 1000);

                for (String str : dates) {
                    indices.add("enn-trace-log-" + str);
                }

                searchRequest = new SearchRequest(indices.toArray(new String[indices.size()]));
                searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));

                boolQueryBuilder.must(QueryBuilders.rangeQuery("dateTime").from(timeStampToDate(startMills))
                                                                                .to(timeStampToDate(endMills)));
            } else {
                searchRequest = new SearchRequest("enn-trace-log-*");
            }

            if (httpRequest.getParameter("bizLine") != null && httpRequest.getParameter("bizLine").length() > 0
                                                              && !httpRequest.getParameter("bizLine").equals("null")) {
                boolQueryBuilder.must(QueryBuilders.termQuery("bizLine", httpRequest.getParameter("bizLine")));
            }

            if (httpRequest.getParameter("serviceName") != null && httpRequest.getParameter("serviceName").length() > 0
                                                                  && !httpRequest.getParameter("serviceName").equals("null")) {
                boolQueryBuilder.must(QueryBuilders.termQuery("service", httpRequest.getParameter("serviceName")));
            }

            if (httpRequest.getParameter("instance") != null && httpRequest.getParameter("instance").length() > 0
                                                               && !httpRequest.getParameter("instance").equals("null")) {
                String a = httpRequest.getParameter("instance");
                boolQueryBuilder.must(QueryBuilders.termQuery("instance", httpRequest.getParameter("instance")));
            }

            if (httpRequest.getParameter("resource") != null  && httpRequest.getParameter("resource").length() > 0
                                                                && !httpRequest.getParameter("resource").equals("null")) {
                boolQueryBuilder.must(QueryBuilders.termQuery("resource", httpRequest.getParameter("resource")));
            }

            if (httpRequest.getParameter("logKeyword") != null && httpRequest.getParameter("logKeyword").length() > 0
                                                                 && !httpRequest.getParameter("logKeyword").equals("null")) {
                boolQueryBuilder.must(QueryBuilders.matchQuery("log", httpRequest.getParameter("logKeyword")));
            }

            if (httpRequest.getParameter("logLevel") != null && !httpRequest.getParameter("logLevel").equals("*") &&
                httpRequest.getParameter("logLevel").length() > 0 && !httpRequest.getParameter("logLevel").equals("null")) {
                String logLevel = httpRequest.getParameter("logLevel");
                boolQueryBuilder.must(QueryBuilders.termsQuery("logLevel", logLevel, logLevel.toLowerCase()));
            }

            int pageNum = Integer.parseInt(httpRequest.getParameter("pageNumber"));
            int pageSize = Integer.parseInt(httpRequest.getParameter("requestLogNum"));

            pageNum = pageNum > 0 ? pageNum : 1;
            pageSize = pageSize > 0? pageSize : 20;

            sourceBuilder.query(boolQueryBuilder);
            sourceBuilder.from(pageSize*(pageNum - 1))
                         .size(pageSize)
                         .sort("dateTime", SortOrder.DESC);
            searchRequest = searchRequest.source(sourceBuilder);

            LogList log = esQuery(searchRequest);

            if (log != null){
                response.setData(log);
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
}
