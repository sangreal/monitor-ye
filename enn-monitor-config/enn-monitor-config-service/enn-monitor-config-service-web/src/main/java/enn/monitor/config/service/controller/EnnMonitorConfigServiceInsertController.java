package enn.monitor.config.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;

@Controller
@RequestMapping(value = "service/insert")
public class EnnMonitorConfigServiceInsertController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceClient client = new EnnMonitorConfigServiceClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service/EnnMonitorConfigServiceInsert");
        
        return mav;
    }
	
	@RequestMapping(value = "/insert")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceName = request.getParameter("serviceName");
		String belongToServiceLine = request.getParameter("belongToServiceLine");
		String token = request.getParameter("token");
		String owner = request.getParameter("owner");
		String guest = request.getParameter("guest");
		
		List<String> serviceList = new ArrayList<String>();
		
		EnnMonitorConfigServiceCreateRequest.Builder createRequestBuilder = EnnMonitorConfigServiceCreateRequest.newBuilder();
		
		EnnMonitorConfigServiceGetRequest.Builder requestBuilder = EnnMonitorConfigServiceGetRequest.newBuilder();
		EnnMonitorConfigServiceGetResponse responseBuilder = null;
		
		
		logger.info("insert");
		
		if (serviceName != null && serviceName.trim().equals("") == false &&
				belongToServiceLine != null && belongToServiceLine.trim().equals("") == false &&
				token != null && token.trim().equals("") == false &&
				owner != null && owner.trim().equals("") == false) {
			createRequestBuilder.setServiceName(serviceName);
			createRequestBuilder.setBelongToServiceLine(Long.parseLong(belongToServiceLine));
			createRequestBuilder.setToken(token);
			createRequestBuilder.setOwner(owner);
			
			if (guest != null && guest.trim().equals("") == false) {
				createRequestBuilder.addAllGuest(Arrays.asList(guest.split(",\\s")));
			}
			
			try {
				client.createService(createRequestBuilder.build());
				
				requestBuilder.setServiceName(serviceName);
				
				responseBuilder = client.getService(requestBuilder.build());
				for (EnnMonitorConfigServiceTable serviceTable : responseBuilder.getServiceTableList()) {
					serviceList.add(serviceTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				serviceList.add(e.getMessage());
			}
		} else {
			serviceList.add("serviceName or belongToServiceLine or token or owner is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceList", serviceList);
        mav.setViewName("service/EnnMonitorConfigServiceInsert");

        return mav;
    }
	
	public static void main(String[] args) throws Exception {
		String mat = "a, a, a";
		String[] as = null;
		as = mat.split(",\\s");
		
		for (int i = 0; i < as.length; ++i) {
			System.out.println(as[i]);
		}
	}

}
