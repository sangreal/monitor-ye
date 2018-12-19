package enn.monitor.log.config.logformat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;

@Controller
@RequestMapping(value = "logformat/insert")
public class EnnMonitorLogConfigLogformatInsertController {
	
	private static EnnMonitorLogConfigLogformatClient client = new EnnMonitorLogConfigLogformatClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		String regex = request.getParameter("regex");
		String logformat = request.getParameter("logformat");
		String belongToServiceId = request.getParameter("belongToServiceId");
		
		List<String> logformatList = new ArrayList<String>();
		
		EnnMonitorLogConfigLogformatCreateRequest.Builder createRequest = EnnMonitorLogConfigLogformatCreateRequest.newBuilder();
		
		EnnMonitorLogConfigLogformatGetResponse logformatResponse = null;
		
		if (userid != null && userid.trim().equals("") == false &&
				regex != null && regex.trim().equals("") == false && 
				logformat != null && logformat.trim().equals("") == false && 
				belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
			
			createRequest.setCreateUser(userid);
			createRequest.setRegex(regex);
			createRequest.setLogformat(logformat);
			createRequest.setBelongToServiceId(Long.parseLong(belongToServiceId));
			
			client.createLogformat(createRequest.build());
			Thread.sleep(100);
			logformatResponse = client.getLogformat(EnnMonitorLogConfigLogformatGetRequest.newBuilder().setLastUpdateUser(userid).build());
			
			if (logformatResponse.getIsSuccess() == false) {
				logformatList.add(logformatResponse.toString());
			} else {
				for (EnnMonitorLogConfigLogformatTable logformatTable : logformatResponse.getLogformatList()) {
					logformatList.add(logformatTable.toString());
				}
			}
		} else {
			userid = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("logformatList", logformatList);
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatInsert");

        return mav;
    }

}
