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
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineStatus;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceline.client.EnnMonitorConfigServiceLineClient;

@Controller
@RequestMapping(value = "serviceLine/view")
public class EnnMonitorConfigServiceLineViewController {
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigServiceLineClient client = new EnnMonitorConfigServiceLineClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String serviceLineName = request.getParameter("serviceLineName");
		String status = request.getParameter("status");
		String belongToCluster = request.getParameter("belongToCluster");
		
		
		List<String> serviceLineList = new ArrayList<String>();
		
		EnnMonitorConfigServiceLineGetRequest.Builder requestBuilder = EnnMonitorConfigServiceLineGetRequest.newBuilder();
		EnnMonitorConfigServiceLineGetResponse responseBuilder = null;
		
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		if (serviceLineName != null && serviceLineName.trim().equals("") == false) {
			requestBuilder.setServiceLineName(serviceLineName);
		}
		
		if (status != null && status.trim().equals("") == false) {
			switch(status) {
			case "0":
				break;
			case "1":
				requestBuilder.setStatus(EnnMonitorConfigServiceLineStatus.ServiceLineRunning);
				break;
			case "2":
				requestBuilder.setStatus(EnnMonitorConfigServiceLineStatus.ServiceLineDeleting);
				break;
			default:
				break;
			}
		}
		
		if (belongToCluster != null && belongToCluster.trim().equals("") == false) {
			requestBuilder.setBelongToCluster(Long.parseLong(belongToCluster));
		}
		
		try {
			responseBuilder = client.getServiceLine(requestBuilder.build());
			for (EnnMonitorConfigServiceLineTable serviceLineTable : responseBuilder.getServiceLineTableList()) {
				serviceLineList.add(serviceLineTable.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			serviceLineList.add(e.getMessage());
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("serviceLineList", serviceLineList);
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineView");

        return mav;
    }

}
