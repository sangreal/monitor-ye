package enn.monitor.streaming.sink.opentsdb;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by weize on 17-8-22.
 */

public class OpenTSDBHttpClient<T> {
    private static final Logger logger = LogManager.getLogger(OpenTSDBHttpClient.class);

    private String opentsdbPutUrl = null;
    private HttpClient httpClient = null;

    public OpenTSDBHttpClient(String hostPort) {
        this.opentsdbPutUrl = "http://" + hostPort + "/api/put";
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.closeIdleConnections(10, TimeUnit.SECONDS);
        httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(manager)
                .build();
    }

    public List<List<T>> split(List<T> opentsdbJsonList, int batch) throws Exception {
        int i, j;

        List<T> tmpOpentsdbJsonList = null;
        List<List<T>> result = new ArrayList<List<T>>();

        if (opentsdbJsonList.size() > batch) {
            for (i = 0; i < opentsdbJsonList.size(); i += batch) {
                tmpOpentsdbJsonList = new ArrayList<T>();
                for (j = i; j < i + batch && j < opentsdbJsonList.size(); ++j) {
                    tmpOpentsdbJsonList.add(opentsdbJsonList.get(j));
                }
                result.add(tmpOpentsdbJsonList);
            }
        } else {
            //sendData(new Gson().toJson(opentsdbJsonList));
            result.add(opentsdbJsonList);
        }

        return result;
    }

    public void sendData(String jsonString) throws Exception {
        HttpPost httpPost = new HttpPost(opentsdbPutUrl);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(10000)
                .build();
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpPost.releaseConnection();
        }
    }

}

