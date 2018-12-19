package enn.monitor.log.config.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;

@Controller
@RequestMapping(value = "template/insert")
public class EnnMonitorLogConfigTemplateInsertController {
	
	private static EnnMonitorLogConfigTemplateClient client = new EnnMonitorLogConfigTemplateClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("template/EnnMonitorLogConfigTemplateInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		String templateKey = request.getParameter("templateKey");
		String belongToParentTemplate = request.getParameter("belongToParentTemplate");
		String belongToServiceId = request.getParameter("belongToServiceId");
		String firstEventId = request.getParameter("firstEventId");
		String secondEvent = request.getParameter("secondEvent");
		
		List<String> templateList = new ArrayList<String>();
		
		EnnMonitorLogConfigTemplateCreateRequest.Builder createRequest = EnnMonitorLogConfigTemplateCreateRequest.newBuilder();
		EnnMonitorLogConfigTemplateGetResponse templateResponse = null;
		
		if (userid != null && userid.trim().equals("") == false &&
				templateKey != null && templateKey.trim().equals("") == false &&
				belongToParentTemplate != null && belongToParentTemplate.trim().equals("") == false &&
				belongToServiceId != null && belongToServiceId.trim().equals("") == false &&
				firstEventId != null && firstEventId.trim().equals("") == false &&
				secondEvent != null && secondEvent.trim().equals("") == false) {
			
			createRequest.setCreateUser(userid);
			createRequest.setTemplateKey(templateKey);
			createRequest.setBelongToParentTemplate(belongToParentTemplate);
			createRequest.setBelongToServiceId(Long.parseLong(belongToServiceId));
			createRequest.setFirstEventId(Long.parseLong(firstEventId));
			createRequest.setSecondEvent(secondEvent);
			
			client.createTemplate(createRequest.build());
			Thread.sleep(100);
			templateResponse = client.getTemplate(EnnMonitorLogConfigTemplateGetRequest.newBuilder().setLastUpdateUser(userid).build());
			
			if (templateResponse.getIsSuccess() == false) {
				templateList.add(templateResponse.toString());
			} else {
				for (EnnMonitorLogConfigTemplateTable templateTable : templateResponse.getTemplateTableList()) {
					templateList.add(templateTable.toString());
				}
			}
		} else {
			userid = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("templateList", templateList);
        mav.setViewName("template/EnnMonitorLogConfigTemplateInsert");

        return mav;
    }

}
