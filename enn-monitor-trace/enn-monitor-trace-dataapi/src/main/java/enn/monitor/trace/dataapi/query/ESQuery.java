package enn.monitor.trace.dataapi.query;

import enn.monitor.trace.dataapi.util.EnnMonitorElasticsearchHttpClient;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by wst_dreg on 2018-03-20.
 */
public abstract class ESQuery {
    public abstract String buildURL();
    public abstract String buildQueryBody();
    public abstract String buildAggregationBody();

    public String buildRequestBody() {
        StringBuilder requestBody = new StringBuilder();

        String queryBody = buildQueryBody();
        String aggregationBody = buildAggregationBody();

        requestBody.append("{")
                .append(queryBody)
                .append(",")
                .append(aggregationBody)
                .append("}");

        return  requestBody.toString();
    }

    public String getResult() {
        String esURL = buildURL();
        String bodyString = buildRequestBody();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyString);
        Request request = new Request.Builder()
                                     .url(esURL)
                                     .post(requestBody)
                                     .build();
        Call call = EnnMonitorElasticsearchHttpClient.client.newCall(request);

        try {
            Response response = call.execute();
            String respBody = response.body().string();

            return respBody;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
