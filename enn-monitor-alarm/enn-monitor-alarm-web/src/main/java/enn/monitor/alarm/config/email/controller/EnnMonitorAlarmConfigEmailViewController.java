package enn.monitor.alarm.config.email.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "email/view")
public class EnnMonitorAlarmConfigEmailViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorAlarmConfigEmailClient client = new EnnMonitorAlarmConfigEmailClient(
			EnnMonitorAlarmConfig.getAlarmConfigHost(), EnnMonitorAlarmConfig.getAlarmConfigPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("email/EnnMonitorAlarmConfigEmailView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String groupName = request.getParameter("groupname");
		String name = request.getParameter("name");
		
		List<EnnMonitorAlarmConfigEmailTable> alarmConfigEmailList = new ArrayList<EnnMonitorAlarmConfigEmailTable>();
		
		EnnMonitorAlarmConfigEmailGetRequest.Builder requestBuilder = EnnMonitorAlarmConfigEmailGetRequest.newBuilder();
		EnnMonitorAlarmConfigEmailGetResponse alarmConfigEmailResponse = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		if (groupName != null && groupName.trim().equals("") == false) {
			requestBuilder.setGroupName(groupName);
		}
		
		if (name != null && name.trim().equals("") == false) {
			requestBuilder.setName(name);
		}
		
		alarmConfigEmailResponse = client.getEnnMonitorAlarmConfigEmail(requestBuilder.build());
		if (alarmConfigEmailResponse.getIsSuccess() == true && alarmConfigEmailResponse.getEnnMonitorAlarmConfigEmailTableListList().size() > 0) {
			alarmConfigEmailList.addAll(alarmConfigEmailResponse.getEnnMonitorAlarmConfigEmailTableListList());
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmEmailList", alarmConfigEmailList);
        mav.setViewName("email/EnnMonitorAlarmConfigEmailView");

        return mav;
    }

}
