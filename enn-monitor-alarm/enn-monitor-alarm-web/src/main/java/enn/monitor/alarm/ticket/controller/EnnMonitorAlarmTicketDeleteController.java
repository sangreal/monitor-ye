package enn.monitor.alarm.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "ticket/delete")
public class EnnMonitorAlarmTicketDeleteController {
	
	private static EnnMonitorAlarmTicketGrpcClient client = new EnnMonitorAlarmTicketGrpcClient(
			EnnMonitorAlarmConfig.getAlarmTicketHost(), EnnMonitorAlarmConfig.getAlarmTicketPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/EnnMonitorAlarmTicketDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ticketDisplay = null;
		
		String id = request.getParameter("id");
		
		ModelAndView mav = new ModelAndView();
		
		client.deleteEnnMonitorAlarmTicket(EnnMonitorAlarmTicketDeleteRequest.newBuilder().setId(Long.parseLong(id)).build());
		Thread.sleep(1000);
		ticketDisplay = client.getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest.newBuilder().setId(Long.parseLong(id)).build()).toString();
		
		mav.addObject("alarmlTicket", ticketDisplay);

        mav.setViewName("ticket/EnnMonitorAlarmTicketDelete");

        return mav;
    }

}
