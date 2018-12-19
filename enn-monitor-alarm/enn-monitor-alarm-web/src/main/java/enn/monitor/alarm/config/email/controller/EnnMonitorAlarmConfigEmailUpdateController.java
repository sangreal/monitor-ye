package enn.monitor.alarm.config.email.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.alarm.config.email.client.EnnMonitorAlarmConfigEmailClient;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest;
import enn.monitor.alarm.token.config.EnnMonitorAlarmConfig;

@Controller
@RequestMapping(value = "email/update")
public class EnnMonitorAlarmConfigEmailUpdateController {
	
	private static EnnMonitorAlarmConfigEmailClient client = new EnnMonitorAlarmConfigEmailClient(
			EnnMonitorAlarmConfig.getAlarmConfigHost(), EnnMonitorAlarmConfig.getAlarmConfigPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("email/EnnMonitorAlarmConfigEmailUpdate");

        return mav;
    }
	
	/*
	 		ID <input type="text" name="id"><br/>
			UserId <input type="text" name="userid"><br/>
			TokenName <input type="text" name="token"><br/>
			TokenStatus <input name="status" type="radio" value="0" />Running <input name="status" type="radio" value="1" />Stop <br/>
			Owner <input type="text" name="owner"><br/>
			GuestList <input type="text" name="guestList"><br/>
	 */
	
	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String groupName = request.getParameter("groupname");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		
		String emailDisplay = null;
		
		EnnMonitorAlarmConfigEmailUpdateRequest.Builder updateRequest = EnnMonitorAlarmConfigEmailUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false) {
			updateRequest.setId(Long.parseLong(id));
			
			if (groupName != null && groupName.trim().equals("") == false) {
				updateRequest.setGroupName(groupName);
			}
			
			if (name != null && name.trim().equals("") == false) {
				updateRequest.setName(name);
			}
			
			if (mail != null && mail.trim().equals("") == false) {
				updateRequest.setMail(mail);
			}
			
			updateRequest.setLastUpdateUser("micklongen");
			
			client.updateEnnMonitorAlarmConfigEmail(updateRequest.build());
			Thread.sleep(100);
			emailDisplay = client.getEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailGetRequest.newBuilder().setId(updateRequest.getId()).build()).toString();
		} else {
			emailDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("alarmlEmail", emailDisplay);
        mav.setViewName("email/EnnMonitorAlarmConfigEmailUpdate");

        return mav;
    }
	
}
