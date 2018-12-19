package enn.monitor.config.serviceline.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceline.client.EnnMonitorConfigServiceLineClient;

@Controller
@RequestMapping(value = "serviceLine/insert")
public class EnnMonitorConfigServiceLineInsertController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceLineClient client = new EnnMonitorConfigServiceLineClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineInsert");
        
        return mav;
    }
	
	@RequestMapping(value = "/insert")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceLineName = request.getParameter("serviceLineName");
		String belongToCluster = request.getParameter("belongToCluster");
		String createUser = request.getParameter("createUser");
		
		List<String> serviceLineList = new ArrayList<String>();
		
		EnnMonitorConfigServiceLineCreateRequest.Builder createRequestBuilder = EnnMonitorConfigServiceLineCreateRequest.newBuilder();
		
		EnnMonitorConfigServiceLineGetRequest.Builder requestBuilder = EnnMonitorConfigServiceLineGetRequest.newBuilder();
		EnnMonitorConfigServiceLineGetResponse responseBuilder = null;
		
		
		logger.info("insert");
		
		if (serviceLineName != null && serviceLineName.trim().equals("") == false &&
				belongToCluster != null && belongToCluster.trim().equals("") == false &&
				createUser != null && createUser.trim().equals("") == false) {
			createRequestBuilder.setServiceLineName(serviceLineName);
			createRequestBuilder.setBelongToCluster(Long.parseLong(belongToCluster));
			createRequestBuilder.setCreateUser(createUser);
			
			try {
				client.createServiceLine(createRequestBuilder.build());
				
				requestBuilder.setServiceLineName(serviceLineName);
				
				responseBuilder = client.getServiceLine(requestBuilder.build());
				for (EnnMonitorConfigServiceLineTable serviceLineTable : responseBuilder.getServiceLineTableList()) {
					serviceLineList.add(serviceLineTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				serviceLineList.add(e.getMessage());
			}
		} else {
			serviceLineList.add("serviceLineName is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceLineList", serviceLineList);
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineInsert");

        return mav;
    }

}
