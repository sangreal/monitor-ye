package enn.monitor.trace.dataapi.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EnnMonitorElasticSearchClientUtil {
    private static RestClient restClient = RestClient.builder(
            new HttpHost(EnnMonitorConfigUtil.getElasticSearchHost(), EnnMonitorConfigUtil.getElasticSearchPort(), "http")).build();

    private static RestHighLevelClient highLevelRestClient = new RestHighLevelClient(restClient);

    public static RestHighLevelClient getRestHighLevelClient() {
        return highLevelRestClient;
    }
}
