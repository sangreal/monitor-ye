package enn.monitor.security.gateway.http.client;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.common.constval.EnnMonitorSecurityConstants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnnMonitorSecurityGatewayHttpClient {
	private static final Logger logger = LogManager.getLogger();
	
	private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
	
	private String host = null;
	private int port = 0;
	
	public EnnMonitorSecurityGatewayHttpClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void put(String source, String token, String json) throws Exception {
		
		RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		Request request = new Request.Builder()
                .url("http://" + host + ":" + port + "/security/gateway")
                .addHeader(EnnMonitorSecurityConstants.SOURCE, source)
                .addHeader(EnnMonitorSecurityConstants.TOKEN, token)
                .post(requestBody)
                .build();
		
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
        	
            @Override
            public void onFailure(Call call, IOException e) {
            	logger.error(e.getMessage(), e);;
            }
            
            @Override
            public void onResponse(Call call, Response response) throws IOException {
	        }
            
        });
	}
	
	public static void main(String[] args) throws Exception {
		EnnMonitorSecurityGatewayHttpClient httpClient = new EnnMonitorSecurityGatewayHttpClient("127.0.0.1", 8090);
		httpClient.put("app", "micklongen", "{\"a\":\"b\"}");
	}
}
