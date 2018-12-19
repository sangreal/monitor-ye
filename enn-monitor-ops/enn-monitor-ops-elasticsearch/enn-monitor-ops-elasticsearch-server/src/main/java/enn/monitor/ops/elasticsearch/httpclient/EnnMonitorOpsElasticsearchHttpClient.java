package enn.monitor.ops.elasticsearch.httpclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.common.httpclient.OkHttpClientUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnnMonitorOpsElasticsearchHttpClient {
	private static final Logger logger = LogManager.getLogger();
	
	private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
	
	private String host = null;
	private int port = 0;
	
	public EnnMonitorOpsElasticsearchHttpClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void put(String json) throws Exception {
		
//		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//		Request request = new Request.Builder()
//                .url("http://" + host + ":" + port + "/_cluster/reroute")
//                .post(requestBody)
//                .build();
//		
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//        	
//            @Override
//            public void onFailure(Call call, IOException e) {
//            	logger.error(e.getMessage(), e);;
//            }
//            
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//	        }
//            
//        });
//        
        
        RequestBody requestBody = RequestBody.create(OkHttpClientUtils.JSON, json);
		
		Request request = new Request.Builder()
				.url("http://" + host + ":" + port + "/_cluster/reroute")
				.post(requestBody)
				.build();
		
		Call call = client.newCall(request);
		
		Response response = call.execute();
        String respBody = response.body().string();
		
		System.out.println(respBody);
	}
	
	public static void main(String[] args) throws Exception {
		EnnMonitorOpsElasticsearchHttpClient httpClient = new EnnMonitorOpsElasticsearchHttpClient("127.0.0.1", 8090);
		httpClient.put("{\"a\":\"b\"}");
	}
}
