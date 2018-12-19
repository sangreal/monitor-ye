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
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest;
import enn.monitor.config.serviceline.client.EnnMonitorConfigServiceLineClient;

@Controller
@RequestMapping(value = "serviceLine/update")
public class EnnMonitorConfigServiceLineUpdateController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceLineClient client = new EnnMonitorConfigServiceLineClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineUpdate");
        
        return mav;
    }
	
	@RequestMapping(value = "/update")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String serviceLineName = request.getParameter("serviceLineName");
		String belongToCluster = request.getParameter("belongToCluster");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		List<String> serviceLineList = new ArrayList<String>();
		
		EnnMonitorConfigServiceLineGetRequest.Builder requestBuilder = EnnMonitorConfigServiceLineGetRequest.newBuilder();
		EnnMonitorConfigServiceLineGetResponse responseBuilder = null;
		
		EnnMonitorConfigServiceLineUpdateRequest.Builder updateBuilder = EnnMonitorConfigServiceLineUpdateRequest.newBuilder();
		
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			updateBuilder.setId(Long.parseLong(id));
			
			if (serviceLineName != null && serviceLineName.trim().equals("") == false) {
				updateBuilder.setServiceLineName(serviceLineName);
			}
			
			if (belongToCluster != null && belongToCluster.trim().equals("") == false) {
				updateBuilder.setBelongToCluster(Long.parseLong(belongToCluster));
			}
			
			if (lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
				updateBuilder.setLastUpdateUser(lastUpdateUser);
			}
			
			try {
				client.updateServiceLine(updateBuilder.build());
				
				requestBuilder.setId(Long.parseLong(id));
				responseBuilder = client.getServiceLine(requestBuilder.build());
				for (EnnMonitorConfigServiceLineTable serviceLineTable : responseBuilder.getServiceLineTableList()) {
					serviceLineList.add(serviceLineTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				serviceLineList.add(e.getMessage());
			}
		} else {
			serviceLineList.add("id is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceLineList", serviceLineList);
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineUpdate");

        return mav;
    }

}
