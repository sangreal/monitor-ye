package enn.monitor.log.config.template.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;

@Controller
@RequestMapping(value = "template/view")
public class EnnMonitorLogConfigTemplateViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorLogConfigTemplateClient client = new EnnMonitorLogConfigTemplateClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("template/EnnMonitorLogConfigTemplateView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String belongToServiceId = request.getParameter("belongToServiceId");
		String belongToParentTemplate = request.getParameter("belongToParentTemplate");
		String firstEventId = request.getParameter("firstEventId");
		
		List<EnnMonitorLogConfigTemplateTable> templateTableList = null;
		
		EnnMonitorLogConfigTemplateGetRequest.Builder requestBuilder = EnnMonitorLogConfigTemplateGetRequest.newBuilder();
		EnnMonitorLogConfigTemplateGetResponse responseBuilder = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
			requestBuilder.setBelongToServiceId(Long.parseLong(belongToServiceId));
		}
		
		if (belongToParentTemplate != null && belongToParentTemplate.trim().equals("") == false) {
			requestBuilder.setBelongToParentTemplate(belongToParentTemplate);
		}
		
		if (firstEventId != null && firstEventId.trim().equals("") == false) {
			requestBuilder.setFirstEventId(Long.parseLong(firstEventId));
		}
		
		try {
			responseBuilder = client.getTemplate(requestBuilder.build());
			if (responseBuilder.getTemplateTableList() != null && responseBuilder.getTemplateTableList().size() > 0) {
				templateTableList = responseBuilder.getTemplateTableList();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("templateTableList", templateTableList);
        mav.setViewName("template/EnnMonitorLogConfigTemplateView");

        return mav;
    }

}
