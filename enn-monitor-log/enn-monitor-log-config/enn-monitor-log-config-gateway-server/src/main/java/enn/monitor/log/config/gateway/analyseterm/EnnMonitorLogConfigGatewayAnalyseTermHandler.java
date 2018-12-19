package enn.monitor.log.config.gateway.analyseterm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;
import enn.monitor.log.config.gateway.analyse.term.proto.EnnMonitorLogConfigGatewayAnalyseTermResponse;

public class EnnMonitorLogConfigGatewayAnalyseTermHandler extends HttpServlet {

	private static final long serialVersionUID = -3168214395200700034L;
	
	private EnnMonitorLogConfigAnalyseTermClient client = null;
	
	private static Gson gson = new Gson();
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogConfigGatewayAnalyseTermHandler(String host, int port) {
		client = new EnnMonitorLogConfigAnalyseTermClient(host, port);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        EnnMonitorLogConfigAnalyseTermGetResponse analyseTermGetResponse = null;
        
        EnnMonitorLogConfigGatewayAnalyseTermResponse gatewayAnalyseTermResponse = new EnnMonitorLogConfigGatewayAnalyseTermResponse();
        
        String serviceId = req.getParameter("serviceId");
        
        gatewayAnalyseTermResponse.setSuccess(false);
        if (serviceId != null && serviceId.equals("") == false) {
        	try {
            	analyseTermGetResponse = client.getAnalyseTerm(EnnMonitorLogConfigAnalyseTermGetRequest.newBuilder().setBelongToServiceId(Long.parseLong(serviceId)).build());
            	if (analyseTermGetResponse.getIsSuccess() == true) {
            		gatewayAnalyseTermResponse.setSuccess(true);
            		if (analyseTermGetResponse.getAnalyseTermTableList() != null && analyseTermGetResponse.getAnalyseTermTableList().size() > 0) {
            			for (EnnMonitorLogConfigAnalyseTermTable analyseTermTable : analyseTermGetResponse.getAnalyseTermTableList()) {
            				if (analyseTermTable.getAddTerm() != null && analyseTermTable.getAddTerm().equals("") == false) {
            					gatewayAnalyseTermResponse.addTerm(analyseTermTable.getAddTerm());
            				}
            				if (analyseTermTable.getFilterTerm() != null && analyseTermTable.getFilterTerm().equals("") == false) {
            					gatewayAnalyseTermResponse.filterTerm(analyseTermTable.getFilterTerm());
            				}
            			}
            		}
            	}
            } catch (Exception e) {
            	gatewayAnalyseTermResponse.setError(e.getMessage());
            	logger.error(e.getMessage(), e);
            }
        } else {
        	gatewayAnalyseTermResponse.setError("serviceId is not set");
        	logger.error("serviceId is not set");
        }
        
        response.setCharacterEncoding("UTF-8");  
        
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.append(gson.toJson(gatewayAnalyseTermResponse));  
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
