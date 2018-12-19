package enn.monitor.log.config.logformat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse;

@Controller
@RequestMapping(value = "logformat/delete")
public class EnnMonitorLogConfigLogformatDeleteController {
	
	private static EnnMonitorLogConfigLogformatClient client = new EnnMonitorLogConfigLogformatClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		EnnMonitorLogConfigLogformatDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false) {
			rsp = client.deleteLogformat(Long.parseLong(id));
			mav.addObject("response", rsp.toString());
		}

        mav.setViewName("logformat/EnnMonitorLogConfigLogformatDelete");

        return mav;
    }

}
