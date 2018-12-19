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
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest;

@Controller
@RequestMapping(value = "service/update")
public class EnnMonitorConfigServiceUpdateController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceClient client = new EnnMonitorConfigServiceClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service/EnnMonitorConfigServiceUpdate");
        
        return mav;
    }
	
	@RequestMapping(value = "/update")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String serviceName = request.getParameter("serviceName");
		String belongToServiceLine = request.getParameter("belongToServiceLine");
		String status = request.getParameter("status");
		String token = request.getParameter("token");
		String owner = request.getParameter("owner");
		String guest = request.getParameter("guest");
		
		List<String> serviceList = new ArrayList<String>();
		
		EnnMonitorConfigServiceGetRequest.Builder requestBuilder = EnnMonitorConfigServiceGetRequest.newBuilder();
		EnnMonitorConfigServiceGetResponse responseBuilder = null;
		
		EnnMonitorConfigServiceUpdateRequest.Builder updateBuilder = EnnMonitorConfigServiceUpdateRequest.newBuilder();
		
		
		logger.info("update");
		
		if (id != null && id.trim().equals("") == false) {
			updateBuilder.setId(Long.parseLong(id));
			
			if (serviceName != null && serviceName.trim().equals("") == false) {
				updateBuilder.setServiceName(serviceName);
			}
			
			if (belongToServiceLine != null && belongToServiceLine.trim().equals("") == false) {
				updateBuilder.setBelongToServiceLine(Long.parseLong(belongToServiceLine));
			}
			
			if (status != null && status.trim().equals("") == false) {
				switch(status) {
				case "0":
					break;
				case "1":
					updateBuilder.setStatus(EnnMonitorConfigServiceStatus.ServiceRunning);
					break;
				case "2":
					updateBuilder.setStatus(EnnMonitorConfigServiceStatus.ServiceStop);
					break;
				default:
					break;
				}
			}
			
			if (token != null && token.trim().equals("") == false) {
				updateBuilder.setToken(token);
			}
			
			if (owner != null && owner.trim().equals("") == false) {
				updateBuilder.setOwner(owner);
			}
			
			if (guest != null && guest.trim().equals("") == false) {
				updateBuilder.addAllGuest(Arrays.asList(guest.split(",\\s")));
			}
			
			try {
				client.updateService(updateBuilder.build());
				
				requestBuilder.setId(Long.parseLong(id));
				responseBuilder = client.getService(requestBuilder.build());
				for (EnnMonitorConfigServiceTable serviceTable : responseBuilder.getServiceTableList()) {
					serviceList.add(serviceTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				serviceList.add(e.getMessage());
			}
		} else {
			serviceList.add("id is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceList", serviceList);
        mav.setViewName("service/EnnMonitorConfigServiceUpdate");

        return mav;
    }

}
