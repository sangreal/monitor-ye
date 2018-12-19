package enn.monitor.alarm.config.email.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "email/insert")
public class EnnMonitorAlarmConfigEmailInsertController {
	
	private static EnnMonitorAlarmConfigEmailClient client = new EnnMonitorAlarmConfigEmailClient(
			EnnMonitorAlarmConfig.getAlarmConfigHost(), EnnMonitorAlarmConfig.getAlarmConfigPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("email/EnnMonitorAlarmConfigEmailInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String groupName = request.getParameter("groupname");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		
		EnnMonitorAlarmConfigEmailCreateRequest.Builder requestBuilder = EnnMonitorAlarmConfigEmailCreateRequest.newBuilder();
		EnnMonitorAlarmConfigEmailGetRequest.Builder getRequestBuilder = EnnMonitorAlarmConfigEmailGetRequest.newBuilder();
		EnnMonitorAlarmConfigEmailGetResponse getAlarmConfigEmailResponse = null;
		
		requestBuilder.setGroupName(groupName);
		requestBuilder.setName(name);
		requestBuilder.setMail(mail);
		requestBuilder.setCreateUser("micklongen");
		
		client.createEnnMonitorAlarmConfigEmail(requestBuilder.build());
		
		getRequestBuilder.setGroupName(groupName);
		getAlarmConfigEmailResponse = client.getEnnMonitorAlarmConfigEmail(getRequestBuilder.build());
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmEmailList", getAlarmConfigEmailResponse.getEnnMonitorAlarmConfigEmailTableListList());
        mav.setViewName("email/EnnMonitorAlarmConfigEmailInsert");

        return mav;
    }

}
