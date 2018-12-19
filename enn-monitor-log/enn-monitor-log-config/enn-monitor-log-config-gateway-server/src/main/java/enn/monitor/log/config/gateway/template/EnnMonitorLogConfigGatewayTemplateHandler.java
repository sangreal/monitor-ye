package enn.monitor.log.config.gateway.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.log.config.gateway.template.proto.EnnMonitorLogConfigGatewayTemplateRequest;
import enn.monitor.log.config.gateway.template.proto.EnnMonitorLogConfigGatewayTemplateResponse;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse;

public class EnnMonitorLogConfigGatewayTemplateHandler extends HttpServlet {

	private static final long serialVersionUID = -32986777482414439L;
	
	private EnnMonitorLogConfigTemplateClient client = null;
	
	private static Gson gson = new Gson();
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogConfigGatewayTemplateHandler(String host, int port) {
		client = new EnnMonitorLogConfigTemplateClient(host, port);
	}
	
	@Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        
        EnnMonitorLogConfigGatewayTemplateRequest templateRequest = null;
        EnnMonitorLogConfigGatewayTemplateResponse templateResponse = new EnnMonitorLogConfigGatewayTemplateResponse();
        EnnMonitorLogConfigTemplateCreateRequest.Builder requestBuilder = EnnMonitorLogConfigTemplateCreateRequest.newBuilder();
        EnnMonitorLogConfigTemplateCreateResponse responseBuilder = null;
        
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }

        // 将资料解码
        String reqBody = stringBuilder.toString();
        String json = URLDecoder.decode(reqBody, HTTP.UTF_8);
        
        templateResponse.setSuccess(false);
        templateRequest = gson.fromJson(json, EnnMonitorLogConfigGatewayTemplateRequest.class);
        if (templateRequest.getBatchId() >= 0 && 
        		templateRequest.getTemplateKey() != null && templateRequest.getTemplateKey().equals("") == false) {
        	logger.info("templateKey: " + requestBuilder.getTemplateKey());
        	
        	requestBuilder.setTemplateKey(templateRequest.getTemplateKey());
        	if (templateRequest.getBelongToParentTemplate() != null && templateRequest.getBelongToParentTemplate().equals("") == false) {
        		requestBuilder.setBelongToParentTemplate(templateRequest.getBelongToParentTemplate());
        	}
        	
        	requestBuilder.setBelongToServiceId(templateRequest.getBelongToServiceId());
        	
        	if (templateRequest.getTagId() >= 0) {
        		requestBuilder.setTagId(templateRequest.getTagId());
        	}
        	
        	requestBuilder.setBatchId(templateRequest.getBatchId());
        	
        	requestBuilder.setCreateUser("micklongen");
        	
        	try {
        		responseBuilder = client.createTemplate(requestBuilder.build());
        		if (responseBuilder.getIsSuccess() == true) {
        			templateResponse.setSuccess(true);
        		} else {
        			templateResponse.setError(responseBuilder.getError());
        		}
    		} catch (Exception e) {
    			templateResponse.setError(e.getMessage());
    			logger.error(e.getMessage(), e);
    		}
        } else {
        	templateResponse.setError("the template is invalid, it is " + json);
        	logger.error("the template is invalid, it is " + json);
        }
        
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(gson.toJson(templateResponse));  
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {  
            logger.error(e.getMessage(), e);
        } finally {  
            if (out != null) {  
                out.close();  
            }  
        }  
    }  

}
