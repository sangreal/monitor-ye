package enn.monitor.alarm.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.ticket.grpc.client.EnnMonitorAlarmTicketGrpcClient;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketLevel;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketStatus;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "ticket/insert")
public class EnnMonitorAlarmTicketInsertController {
	
	private static EnnMonitorAlarmTicketGrpcClient client = new EnnMonitorAlarmTicketGrpcClient(
			EnnMonitorAlarmConfig.getAlarmTicketHost(), EnnMonitorAlarmConfig.getAlarmTicketPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ticket/EnnMonitorAlarmTicketInsert");

        return mav;
    }
	
	/*
	 	User ID <input type="text" name="userid"><br/>
		Ticket Status <input name="status" type="radio" value="0" checked/>WaitingAutoRecover <input name="status" type="radio" value="1" />WaitingNotify <input name="status" type="radio" value="2" />WaitingResolved <input name="status" type="radio" value="3" />Resolved <br/>
		Ticket Level <input name="status" type="radio" value="0" checked/>Warning <input name="status" type="radio" value="1" />Critical <br/>
		Automation <input name="automation" type="radio" value="0" checked/>No <input name="automation" type="radio" value="1" />Yes <br/>
		GroupName <input type="text" name="groupname"><br/>
		ClusterName <input type="text" name="clustername"><br/>
		Ip <input type="text" name="ip"><br/>
		NameSpace <input type="text" name="namespace"><br/>
		PodName <input type="text" name="podname"><br/>
		AppName <input type="text" name="appname"><br/>
		Error <input type="text" name="error"><br/>
		ErrorReason <input type="text" name="errorreason"><br/>
		Remark <input type="text" name="remark"><br/>
	 */
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userid");
		String status = request.getParameter("status");
		String level = request.getParameter("level");
		String automation = request.getParameter("automation");
		String groupname = request.getParameter("groupname");
		String clustername = request.getParameter("clustername");
		String ip = request.getParameter("ip");
		String namespace = request.getParameter("namespace");
		String podname = request.getParameter("podname");
		String appname = request.getParameter("appname");
		String error = request.getParameter("error");
		String errorreason = request.getParameter("errorreason");
		String remark = request.getParameter("remark");
		
		EnnMonitorAlarmTicketCreateRequest.Builder requestBuilder = EnnMonitorAlarmTicketCreateRequest.newBuilder();
		EnnMonitorAlarmTicketGetRequest.Builder getRequestBuilder = EnnMonitorAlarmTicketGetRequest.newBuilder();
		EnnMonitorAlarmTicketGetResponse getAlarmTicketResponse = null;
		
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
		
		switch (level) {
		case "0":
			requestBuilder.setEnnMonitorAlarmTicketLevel(EnnMonitorAlarmTicketLevel.Warning);
			break;
		case "1":
			requestBuilder.setEnnMonitorAlarmTicketLevel(EnnMonitorAlarmTicketLevel.Critical);
			break;
		}
		
		if (automation.equals("1") == true) {
			requestBuilder.setAutomation(true);
		}
		
		requestBuilder.setGrourName(groupname);
		requestBuilder.setClusterName(clustername);
		requestBuilder.setIp(ip);
		requestBuilder.setNameSpace(namespace);
		requestBuilder.setPodName(podname);
		requestBuilder.setAppName(appname);
		requestBuilder.setError(error);
		requestBuilder.setErrorReason(errorreason);
		requestBuilder.setRemark(remark);
		requestBuilder.setCreateUser(userId);
		
		requestBuilder.setTicketKey(generateKey(requestBuilder.build()));
		
		client.createEnnMonitorAlarmTicket(requestBuilder.build());
		
		getRequestBuilder.setGrourName(groupname);
		getAlarmTicketResponse = client.getEnnMonitorAlarmTicket(getRequestBuilder.build());
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmTicketList", getAlarmTicketResponse.getEnnMonitorAlarmTicketTableListList());
        mav.setViewName("ticket/EnnMonitorAlarmTicketInsert");

        return mav;
    }
	
	private String generateKey(EnnMonitorAlarmTicketCreateRequest request) throws Exception {
		String key = "web-test-" + request.getClusterName() + "-";

		if (request.getIp() != null && request.getIp().equals("") == false) {
			key = key + request.getIp() + "-";
		}
		
		if (request.getAppName() != null && request.getAppName().equals("") == false) {
			key = key + request.getAppName() + "-";
		}
		
		if (request.getNameSpace() != null && request.getNameSpace().equals("") == false) {
			key = key + request.getNameSpace() + "-";
		}
		
		if (request.getPodName() != null && request.getPodName().equals("") == false) {
			key = key + request.getPodName() + "-";
		}
		
		key = key + request.getEnnMonitorAlarmTicketLevel().name() + "-" + System.currentTimeMillis();

		return key;
	}

}
