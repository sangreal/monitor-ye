package enn.monitor.trace.dataapi.query;


import enn.monitor.trace.proto.model.service.ResponseType;

import java.util.List;

/**
 * Created by wst_dreg on 2018-03-20.
 */
public class ServiceSummaryQuery extends ESQuery{
    @Override
    public String buildURL() {
        return null;
    }

    @Override
    public String buildQueryBody() {
        String a = "{\n" +
                "\t\"query\": { \n" +
                "\t\t\"bool\": {\n" +
                "    \t\t\"must\": [\n" +
                "    \t\t\t{\t\"range\": {\n" +
                "        \t\t\t\t\"minute\":{\n" +
                "        \t\t\t\t\t\"gte\" : 1521439920,\n" +
                "            \t\t\t\t\"lt\"  : 1521443520\n" +
                "        \t\t\t\t} \n" +
                "        \t\t\t} \n" +
                "        \t\t},\n" +
                "        \t\t{\n" +
                "        \t\t\t\"bool\": {\n" +
                "        \t\t\t\t\"should\": [\n" +
                "        \t\t\t\t\t{ \"wildcard\": { \"callee_biz\": \"M*\" } },\n" +
                "        \t\t\t\t\t{ \"wildcard\": { \"callee_svc\": \"*\" } }\n" +
                "        \t\t\t\t],\n" +
                "        \t\t\t\t\"minimum_should_match\" : 1\n" +
                "        \t\t\t}\n" +
                "        \t\t}\n" +
                "    \t\t]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"size\": 1,\n" +
                "\t\"aggs\":{\n" +
                "\t\t\"callersvc_aggs\": {\n" +
                "\t\t\t\"terms\": {\n" +
                "\t\t\t\t\"field\": \"caller_svc\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"aggs\": {\n" +
                "\t\t\t\t\"total_count\": {\n" +
                "\t\t\t\t\t\"sum\": {\n" +
                "\t\t\t\t\t\t\"field\": \"count\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"calleesvc_aggs\": {\n" +
                "\t\t\t\t\t\"terms\": {\n" +
                "\t\t\t\t\t\t\"field\": \"callee_svc\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"aggs\": {\n" +
                "\t\t\t\t\t\t\"all_count\": {\n" +
                "\t\t\t\t\t\t\t\"sum\": {\n" +
                "\t\t\t\t\t\t\t\t\"field\": \"count\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        return null;
    }

    @Override
    public String buildAggregationBody() {
        return null;
    }

//    @Override
//    public List<ResponseType> parseResponse(String respBody) {
//        return null;
//    }
}
