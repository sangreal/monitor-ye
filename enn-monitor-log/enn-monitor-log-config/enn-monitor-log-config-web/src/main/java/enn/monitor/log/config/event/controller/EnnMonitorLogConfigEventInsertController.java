package enn.monitor.log.config.event.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;

@Controller
@RequestMapping(value = "event/insert")
public class EnnMonitorLogConfigEventInsertController {
	
	private static EnnMonitorLogConfigEventClient client = new EnnMonitorLogConfigEventClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("event/EnnMonitorLogConfigEventInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		String eventName = request.getParameter("eventName");
		
		List<String> eventList = new ArrayList<String>();
		
		EnnMonitorLogConfigEventCreateRequest.Builder createRequest = EnnMonitorLogConfigEventCreateRequest.newBuilder();
		EnnMonitorLogConfigEventGetResponse eventResponse = null;
		
		if (userid != null && userid.trim().equals("") == false &&
				eventName != null && eventName.trim().equals("") == false) {
			
			createRequest.setCreateUser(userid);
			createRequest.setEventName(eventName);
			
			client.createEvent(createRequest.build());
			Thread.sleep(100);
			eventResponse = client.getEvent(EnnMonitorLogConfigEventGetRequest.newBuilder().setLastUpdateUser(userid).build());
			
			if (eventResponse.getIsSuccess() == false) {
				eventList.add(eventResponse.toString());
			} else {
				for (EnnMonitorLogConfigEventTable eventTable : eventResponse.getEventTableList()) {
					eventList.add(eventTable.toString());
				}
			}
		} else {
			userid = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("eventList", eventList);
        mav.setViewName("event/EnnMonitorLogConfigEventInsert");

        return mav;
    }

}
