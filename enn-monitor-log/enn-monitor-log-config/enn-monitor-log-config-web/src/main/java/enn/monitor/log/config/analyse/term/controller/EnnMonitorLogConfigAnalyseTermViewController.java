package enn.monitor.log.config.analyse.term.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;

@Controller
@RequestMapping(value = "analyseTerm/view")
public class EnnMonitorLogConfigAnalyseTermViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorLogConfigAnalyseTermClient client = new EnnMonitorLogConfigAnalyseTermClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String belongToServiceId = request.getParameter("belongToServiceId");
		
		List<EnnMonitorLogConfigAnalyseTermTable> analyseTermTableList = null;
		
		EnnMonitorLogConfigAnalyseTermGetRequest.Builder requestBuilder = EnnMonitorLogConfigAnalyseTermGetRequest.newBuilder();
		EnnMonitorLogConfigAnalyseTermGetResponse responseBuilder = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
			requestBuilder.setBelongToServiceId(Long.parseLong(belongToServiceId));
		}
		
		try {
			responseBuilder = client.getAnalyseTerm(requestBuilder.build());
			if (responseBuilder.getAnalyseTermTableList() != null && responseBuilder.getAnalyseTermTableList().size() > 0) {
				analyseTermTableList = responseBuilder.getAnalyseTermTableList();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("analyseTermTableList", analyseTermTableList);
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermView");

        return mav;
    }

}
