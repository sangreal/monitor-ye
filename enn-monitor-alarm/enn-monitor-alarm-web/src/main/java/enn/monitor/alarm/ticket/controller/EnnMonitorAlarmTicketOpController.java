package enn.monitor.alarm.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateEnum;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "ticket/op")
public class EnnMonitorAlarmTicketOpController {
	
	private static EnnMonitorAlarmTicketGrpcClient client = new EnnMonitorAlarmTicketGrpcClient(
			EnnMonitorAlarmConfig.getAlarmTicketHost(), EnnMonitorAlarmConfig.getAlarmTicketPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/EnnMonitorAlarmTicketOp");

        return mav;
    }
	
	@RequestMapping(value = "/resolved")
    public ModelAndView resolved(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ticketDisplay = null;
		
		String ticketKey = request.getParameter("ticketKey");
		
		
		ModelAndView mav = new ModelAndView();
		
		client.updateEnnMonitorAlarmTicketTransformState(
				EnnMonitorAlarmTicketTransformState.newBuilder().setTicketKey(ticketKey).setTicketTransformState(EnnMonitorAlarmTicketTransformStateEnum.ManualResolved).build());
		
		Thread.sleep(1000);
		ticketDisplay = client.getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest.newBuilder().setTicketKey(ticketKey).build()).toString();
		
		mav.addObject("alarmlTicket", ticketDisplay);

        mav.setViewName("ticket/EnnMonitorAlarmTicketOp");

        return mav;
    }
	
	@RequestMapping(value = "/received")
    public ModelAndView received(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ticketDisplay = null;
		
		String ticketKey = request.getParameter("ticketKey");
		
		ModelAndView mav = new ModelAndView();
		
		client.updateEnnMonitorAlarmTicketTransformState(
				EnnMonitorAlarmTicketTransformState.newBuilder().setTicketKey(ticketKey).setTicketTransformState(EnnMonitorAlarmTicketTransformStateEnum.Received).build());
		
		Thread.sleep(1000);
		ticketDisplay = client.getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest.newBuilder().setTicketKey(ticketKey).build()).toString();
		
		mav.addObject("alarmlTicket", ticketDisplay);

        mav.setViewName("ticket/EnnMonitorAlarmTicketOp");

        return mav;
    }

}
