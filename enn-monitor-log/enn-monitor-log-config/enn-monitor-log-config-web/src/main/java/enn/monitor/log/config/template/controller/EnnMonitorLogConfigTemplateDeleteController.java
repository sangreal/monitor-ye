package enn.monitor.log.config.template.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse;

@Controller
@RequestMapping(value = "template/delete")
public class EnnMonitorLogConfigTemplateDeleteController {
	
	private static EnnMonitorLogConfigTemplateClient client = new EnnMonitorLogConfigTemplateClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("template/EnnMonitorLogConfigTemplateDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		EnnMonitorLogConfigTemplateDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false) {
			rsp = client.deleteTemplate(Long.parseLong(id));
			mav.addObject("response", rsp.toString());
		}

        mav.setViewName("template/EnnMonitorLogConfigTemplateDelete");

        return mav;
    }

}
