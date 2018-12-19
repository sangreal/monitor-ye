package enn.monitor.alarm.ticket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "ticket/view")
public class EnnMonitorAlarmTicketViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorAlarmTicketGrpcClient client = new EnnMonitorAlarmTicketGrpcClient(
			EnnMonitorAlarmConfig.getAlarmTicketHost(), EnnMonitorAlarmConfig.getAlarmTicketPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/EnnMonitorAlarmTicketView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		String groupname = request.getParameter("groupname");
		
		List<EnnMonitorAlarmTicketTable> alarmTicketList = new ArrayList<EnnMonitorAlarmTicketTable>();
		
		EnnMonitorAlarmTicketGetRequest.Builder requestBuilder = EnnMonitorAlarmTicketGetRequest.newBuilder();
		EnnMonitorAlarmTicketGetResponse alarmTicketResponse = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		switch (status) {
		case "0":
			requestBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingAutoRecover);
			break;
		case "1":
			requestBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingNotify);
			break;
		case "2":
			requestBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingReceive);
			break;
		case "3":
			requestBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingResolve);
			break;
		case "4":
			requestBuilder.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.Resolved);
			break;
		}
		
		if (groupname != null && groupname.trim().equals("") == false) {
			requestBuilder.setGrourName(groupname);
		}
		
		alarmTicketResponse = client.getEnnMonitorAlarmTicket(requestBuilder.build());
		if (alarmTicketResponse.getIsSuccess() == true && alarmTicketResponse.getEnnMonitorAlarmTicketTableListList().size() > 0) {
			alarmTicketList.addAll(alarmTicketResponse.getEnnMonitorAlarmTicketTableListList());
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmTicketList", alarmTicketList);
        mav.setViewName("ticket/EnnMonitorAlarmTicketView");

        return mav;
    }

}
