package enn.monitor.log.config.gateway.client;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.common.httpclient.OkHttpClientUtils;
import enn.monitor.log.config.gateway.analyse.term.proto.EnnMonitorLogConfigGatewayAnalyseTermResponse;
import enn.monitor.log.config.gateway.batchid.proto.EnnMonitorLogConfigGatewayBatchIdResponse;
import enn.monitor.log.config.gateway.template.proto.EnnMonitorLogConfigGatewayTemplateRequest;
import enn.monitor.log.config.gateway.template.proto.EnnMonitorLogConfigGatewayTemplateResponse;
import enn.monitor.log.config.gateway.template.term.proto.EnnMonitorLogConfigGatewayTemplateTermRequest;
import enn.monitor.log.config.gateway.template.term.proto.EnnMonitorLogConfigGatewayTemplateTermResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnnMonitorLogConfigGatewayClient {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static final OkHttpClient client = new OkHttpClient().newBuilder().build();
	
	private static Gson gson = new Gson();
	
	private String batchIdUrl = null;
	private String templateTermUrl = null;
	private String templateUrl = null;
	private String analyseTermUrl = null;
	
	public EnnMonitorLogConfigGatewayClient(String host, int port) {
		batchIdUrl = "http://" + host + ":" + port + "/template/batchId?batchId_key=";
		templateTermUrl = "http://" + host + ":" + port + "/template/term";
		templateUrl = "http://" + host + ":" + port + "/template/content";
		analyseTermUrl = "http://" + host + ":" + port + "/template/analyseterm?serviceId=";
	}
	
	public EnnMonitorLogConfigGatewayAnalyseTermResponse getAnalyseTerm(long serviceId) throws Exception {
		String requestUrl = analyseTermUrl + serviceId;
		Request request = new Request.Builder().url(requestUrl).build();
		
		EnnMonitorLogConfigGatewayAnalyseTermResponse analyseTermResponse = null;
		
		Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String respBody = response.body().string();

            analyseTermResponse = gson.fromJson(respBody, EnnMonitorLogConfigGatewayAnalyseTermResponse.class);
            return analyseTermResponse;
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }

        return null;
	}
	
	public EnnMonitorLogConfigGatewayBatchIdResponse getBatchId(String key) throws Exception {
		String requestUrl = batchIdUrl + key;
		Request request = new Request.Builder().url(requestUrl).build();
		
		EnnMonitorLogConfigGatewayBatchIdResponse batchIdResponse = null;
		
		Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String respBody = response.body().string();

            batchIdResponse = gson.fromJson(respBody, EnnMonitorLogConfigGatewayBatchIdResponse.class);
            return batchIdResponse;
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }

        return null;
	}
	
	public EnnMonitorLogConfigGatewayTemplateTermResponse createTemplateTerm(EnnMonitorLogConfigGatewayTemplateTermRequest templateTerm) throws Exception {
		RequestBody requestBody = RequestBody.create(OkHttpClientUtils.JSON, gson.toJson(templateTerm));
		
		EnnMonitorLogConfigGatewayTemplateTermResponse templateTermResponse = null;
		
		Request request = new Request.Builder()
				.url(templateTermUrl)
				.post(requestBody)
				.build();
		
		Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String respBody = response.body().string();
            
            templateTermResponse = gson.fromJson(respBody, EnnMonitorLogConfigGatewayTemplateTermResponse.class);

            return templateTermResponse;
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }

        return null;
	}
	
	public EnnMonitorLogConfigGatewayTemplateResponse createTemplate(EnnMonitorLogConfigGatewayTemplateRequest template) throws Exception {
		RequestBody requestBody = RequestBody.create(OkHttpClientUtils.JSON, gson.toJson(template));
		
		EnnMonitorLogConfigGatewayTemplateResponse templateResponse = null;
		
		Request request = new Request.Builder()
				.url(templateUrl)
				.post(requestBody)
				.build();
		
		Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String respBody = response.body().string();
            
//            System.out.println(respBody);
            templateResponse = gson.fromJson(respBody, EnnMonitorLogConfigGatewayTemplateResponse.class);

            return templateResponse;
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }

        return null;
	}
	
//	public static void main(String[] args) throws Exception {
//		EnnMonitorLogConfigGatewayClient gatewayClient = new EnnMonitorLogConfigGatewayClient("10.19.140.200", 29319);
//		System.out.println(gatewayClient.getBatchId("hello"));
//		
//		EnnMonitorLogConfigGatewayTemplateTermRequest templateTerm = new EnnMonitorLogConfigGatewayTemplateTermRequest();
//		templateTerm.setBatchId(100l);
//		templateTerm.setBelongToServiceId(100l);
//		templateTerm.setSelected(false);
//		templateTerm.setTemplateTerm("error1");
//		templateTerm.setTermValue(0.15);
//		EnnMonitorLogConfigGatewayTemplateTermResponse templateTermResponse = gatewayClient.createTemplateTerm(templateTerm);
//		System.out.println(templateTermResponse.isSuccess());
//		
//		EnnMonitorLogConfigGatewayTemplateRequest template = new EnnMonitorLogConfigGatewayTemplateRequest();
//		template.setBatchId(100l);
//		template.setBelongToRootTemplateId(1001l);
//		template.setBelongToServiceId(12l);
//		template.setFirstEventId(111111l);
//		template.setSecondEvent("event");
//		template.setTemplateKey("1-a-b-c-c-d-e-f");
//		EnnMonitorLogConfigGatewayTemplateResponse templateResponse = gatewayClient.createTemplate(template);
//		System.out.println(templateResponse.isSuccess());
//	}

}
