package enn.monitor.log.config.gateway.template.term;

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

import enn.monitor.log.config.gateway.template.term.proto.EnnMonitorLogConfigGatewayTemplateTermRequest;
import enn.monitor.log.config.gateway.template.term.proto.EnnMonitorLogConfigGatewayTemplateTermResponse;
import enn.monitor.log.config.template.term.client.EnnMonitorLogConfigTemplateTermClient;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse;

public class EnnMonitorLogConfigGatewayTemplateTermHandler extends HttpServlet {

	private static final long serialVersionUID = 2866389108460862811L;
	
	private EnnMonitorLogConfigTemplateTermClient client = null;
	
	private static Gson gson = new Gson();
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogConfigGatewayTemplateTermHandler(String host, int port) {
		client = new EnnMonitorLogConfigTemplateTermClient(host, port);
	}
	
	@Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        
        EnnMonitorLogConfigGatewayTemplateTermRequest templateTermRequest = null;
        EnnMonitorLogConfigGatewayTemplateTermResponse templateTermResponse = new EnnMonitorLogConfigGatewayTemplateTermResponse();
        EnnMonitorLogConfigTemplateTermCreateRequest.Builder requestBuilder = EnnMonitorLogConfigTemplateTermCreateRequest.newBuilder();
        EnnMonitorLogConfigTemplateTermCreateResponse responseBuilder = null;
        
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }

        // 将资料解码
        String reqBody = stringBuilder.toString();
        String json = URLDecoder.decode(reqBody, HTTP.UTF_8);
        
        logger.info("templateTerm: " + json);
        
        templateTermResponse.setSuccess(false);
        templateTermRequest = gson.fromJson(json, EnnMonitorLogConfigGatewayTemplateTermRequest.class);
        if (templateTermRequest.getBatchId() >= 0 && 
        		templateTermRequest.getBelongToServiceId() >= 0 &&
        		templateTermRequest.getTemplateTerm() != null && templateTermRequest.getTemplateTerm().equals("") == false) {
        	requestBuilder.setBatchId(templateTermRequest.getBatchId());
        	requestBuilder.setBelongToServiceId(templateTermRequest.getBelongToServiceId());
        	requestBuilder.setTemplateTerm(templateTermRequest.getTemplateTerm());
        	requestBuilder.setIsSelected(templateTermRequest.isSelected());
        	requestBuilder.setTemplateTermValue(templateTermRequest.getTermValue());
        	
        	requestBuilder.setCreateUser("micklongen");
        	
        	try {
        		responseBuilder = client.createTemplateTerm(requestBuilder.build());
        		if (responseBuilder.getIsSuccess() == true) {
        			templateTermResponse.setSuccess(true);
        		} else {
        			templateTermResponse.setError(responseBuilder.getError());
        		}
    		} catch (Exception e) {
    			templateTermResponse.setError(e.getMessage());
    			logger.error(e.getMessage(), e);
    		}
        } else {
        	templateTermResponse.setError("the templateTerm is invalid, it is " + json);
        	logger.error("the templateTerm is invalid, it is " + json);
        }
        
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(gson.toJson(templateTermResponse));  
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
