package enn.monitor.config.business.topic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.business.EnnMonitorConfigBusiness;
import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse;

@Controller
@RequestMapping(value = "topic/delete")
public class EnnMonitorConfigBusinessTopicDeleteController {
	
	private static EnnMonitorConfigBusinessTopicClient client = new EnnMonitorConfigBusinessTopicClient(
			EnnMonitorConfigBusiness.getHost(), EnnMonitorConfigBusiness.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		EnnMonitorConfigBusinessTopicDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false) {
			rsp = client.deleteTopic(Long.parseLong(id));
			mav.addObject("response",  rsp.toString());
		}

        mav.setViewName("topic/EnnMonitorConfigBusinessTopicDelete");

        return mav;
    }

}
