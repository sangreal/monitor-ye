package enn.monitor.log.config.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest;

@Controller
@RequestMapping(value = "event/update")
public class EnnMonitorLogConfigEventUpdateController {
	
	private static EnnMonitorLogConfigEventClient client = new EnnMonitorLogConfigEventClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("event/EnnMonitorLogConfigEventUpdate");

        return mav;
    }
	
	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String eventName = request.getParameter("eventName");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		String eventDisplay = null;
		
		EnnMonitorLogConfigEventUpdateRequest.Builder eventTable = EnnMonitorLogConfigEventUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false && 
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			eventTable.setId(Long.parseLong(id));
			eventTable.setLastUpdateUser(lastUpdateUser);
			
			if (eventName != null && eventName.trim().equals("") == false) {
				eventTable.setEventName(eventName);
			}
			
			client.updateEvent(eventTable.build());
			Thread.sleep(100);
			eventDisplay = client.getEvent(EnnMonitorLogConfigEventGetRequest.newBuilder().setId(eventTable.getId()).build()).toString();
		} else {
			eventDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("Event", eventDisplay);
        mav.setViewName("event/EnnMonitorLogConfigEventUpdate");

        return mav;
    }
	
}
