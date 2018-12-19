package enn.monitor.metric.kubelet.streaming.cache;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 2017/4/14.
 */
public class K8sApiReader {
    private HttpHost targetHost;
    private CredentialsProvider credentialsProvider;
    private UsernamePasswordCredentials creds;
    private AuthCache authCache;
    private BasicScheme basicAuth;
    private CloseableHttpClient httpClient;
    private HttpClientContext context;
    private Gson gson;

    public K8sApiReader(String apiServerUrl, String apiUser, String apiPass) {
        creds = new UsernamePasswordCredentials(apiUser, apiPass);
//        targetHost = new HttpHost("10.19.140.200", 6443, "https");
        targetHost = HttpHost.create("https://" + apiServerUrl);
        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                creds);
        credentialsProvider.setCredentials(AuthScope.ANY,creds);
        authCache = new BasicAuthCache();
        basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);
        context = HttpClientContext.create();
        context.setCredentialsProvider(credentialsProvider);
        context.setAuthCache(authCache);

        httpClient = HttpClients.custom()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .build();

        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(builder.build()))
                    .build();
            httpClient = HttpClients.custom()
                    .setConnectionManager(new PoolingHttpClientConnectionManager(socketFactoryRegistry))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        gson = new Gson();
    }

    public ClusterInfo getClusterInfo() {
        ClusterInfo clusterInfo = new ClusterInfo();
        updateNodes(clusterInfo);
        updatePods(clusterInfo);
//        clusterInfo.setNodes(getNodes());
//        clusterInfo.setPods(getPods());
        return clusterInfo;
    }

    private void updateNodes(ClusterInfo clusterInfo) {
        Map<String, Node> nodes = new HashMap<>();
        String result = httpGet("/api/v1/nodes");
        K8sApiNodeList k8sApiNodeList = gson.fromJson(result, K8sApiNodeList.class);
        for (Node node : k8sApiNodeList.getNodes()) {
            nodes.put(node.getMetadata().getName(), node);
        }
        if (nodes.size() != 0) {
            clusterInfo.setResourceVersion(k8sApiNodeList.getMetadata().getResourceVersion());
            clusterInfo.setNodes(nodes);
        }
    }

    private void updatePods(ClusterInfo clusterInfo) {
        Map<String, Pod> pods = new HashMap<>();
        String result = httpGet("/api/v1/pods");
        K8sApiPodList k8sApiNodeList = gson.fromJson(result, K8sApiPodList.class);
        for (Pod pod : k8sApiNodeList.getPods()) {
            pods.put(pod.getMetadata().getName(), pod);
        }
        if (pods.size() != 0) {
            clusterInfo.setPods(pods);
        }
    }

    private String httpGet(String uri) {
        HttpGet httpget = new HttpGet(targetHost.toURI() + uri);
        String rtn = "";
        try {
            CloseableHttpResponse response = httpClient.execute(targetHost, httpget, context);
            try {
                rtn = EntityUtils.toString(response.getEntity());
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpget.releaseConnection();
        }
        return rtn;
    }


}
