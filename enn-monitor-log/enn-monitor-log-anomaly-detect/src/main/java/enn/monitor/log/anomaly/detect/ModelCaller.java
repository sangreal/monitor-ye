package enn.monitor.log.anomaly.detect;

import com.google.gson.Gson;
import enn.monitor.log.anomaly.detect.proto.LSTMRegPredictRequest;
import enn.monitor.log.anomaly.detect.proto.LSTMRegPredictResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by weize on 18-8-13.
 */
public class ModelCaller {
    private static ModelCaller instance;
    private static ThreadPoolExecutor threadPoolExecutor;
    private static HttpClient httpClient;
    private static Gson gson;

    private ModelCaller() {
        threadPoolExecutor
                = new ThreadPoolExecutor(5, 50, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.closeIdleConnections(10, TimeUnit.SECONDS);
        httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(manager)
                .build();
        gson = new Gson();
    }

    public static ModelCaller getInstance() {
        if (instance == null) {
            synchronized (ModelCaller.class) {
                if (instance == null) {
                    instance = new ModelCaller();
                }
            }
        }
        return instance;
    }
    public void predictAsync(String modelUrl, String token, long minute, List<double[]> vecs) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<List<double[]>> vecsList = new ArrayList<>();
                vecsList.add(vecs);
                LSTMRegPredictRequest request = new LSTMRegPredictRequest(vecsList);
                HttpPost httpPost = new HttpPost(modelUrl);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setSocketTimeout(10000)
                        .setConnectTimeout(3000)
                        .setConnectionRequestTimeout(10000)
                        .build();
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(new StringEntity(gson.toJson(request), ContentType.APPLICATION_JSON));
                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    String strResp = EntityUtils.toString(response.getEntity(), "utf-8");
                    LSTMRegPredictResponse predictResponse = gson.fromJson(strResp, LSTMRegPredictResponse.class);
                    PredictCache.getInstance().addPredictVec(token, minute, predictResponse.getPredictions().get(0));
                } catch (IOException e) {
                    Logger.getRootLogger().error(e.getMessage(), e);
                } finally {
                    httpPost.releaseConnection();
                }
            }
        });
    }
}
