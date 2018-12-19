package enn.monitor.trace.dataapi.query;


import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import enn.monitor.trace.dataapi.Config;
import enn.monitor.trace.dataapi.util.DateUtils;
import enn.monitor.trace.dataapi.util.EnnMonitorElasticSearchClientUtil;
import enn.monitor.trace.proto.model.common.Aggregates;
import enn.monitor.trace.proto.model.dependency.EnnDependencyLink;
import enn.monitor.trace.proto.model.dependency.Pair;
import enn.monitor.trace.proto.model.service.*;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;

import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Created by weize on 18-1-5.
 */
public class Service {
    public static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    public static List<ServiceSummary> getSummaries(long start, long end, String bizLine, String serviceName) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);
        StringBuilder whereSB = new StringBuilder("minute >= " + start + " and minute < " + end);
        if (!Strings.isNullOrEmpty(bizLine)) {
            whereSB.append(" and callee_biz = '" + bizLine + "'");
        }
        if (!Strings.isNullOrEmpty(serviceName)) {
            whereSB.append(" and callee_svc = '" + serviceName + "'");
        }
        String sql = "select /*! IGNORE_UNAVAILABLE */ callee_biz, callee_svc, sum(count) as all_counts" +
                " from " + indexes +
                " where " + whereSB +
                " group by callee_biz, callee_svc" +
                " limit 10000";
        return getSummariesBySql(sql);
    }

    public static List<ServiceSummary> getSummariesFuzzily(long start, long end, String fuzzy) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);
        StringBuilder whereSB = new StringBuilder("minute >= " + start + " and minute < " + end);
        if (fuzzy != null && !fuzzy.isEmpty()) {
            whereSB.append(" and (callee_biz like '" + fuzzy + "%' or callee_svc like '" + fuzzy + "%')");
        }
        String sql = "select /*! IGNORE_UNAVAILABLE */ callee_biz, callee_svc, sum(count) as all_counts" +
                " from " + indexes +
                " where " + whereSB +
                " group by callee_biz, callee_svc" +
                " limit 10000";
        return getSummariesBySql(sql);
    }

    protected static List<ServiceSummary> getSummariesBySql(String sql) {
        try {
            Request request = new Request.Builder()
                    .url(Config.esSqlUrl + URLEncoder.encode(sql, "utf-8"))
                    .get()
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            String res = response.body().string();

            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(res);
            JsonObject jo = je.getAsJsonObject();
            Map<String, ServiceSummary> serviceSummaries = new HashMap<>();
            JsonArray bizLines = jo
                    .getAsJsonObject("aggregations")
                    .getAsJsonObject("callee_biz")
                    .getAsJsonArray("buckets");
            bizLines.forEach(bl -> {
                String bizLine = bl.getAsJsonObject().get("key").getAsString();
                JsonArray services = bl.getAsJsonObject().getAsJsonObject("callee_svc").getAsJsonArray("buckets");
                services.forEach(sv -> {
                    String service = sv.getAsJsonObject().get("key").getAsString();
                    String uniKey = bizLine + "-" + service;
                    long requests = sv.getAsJsonObject().getAsJsonObject("all_counts").get("value").getAsLong();
                    ServiceSummary ss = serviceSummaries.get(uniKey);
                    if (ss == null) {
                        ss = new ServiceSummary(bizLine, service, requests);
                    } else {
                        ss.setRequests(ss.getRequests() + requests);
                    }
                    serviceSummaries.put(uniKey, ss);
                });
            });

            return new ArrayList<>(serviceSummaries.values());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public static List<ServiceTopology> getDependencies(long start, long end, String calleeBiz) {
        List<ServiceTopology> res = new ArrayList<>();
        try {
            List<String> dates = DateUtils.getDatesBetween(start, end);
            StringBuilder indexSB = new StringBuilder();
            dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
            String indices = indexSB.substring(0, indexSB.length() - 1);

            StringBuilder whereSB = new StringBuilder("minute >= " + start + " and minute < " + end);
            whereSB.append(" and callee_biz "  + "='" + calleeBiz + "'");

            String sql = "select /*! IGNORE_UNAVAILABLE */ caller_svc, callee_svc, sum(count) as all_counts" +
                    " from " + indices +
                    " where " + whereSB +
                    " group by caller_svc, callee_svc" +
                    " limit 10000";
            Request request = new Request.Builder()
                    .url(Config.esSqlUrl + URLEncoder.encode(sql, "utf-8"))
                    .get()
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            String resp = response.body().string();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(resp);
            JsonObject jo = je.getAsJsonObject();
            JsonArray callerSvcJA = jo
                    .getAsJsonObject("aggregations")
                    .getAsJsonObject("caller_svc")
                    .getAsJsonArray("buckets");

            callerSvcJA.forEach(crsJA -> {
            String callerSvc = crsJA.getAsJsonObject().get("key").getAsString();
            JsonArray calleeSvcJA = crsJA.getAsJsonObject().getAsJsonObject("callee_svc").getAsJsonArray("buckets");
            calleeSvcJA.forEach(cesJA -> {
                String calleeSvc = cesJA.getAsJsonObject().get("key").getAsString();
                long count = cesJA.getAsJsonObject().getAsJsonObject("all_counts").get("value").getAsLong();
                res.add(new ServiceTopology(callerSvc, calleeSvc, count));
            });
        });

        return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ServiceDetail> getServiceDetail(long start, long end, String calleeBiz, String calleeSvc) {
        List<ServiceDetail> res = new ArrayList<>();
        try {
            List<String> dates = DateUtils.getDatesBetween(start, end);
            StringBuilder indexSB = new StringBuilder();
            dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
            String indices = indexSB.substring(0, indexSB.length() - 1);

            StringBuilder whereSB = new StringBuilder("minute >= " + start + " and minute < " + end);
            whereSB.append(" and callee_biz "  + "='" + calleeBiz + "'");
            whereSB.append(" and callee_svc "  + "='" + calleeSvc + "'");

            String sql = "select /*! IGNORE_UNAVAILABLE */ caller_svc, callee_svc, sum(count) as all_counts" +
                    " from " + indices +
                    " where " + whereSB +
                    " group by callee_svc, callee_ins" +
                    " limit 10000";
            Request request = new Request.Builder()
                    .url(Config.esSqlUrl + URLEncoder.encode(sql, "utf-8"))
                    .get()
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            String resp = response.body().string();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(resp);
            JsonObject jo = je.getAsJsonObject();
            JsonArray callerSvcJA = jo
                    .getAsJsonObject("aggregations")
                    .getAsJsonObject("callee_svc")
                    .getAsJsonArray("buckets");

            callerSvcJA.forEach(crsJA -> {
                String serviceName = crsJA.getAsJsonObject().get("key").getAsString();
                JsonArray calleeSvcJA = crsJA.getAsJsonObject().getAsJsonObject("callee_ins").getAsJsonArray("buckets");
                Map<String, Long> instanceDetailMap = new HashMap<>();
                calleeSvcJA.forEach(cesJA -> {
                    String calleeIns = cesJA.getAsJsonObject().get("key").getAsString();
                    long calleeInsCount = cesJA.getAsJsonObject().getAsJsonObject("all_counts").get("value").getAsLong();
                    instanceDetailMap.put(calleeIns, calleeInsCount);
                });

                long count = 0;

                for (String key : instanceDetailMap.keySet())
                    count += instanceDetailMap.get(key);
                res.add(new ServiceDetail(serviceName, count, instanceDetailMap));

            });

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ServiceAggregates> getServiceAggregates(long start, long end,
                                                                               String bizLine, String service) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("callee_biz", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("callee_svc", service));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                                                                          .to(end-1));

            searchSourceBuilder.size(5000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            Map<String, ServiceAggregates> requests = new HashMap<>();
            searchHitList.forEach(record -> {
                Map<String,Object> source = record.getSource();
                long count = Long.valueOf(source.get("count").toString());
                long error = Long.valueOf(source.get("error").toString());
                long avg_latency = Long.valueOf(source.get("avg_latency").toString());
                long minute = Long.valueOf(source.get("minute").toString());
                Aggregates aggregates = new Aggregates(count, error, avg_latency);

                int granularity = parseGranularityInterval(start, end);
                minute = start + (minute - start)/(granularity*60)*granularity*60;

                ServiceAggregates srr = new ServiceAggregates(bizLine, service, minute, aggregates);
                if (requests.containsKey(srr.id())) {
                    requests.put(srr.id(), requests.get(srr.id()).merge(srr));
                } else {
                    requests.put(srr.id(), srr);
                }
            });

            List<ServiceAggregates> res = new ArrayList<>(requests.values());
            Collections.sort(res, new Comparator<ServiceAggregates>() {
                @Override
                public int compare(ServiceAggregates o1, ServiceAggregates o2) {
                    return Long.compare(o1.getMinute(), o2.getMinute());
                }
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ServiceResourceAggregates> getServiceResourceAggregates(long start, long end,
                                                                               String bizLine, String service,
                                                                               String resource) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("callee_biz", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("callee_svc", service));
            boolQueryBuilder.must(QueryBuilders.termQuery("callee_res", resource));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                                                                          .to(end-1));

            searchSourceBuilder.size(5000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            Map<String, ServiceResourceAggregates> requests = new HashMap<>();
            searchHitList.forEach(record -> {
                Map<String,Object> source = record.getSource();
                long count = Long.valueOf(source.get("count").toString());
                long error = Long.valueOf(source.get("error").toString());
                long avg_latency = Long.valueOf(source.get("avg_latency").toString());
                long minute = Long.valueOf(source.get("minute").toString());
                Aggregates aggregates = new Aggregates(count, error, avg_latency);

                int granularity = parseGranularityInterval(start, end);
                minute = start + (minute - start)/(granularity*60)*granularity*60;

                ServiceResourceAggregates srr = new ServiceResourceAggregates(bizLine, service, resource,
                        minute, aggregates);
                if (requests.containsKey(srr.id())) {
                    requests.put(srr.id(), requests.get(srr.id()).merge(srr));
                } else {
                    requests.put(srr.id(), srr);
                }
            });
            List<ServiceResourceAggregates> res = new ArrayList<>(requests.values());
            Collections.sort(res, new Comparator<ServiceResourceAggregates>() {
                @Override
                public int compare(ServiceResourceAggregates o1, ServiceResourceAggregates o2) {
                    return (int)(o1.getMinute() - o2.getMinute());
                }
            });
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ServiceResourceInstanceAggregates> getServiceResourceInstanceAggregates(long start, long end,
                                                                                       String bizLine, String service,
                                                                                       String resource) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-dependency-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("callee_biz", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("callee_svc", service));
            boolQueryBuilder.must(QueryBuilders.termQuery("callee_res", resource));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                                                                          .to(end-1));

            searchSourceBuilder.size(10000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            Map<String, ServiceResourceInstanceAggregates> requests = new HashMap<>();
            searchHitList.forEach(record -> {
                Map<String,Object> source = record.getSource();
                long count = Long.valueOf(source.get("count").toString());
                long error = Long.valueOf(source.get("error").toString());
                long avg_latency = Long.valueOf(source.get("avg_latency").toString());
                long minute = Long.valueOf(source.get("minute").toString());
                String instance = source.get("callee_ins").toString();
                Aggregates aggregates = new Aggregates(count, error, avg_latency);

                int granularity = parseGranularityInterval(start, end);
                minute = start + (minute - start)/(granularity*60)*granularity*60;

                ServiceResourceInstanceAggregates srir =
                        new ServiceResourceInstanceAggregates(bizLine, service, resource,
                                instance, minute, aggregates);
                if (requests.containsKey(srir.id())) {
                    requests.put(srir.id(), requests.get(srir.id()).merge(srir));
                } else {
                    requests.put(srir.id(), srir);
                }
            });

            return new ArrayList<>(requests.values());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ServiceResourceHistogram getServiceResourceHistogram(long start, long end,
                                                                       String bizLine, String service,
                                                                       String resource, int count) {
        TreeMap<Long, Long> countMap = null;

        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-service-resource-histogram-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("bizLine", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("service", service));
            boolQueryBuilder.must(QueryBuilders.termQuery("resource", resource));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                    .to(end-1));

            searchSourceBuilder.size(10000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            ServiceResourceHistogram result = null;
            for (SearchHit record : searchHitList) {
                Map<String,Object> source = record.getSource();
                String bl = source.get("bizLine").toString();
                String svc = source.get("service").toString();
                String res = source.get("resource").toString();
                long minute = Long.valueOf(source.get("minute").toString());
                long timestamp = Long.valueOf(source.get("timestamp").toString());
                long interval = Long.valueOf(source.get("interval").toString());

                TreeMap<Long, Long> bucketCounts = new TreeMap<>();
                Map<String, Integer> bucketCountsMap = (Map<String, Integer>) source.get("bucketCounts");

                for (String key : bucketCountsMap.keySet()){
                    bucketCounts.put(Long.valueOf(key), bucketCountsMap.get(key).longValue());
                }

                ServiceResourceHistogram histogram = new ServiceResourceHistogram(bl, svc, res, bucketCounts, minute);
                histogram.setTimestamp(timestamp);
                histogram.setInterval(interval);

                if (result == null) {
                    result = histogram;
                } else if (result.idWithOutTime().equals(histogram.idWithOutTime())) {
                    result.merge(histogram);
                }
            }

            if (result != null) {
                countMap = mergeData(result, result.getBucketCounts(), count);
                result.setBucketCounts(countMap);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static TreeMap<Long, Long> mergeData(ServiceResourceHistogram res, TreeMap<Long, Long> countMap, int count) {
    	Long min = Long.MAX_VALUE;
    	Long max = Long.MIN_VALUE;
    	
    	Long start = 0l;
    	
    	Long distance = 0l;
    	Long period = 0l;
    	
    	TreeMap<Long, Long> resultMap = null;
    	
    	if (countMap == null) {
    		return null;
    	}
    	
    	resultMap = new TreeMap<Long, Long>();
    	if (countMap.size() > 0) {
    		for (Entry<Long, Long> entry : countMap.entrySet()) {
    			min = Long.min(entry.getKey(), min);
    			max = Long.max(entry.getKey(), max);
    		}
    		
    		distance = max - min + 5000 - 1;
    		if (distance / 5000 < count) {
    			return countMap;
    		}
    		
    		period = distance / count;
    		res.setInterval(period);
    		
    		for (Entry<Long, Long> entry : countMap.entrySet()) {
    			start = entry.getKey();
    			start = (start - min) / period * period + min;
    			if (resultMap.containsKey(start) == false) {
    				resultMap.put(start, entry.getValue());
    			} else {
    				resultMap.put(start, resultMap.get(start) + entry.getValue());
    			}
    		}
    	}
    	
    	return resultMap;
    }

    public static List<ServiceResourcePercentiles> getServiceResourcePercentiles(long start, long end,
                                                                             String bizLine, String service,
                                                                             String resource) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-service-resource-percentiles-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("bizLine", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("service", service));
            boolQueryBuilder.must(QueryBuilders.termQuery("resource", resource));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                    .to(end-1));

            searchSourceBuilder.size(10000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            Map<String, ServiceResourcePercentiles> requests = new HashMap<>();
            Map<String, List<ServiceResourcePercentiles>> percentilesAggregationMap = new HashMap<>();

            searchHitList.forEach(record -> {
                Map<String,Object> source = record.getSource();
                String bl = source.get("bizLine").toString();
                String svc = source.get("service").toString();
                String res = source.get("resource").toString();
                long minute = Long.valueOf(source.get("minute").toString());
                Map<String, Double> curMap = (Map<String, Double>) source.get("percentiles");
                Map<Double, Double> percentileMap = new HashMap<>();

                for (String key : curMap.keySet()) {
                    percentileMap.put(Double.valueOf(key), curMap.get(key));
                }

                int granularity = parseGranularityInterval(start, end);
                minute = start + (minute - start)/(granularity*60)*granularity*60;
                ServiceResourcePercentiles percentiles = new ServiceResourcePercentiles(bl, svc, res, percentileMap, minute);
                if (percentilesAggregationMap.containsKey(percentiles.id()))
                    percentilesAggregationMap.get(percentiles.id()).add(percentiles);
                else{
                    List<ServiceResourcePercentiles> srpList = new ArrayList<>();
                    srpList.add(percentiles);
                    percentilesAggregationMap.put(percentiles.id(), srpList);
                }
            });

            for (String key : percentilesAggregationMap.keySet()) {
                List<ServiceResourcePercentiles> srpLists = percentilesAggregationMap.get(key);
                int num = srpLists.size();
                ServiceResourcePercentiles  srp = srpLists.get(0);
                srpLists.remove(0);
                Map<Double, Double> srpPercentileMap = srp.getPercentiles();

                srpLists.forEach(record -> {
                    Map<Double, Double> cur = record.getPercentiles();
                    for (Double dKey1 : srpPercentileMap.keySet()) {
                        srpPercentileMap.put(dKey1, srpPercentileMap.get(dKey1) + cur.get(dKey1));
                    }
                });

                for (double dKey2 : srpPercentileMap.keySet()) {
                    srpPercentileMap.put(dKey2, srpPercentileMap.get(dKey2)/num);
                }

                requests.put(key, srp);
            }

            return new ArrayList<>(requests.values());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<ServiceResourceInstancePercentiles> getServiceResourceInstancePercentiles(long start, long end,
                                                                                 String bizLine, String service,
                                                                                 String resource) {
        List<String> dates = DateUtils.getDatesBetween(start, end);
        StringBuilder indexSB = new StringBuilder();
        dates.forEach(date -> indexSB.append("enn-service-resource-instance-percentiles-").append(date).append(","));
        String indexes = indexSB.substring(0, indexSB.length() - 1);

        try {
            // query data by ES Java REST Client API
            RestHighLevelClient highLevelClient = EnnMonitorElasticSearchClientUtil.getRestHighLevelClient();

            final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(30));

            SearchRequest searchRequest = new SearchRequest(indexes);
            searchRequest.indicesOptions(IndicesOptions.fromOptions(true,true,false,false));
            searchRequest.scroll(scroll);

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

            boolQueryBuilder.must(QueryBuilders.termQuery("bizLine", bizLine));
            boolQueryBuilder.must(QueryBuilders.termQuery("service", service));
            boolQueryBuilder.must(QueryBuilders.termQuery("resource", resource));

            boolQueryBuilder.must(QueryBuilders.rangeQuery("minute").from(start)
                                                                         .to(end-1));

            searchSourceBuilder.size(10000);
            searchSourceBuilder.query(boolQueryBuilder);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = highLevelClient.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            List<SearchHit> searchHitList = new ArrayList<>();
            for (SearchHit searchHit : searchHits) {
                searchHitList.add(searchHit);
            }

            List<String> scrollIdList = new ArrayList<>();
            String scrollId = searchResponse.getScrollId();
            scrollIdList.add(scrollId);

            // loop to fetch remain match data
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = highLevelClient.searchScroll(scrollRequest);
                searchHits = searchResponse.getHits().getHits();
                if (searchHits != null){
                    for (SearchHit searchHit : searchHits) {
                        searchHitList.add(searchHit);
                    }
                }
                scrollId = searchResponse.getScrollId();
                scrollIdList.add(scrollId);
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.setScrollIds(scrollIdList);

            highLevelClient.clearScrollAsync(clearScrollRequest, new ActionListener<ClearScrollResponse>() {
                @Override
                public void onResponse(ClearScrollResponse clearScrollResponse) {
                    Logger.getRootLogger().info("Clear scroll search contexts successfully!");
                }

                @Override
                public void onFailure(Exception e) {
                    Logger.getRootLogger().error("Clear scroll search contexts failed, which caused by " + e.getMessage());
                }
            });

            Map<String, ServiceResourceInstancePercentiles> requests = new HashMap<>();
            Map<String, List<ServiceResourceInstancePercentiles>> percentilesAggregationMap = new HashMap<>();
            searchHitList.forEach(record -> {
                Map<String,Object> source = record.getSource();
                String bl = source.get("bizLine").toString();
                String svc = source.get("service").toString();
                String ins = source.get("instance").toString();
                String res = source.get("resource").toString();
                long minute = Long.valueOf(source.get("minute").toString());
                Map<String, Double> curMap = (Map<String, Double>) source.get("percentiles");
                Map<Double, Double> percentileMap = new HashMap<>();

                for (String key : curMap.keySet()) {
                    percentileMap.put(Double.valueOf(key), curMap.get(key));
                }

                int granularity = parseGranularityInterval(start, end);
                minute = start + (minute - start)/(granularity*60)*granularity*60;

                ServiceResourceInstancePercentiles percentiles = new ServiceResourceInstancePercentiles(bl, svc, res, ins, percentileMap, minute);
                if (percentilesAggregationMap.containsKey(percentiles.id()))
                    percentilesAggregationMap.get(percentiles.id()).add(percentiles);
                else{
                    List<ServiceResourceInstancePercentiles> srpList = new ArrayList<>();
                    srpList.add(percentiles);
                    percentilesAggregationMap.put(percentiles.id(), srpList);
                }
            });

            for (String key : percentilesAggregationMap.keySet()) {
                List<ServiceResourceInstancePercentiles> srpLists = percentilesAggregationMap.get(key);
                int num = srpLists.size();
                ServiceResourceInstancePercentiles  srp = srpLists.get(0);
                srpLists.remove(0);
                Map<Double, Double> srpPercentileMap = srp.getPercentiles();

                srpLists.forEach(record -> {
                    Map<Double, Double> cur = record.getPercentiles();
                    for (Double dKey1 : srpPercentileMap.keySet()) {
                        srpPercentileMap.put(dKey1, srpPercentileMap.get(dKey1) + cur.get(dKey1));
                    }
                });

                for (double dKey2 : srpPercentileMap.keySet()) {
                    srpPercentileMap.put(dKey2, srpPercentileMap.get(dKey2)/num);
                }

                requests.put(key, srp);
            }

            return new ArrayList<>(requests.values());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static int parseGranularityInterval(long start, long end) {
        final int[] timeIntervel = {1, 6, 12, 24, 72, 168, 336, 720};    // Unit: hour
        final int[] granularity  = {1, 5, 10, 20, 60, 120, 240, 720};    // unit: minute

        long interval = (end - start) / 3600;

        for (int num=0; num<timeIntervel.length; ++num) {
            if (interval <= timeIntervel[num])
                return granularity[num];
        }

        return granularity[timeIntervel.length-1];
    }

    public static List<EnnDependencyLink> merge(Iterable<EnnDependencyLink> in) {
        Map<Pair, Aggregates> aggMap = new LinkedHashMap<>();

        for (EnnDependencyLink link : in) {
            Pair parentChild =  Pair.of(link.caller(), link.callee(), link.minute());
            if (aggMap.containsKey(parentChild)) {
                Aggregates agg = aggMap.get(parentChild);
                agg.avgLatency = agg.callCount * agg.avgLatency + link.callCount() * link.avgLatency();
                agg.callCount += link.callCount();
                agg.errorCount += link.errorCount();
                agg.avgLatency /= agg.callCount;
            } else {
                aggMap.put(parentChild, link.aggregates());
            }
        }

        return link(aggMap);
    }

    static List<EnnDependencyLink> link(Map<Pair, Aggregates> aggregates) {
        List<EnnDependencyLink> result = new ArrayList<>(aggregates.size());
        for (Map.Entry<Pair, Aggregates> entry : aggregates.entrySet()) {
            Pair parentChild = entry.getKey();
            result.add(EnnDependencyLink.newBuilder()
                    .caller(parentChild.left())
                    .callee(parentChild.right())
                    .aggregates(entry.getValue())
                    .minute(parentChild.minute())
                    .build());
        }
        return result;
    }
}
