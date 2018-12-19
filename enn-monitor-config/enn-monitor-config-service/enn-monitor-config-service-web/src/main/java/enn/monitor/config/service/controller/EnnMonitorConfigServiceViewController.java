package enn.monitor.config.service.controller;

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
import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;

@Controller
@RequestMapping(value = "service/view")
public class EnnMonitorConfigServiceViewController {
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceClient client = new EnnMonitorConfigServiceClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service/EnnMonitorConfigServiceView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceName = request.getParameter("serviceName");
		String belongToServiceLine = request.getParameter("belongToServiceLine");
		String status = request.getParameter("status");
		String owner = request.getParameter("owner");
		String guest = request.getParameter("guest");
		
		List<String> serviceList = new ArrayList<String>();
		
		EnnMonitorConfigServiceGetRequest.Builder requestBuilder = EnnMonitorConfigServiceGetRequest.newBuilder();
		EnnMonitorConfigServiceGetResponse responseBuilder = null;
		
		logger.info("search");
		
		if (serviceName != null && serviceName.trim().equals("") == false) {
			requestBuilder.setServiceName(serviceName);
		}
		
		if (belongToServiceLine != null && belongToServiceLine.trim().equals("") == false) {
			requestBuilder.setBelongToServiceLine(Long.parseLong(belongToServiceLine));
		}
		
		if (status != null && status.trim().equals("") == false) {
			switch(status) {
			case "0":
				break;
			case "1":
				requestBuilder.setStatus(EnnMonitorConfigServiceStatus.ServiceRunning);
				break;
			case "2":
				requestBuilder.setStatus(EnnMonitorConfigServiceStatus.ServiceStop);
				break;
			default:
				break;
			}
		}
		
		if (owner != null && owner.trim().equals("") == false) {
			requestBuilder.setOwner(owner);
		}
		
		if (guest != null && guest.trim().equals("") == false) {
			requestBuilder.setGuest(guest);
		}
		
		try {
			responseBuilder = client.getService(requestBuilder.build());
			for (EnnMonitorConfigServiceTable serviceTable : responseBuilder.getServiceTableList()) {
				serviceList.add(serviceTable.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceList.add(e.getMessage());
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceList", serviceList);
        mav.setViewName("service/EnnMonitorConfigServiceView");

        return mav;
    }

}
