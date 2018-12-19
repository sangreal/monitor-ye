package enn.monitor.log.config.logformat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest;

@Controller
@RequestMapping(value = "logformat/update")
public class EnnMonitorLogConfigLogformatUpdateController {
	
	private static EnnMonitorLogConfigLogformatClient client = new EnnMonitorLogConfigLogformatClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatUpdate");

        return mav;
    }
	
	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String regex = request.getParameter("regex");
		String logformat = request.getParameter("logformat");
		String belongToServiceId = request.getParameter("belongToServiceId");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		String logformatDisplay = null;
		
		EnnMonitorLogConfigLogformatUpdateRequest.Builder logformatTable = EnnMonitorLogConfigLogformatUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false && 
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			logformatTable.setId(Long.parseLong(id));
			logformatTable.setLastUpdateUser(lastUpdateUser);
			
			if (regex != null && regex.trim().equals("") == false) {
				logformatTable.setRegex(regex);
			}
			if (logformat != null && logformat.trim().equals("") == false) {
				logformatTable.setLogformat(logformat);
			}
			
			if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
				logformatTable.setBelongToServiceId(Long.parseLong(belongToServiceId));
			}
			
			client.updateLogformat(logformatTable.build());
			Thread.sleep(100);
			logformatDisplay = client.getLogformat(EnnMonitorLogConfigLogformatGetRequest.newBuilder().setId(logformatTable.getId()).build()).toString();
		} else {
			logformatDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("Logformat", logformatDisplay);
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatUpdate");

        return mav;
    }
	
}
