package enn.monitor.alarm.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "ticket/update")
public class EnnMonitorAlarmTicketUpdateController {
	
	private static EnnMonitorAlarmTicketGrpcClient client = new EnnMonitorAlarmTicketGrpcClient(
			EnnMonitorAlarmConfig.getAlarmTicketHost(), EnnMonitorAlarmConfig.getAlarmTicketPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/EnnMonitorAlarmTicketUpdate");

        return mav;
    }

	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String userId = request.getParameter("userid");
		String status = request.getParameter("status");
		String groupname = request.getParameter("groupname");
		String error = request.getParameter("error");
		String errorreason = request.getParameter("errorreason");
		String remark = request.getParameter("remark");
		
		String ticketDisplay = null;
		
		EnnMonitorAlarmTicketUpdateRequest.Builder updateRequest = EnnMonitorAlarmTicketUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false) {
			updateRequest.setId(Long.parseLong(id));
			
			switch (status) {
			case "0":
				updateRequest.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingAutoRecover);
				break;
			case "1":
				updateRequest.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingNotify);
				break;
			case "2":
				updateRequest.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingReceive);
				break;
			case "3":
				updateRequest.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.WaitingResolve);
				break;
			case "4":
				updateRequest.setEnnMonitorAlarmTicketStatus(EnnMonitorAlarmTicketStatus.Resolved);
				break;
			}

			if (groupname != null && groupname.equals("") == false) {
				updateRequest.setGrourName(groupname);
			}
			
			if (error != null && error.equals("") == false) {
				updateRequest.setError(error);
			}
			
			if (errorreason != null && errorreason.equals("") == false) {
				updateRequest.setErrorReason(errorreason);
			}
			
			if (remark != null && remark.equals("") == false) {
				updateRequest.setRemark(remark);
			}
			
			if (userId != null && userId.equals("") == false) {
				updateRequest.setLastUpdateUser(userId);
			}
			
			client.updateEnnMonitorAlarmTicket(updateRequest.build());
			Thread.sleep(100);
			ticketDisplay = client.getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest.newBuilder().setId(updateRequest.getId()).build()).toString();
		} else {
			ticketDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmlTicket", ticketDisplay);
        mav.setViewName("ticket/EnnMonitorAlarmTicketUpdate");

        return mav;
    }
	
}
