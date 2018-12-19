package enn.monitor.security.gateway.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayResponse;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.framework.common.constval.EnnMonitorSecurityConstants;
import enn.monitor.security.gateway.grpc.client.EnnMonitorSecurityGatewayGrpcClient;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest;

public class EnnMonitorSecurityHandler extends HttpServlet {
    private static final long serialVersionUID = 2271797150647771294L;  
    
    private static final Logger logger = LogManager.getLogger();
    
    private CounterMetric putMetrics = MetricManager.getCounterMetric(EnnMonitorSecurityHandler.class, "put");
    
    private EnnMonitorSecurityGatewayGrpcClient gatewayClient = null;
      
    public EnnMonitorSecurityHandler(String server, int port){
    	gatewayClient = new EnnMonitorSecurityGatewayGrpcClient(server, port);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        Access-Control-Allow-Credentials:true
        Access-Control-Allow-Headers:content-type, source, token
        Access-Control-Allow-Methods:POST
        Access-Control-Allow-Origin:null
        Access-Control-Max-Age:1800
        Allow:GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH
        Content-Length:0
        Date:Tue, 10 Apr 2018 06:35:21 GMT
        Vary:Origin
        X-Application-Context:zipkin-server:shared,kafka:9411
         */
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "content-type, source, token");
        resp.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
        resp.setHeader("Vary", "Origin");
        super.doOptions(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String line = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }

        // 将资料解码
        String reqBody = stringBuilder.toString();
        String json = URLDecoder.decode(reqBody, HTTP.UTF_8);
        
        EnnMonitorSecurityGatewayRequest.Builder request = EnnMonitorSecurityGatewayRequest.newBuilder();
        request.setSource(req.getHeader(EnnMonitorSecurityConstants.SOURCE));
        request.setToken(req.getHeader(EnnMonitorSecurityConstants.TOKEN));
		request.setJsonList(json);
		
//		logger.info(request.toString());
		
		putMetrics.markEvent();

        EnnMonitorSecurityGatewayResponse response1 = gatewayClient.put(request.build(), null);
        if (response1.getIsSuccess() == false) {
        	logger.error(response1.getError());
        }

        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }  
}
