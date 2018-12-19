package enn.monitor.log.config.gateway.batchId;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.log.config.common.client.EnnMonitorLogConfigCommonClient;
import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse;
import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest;
import enn.monitor.log.config.gateway.batchid.proto.EnnMonitorLogConfigGatewayBatchIdResponse;

public class EnnMonitorLogConfigGatewayBatchIdHandler extends HttpServlet {

	private static final long serialVersionUID = -481588101095267390L;
	
	private EnnMonitorLogConfigCommonClient client = null;
	
	private static Gson gson = new Gson();
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogConfigGatewayBatchIdHandler(String host, int port) {
		client = new EnnMonitorLogConfigCommonClient(host, port);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        EnnMonitorLogConfigBatchIdGetResponse batchIdResponse = null;
        
        EnnMonitorLogConfigGatewayBatchIdResponse batchIdresponse = new EnnMonitorLogConfigGatewayBatchIdResponse();
        
        String key = req.getParameter("batchId_key");
        
        batchIdresponse.setSuccess(false);
        if (key != null && key.equals("") == false) {
        	try {
            	batchIdResponse = client.getBatchId(EnnMonitorLogConfigCommonBatchIdGetRequest.newBuilder().setKey(key).build());
            	if (batchIdResponse.getIsSuccess() == true) {
            		batchIdresponse.setSuccess(true);
            		batchIdresponse.setBatchId(batchIdResponse.getBatchId());
            	}
            } catch (Exception e) {
            	batchIdresponse.setError(e.getMessage());
            	logger.error(e.getMessage(), e);
            }
        } else {
        	batchIdresponse.setError("batchId_key is not set");
        	logger.error("batchId_key is not set");
        }
        
        response.setCharacterEncoding("UTF-8");  
        
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(gson.toJson(batchIdresponse));  
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
