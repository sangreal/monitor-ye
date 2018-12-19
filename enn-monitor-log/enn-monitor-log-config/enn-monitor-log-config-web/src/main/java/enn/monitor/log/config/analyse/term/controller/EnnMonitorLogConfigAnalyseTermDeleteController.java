package enn.monitor.log.config.analyse.term.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse;

@Controller
@RequestMapping(value = "analyseTerm/delete")
public class EnnMonitorLogConfigAnalyseTermDeleteController {
	
	private static EnnMonitorLogConfigAnalyseTermClient client = new EnnMonitorLogConfigAnalyseTermClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		EnnMonitorLogConfigAnalyseTermDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false) {
			rsp = client.deleteAnalyseTerm(Long.parseLong(id));
			mav.addObject("response", rsp.toString());
		}

        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermDelete");

        return mav;
    }

}
