package enn.monitor.log.config.event.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;

@Controller
@RequestMapping(value = "event/view")
public class EnnMonitorLogConfigEventViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorLogConfigEventClient client = new EnnMonitorLogConfigEventClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("event/EnnMonitorLogConfigEventView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		List<EnnMonitorLogConfigEventTable> eventTableList = null;
		
		EnnMonitorLogConfigEventGetRequest.Builder requestBuilder = EnnMonitorLogConfigEventGetRequest.newBuilder();
		EnnMonitorLogConfigEventGetResponse responseBuilder = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		try {
			responseBuilder = client.getEvent(requestBuilder.build());
			if (responseBuilder.getEventTableList() != null && responseBuilder.getEventTableList().size() > 0) {
				eventTableList = responseBuilder.getEventTableList();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("eventTableList", eventTableList);
        mav.setViewName("event/EnnMonitorLogConfigEventView");

        return mav;
    }

}
