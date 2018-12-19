package enn.monitor.log.analyse.template.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EnnMonitorLogAnalyseTemplateClient {
	
private static final Logger logger = LogManager.getLogger();
	
	private static final OkHttpClient client = 
			new OkHttpClient().newBuilder()
				.connectTimeout(0, TimeUnit.MICROSECONDS)
				.readTimeout(0, TimeUnit.MICROSECONDS)
				.writeTimeout(0, TimeUnit.MICROSECONDS)
				.build();
	
	private String templateUrl = null;
	
	public EnnMonitorLogAnalyseTemplateClient(String host, int port) {
		templateUrl = "http://" + host + ":" + port + "/spark/job?index=";
	}
	
	public String analyseTemplate(String index, String tfRatio, String similarRatio) throws Exception {
		String respBody = null;
		Request request = null;
		
		String requestUrl = templateUrl + index;
		if (tfRatio != null && tfRatio.equals("") == false) {
			requestUrl = requestUrl + "&tfRatio=" + tfRatio;
		}
		if (similarRatio != null && similarRatio.equals("") == false) {
			requestUrl = requestUrl + "&similarRatio=" + similarRatio;
		}
		
		logger.info("url: " + requestUrl);
		
		request = new Request.Builder().url(requestUrl).build();
		
		Call call = client.newCall(request);

        try {
            Response response = call.execute();
            respBody = response.body().string();
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        	respBody = e.getMessage();
        }

        return respBody;
	}
	
	public static void main(String[] args) throws Exception {
		EnnMonitorLogAnalyseTemplateClient httpClient = new EnnMonitorLogAnalyseTemplateClient("10.19.248.200", 29320);
		String resp = httpClient.analyseTemplate("yancheng-monitor-system-log-2018-10-22", null, null);
		System.out.println(resp);
	}
	
}
