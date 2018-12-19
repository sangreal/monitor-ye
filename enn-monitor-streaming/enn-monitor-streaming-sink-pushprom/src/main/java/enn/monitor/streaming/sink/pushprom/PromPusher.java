package enn.monitor.streaming.sink.pushprom;

import com.google.gson.Gson;
import io.prometheus.client.Collector;
import io.prometheus.client.Counter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by weize on 17-5-11.
 */
public class PromPusher implements Serializable {
    private static final Logger logger = LogManager.getLogger();
    private String promPushUrl;
    private HttpClient httpClient;

    public PromPusher(String promPushUrl) {
        this.promPushUrl = promPushUrl;
        httpClient = HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();
    }

    public void push(Collector collector, String job, Map<String, String> groupKeys) throws Exception {
        PromPushRequest request = null;
        if (collector instanceof Counter) {
            Collector.MetricFamilySamples metricFamilySamples = ((Counter) collector).describe().get(0);
            groupKeys.put("job", job);
            request = new PromPushRequest(
                    "counter",
                    metricFamilySamples.name,
                    metricFamilySamples.help,
                    "inc",
                    ((Counter) collector).get(),
                    groupKeys
            );
            sendData(new Gson().toJson(request));
        }
    }

    private void sendData(String jsonString) throws Exception {
        HttpPost httpPost = new HttpPost("http://" + promPushUrl + "/");
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(20000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(10000)
                .build();
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
        try {
            HttpResponse response = httpClient.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity());
            System.out.println(responseStr);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            httpPost.releaseConnection();
        }
    }
}
