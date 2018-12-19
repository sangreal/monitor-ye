package enn.monitor.alarm.config.email.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "email/delete")
public class EnnMonitorAlarmConfigEmailDeleteController {
	
	private static EnnMonitorAlarmConfigEmailClient client = new EnnMonitorAlarmConfigEmailClient(
			EnnMonitorAlarmConfig.getAlarmConfigHost(), EnnMonitorAlarmConfig.getAlarmConfigPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("email/EnnMonitorAlarmConfigEmailDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String emailDisplay = null;
		
		String id = request.getParameter("id");
		
		ModelAndView mav = new ModelAndView();
		
		client.deleteEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailDeleteRequest.newBuilder().setId(Long.parseLong(id)).build());
		Thread.sleep(1000);
		emailDisplay = client.getEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailGetRequest.newBuilder().setId(Long.parseLong(id)).build()).toString();
		
		mav.addObject("alarmlEmail", emailDisplay);

        mav.setViewName("email/EnnMonitorAlarmConfigEmailDelete");

        return mav;
    }

}
