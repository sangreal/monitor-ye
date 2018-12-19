package enn.monitor.log.config.template.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest;

@Controller
@RequestMapping(value = "template/update")
public class EnnMonitorLogConfigTemplateUpdateController {
	
	private static EnnMonitorLogConfigTemplateClient client = new EnnMonitorLogConfigTemplateClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("template/EnnMonitorLogConfigTemplateUpdate");

        return mav;
    }
	
	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String templateKey = request.getParameter("templateKey");
		String belongToParentTemplate = request.getParameter("belongToParentTemplate");
		String belongToServiceId = request.getParameter("belongToServiceId");
		String firstEventId = request.getParameter("firstEventId");
		String secondEvent = request.getParameter("secondEvent");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		String templateDisplay = null;
		
		EnnMonitorLogConfigTemplateUpdateRequest.Builder templateTable = EnnMonitorLogConfigTemplateUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false && 
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			templateTable.setId(Long.parseLong(id));
			templateTable.setLastUpdateUser(lastUpdateUser);
			
			if (templateKey != null) {
				templateTable.setTemplateKey(templateKey);
			}
			
			if (belongToParentTemplate != null && belongToParentTemplate.trim().equals("") == false) {
				templateTable.setBelongToParentTemplate(belongToParentTemplate);
			}
			
			if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
				templateTable.setBelongToServiceId(Long.parseLong(belongToServiceId));
			}
			
			if (firstEventId != null && firstEventId.trim().equals("") == false) {
				templateTable.setFirstEventId(Long.parseLong(firstEventId));
			}
			
			if (secondEvent != null && secondEvent.trim().equals("") == false) {
				templateTable.setSecondEvent(secondEvent);
			}
			
			client.updateTemplate(templateTable.build());
			Thread.sleep(100);
			templateDisplay = client.getTemplate(EnnMonitorLogConfigTemplateGetRequest.newBuilder().setId(templateTable.getId()).build()).toString();
		} else {
			templateDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("Template", templateDisplay);
        mav.setViewName("template/EnnMonitorLogConfigTemplateUpdate");

        return mav;
    }
	
}
