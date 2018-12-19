package enn.monitor.log.config.logformat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.normalizing.client.EnnMonitorLogNormalizingClient;
import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest;
import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse;

@Controller
@RequestMapping(value = "logformat/test")
public class EnnMonitorLogConfigLogformatTestController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorLogNormalizingClient client = new EnnMonitorLogNormalizingClient(
			EnnMonitorLogConfig.getLogformatHost(), EnnMonitorLogConfig.getLogformatPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatTest");
        
        return mav;
    }
	
	@RequestMapping(value = "/parse")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String regex = request.getParameter("regex");
		String logformat = request.getParameter("logformat");
		String log = request.getParameter("log");
		
		EnnMonitorLogNormalizingRequest.Builder requestBuilder = EnnMonitorLogNormalizingRequest.newBuilder();
		EnnMonitorLogNormalizingResponse responseBuilder = null;
		
		logger.info("parse");
		
		if (regex != null && regex.trim().equals("") == false) {
			requestBuilder.setRegex(regex);
		}
		
		if (logformat != null && logformat.trim().equals("") == false) {
			requestBuilder.setLogformat(logformat);
		}

		if (log != null && log.trim().equals("") == false) {
			requestBuilder.setLog(log);
		}
		
		try {
			responseBuilder = client.normalizing(requestBuilder.build());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("regex", regex);
        mav.addObject("logformat", logformat);
        mav.addObject("log", log);
        if (responseBuilder.getIsSuccess() == true) {
        	mav.addObject("lognormal", responseBuilder.getResult());
        } else {
        	mav.addObject("lognormal", responseBuilder.getError());
        }
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatTest");

        return mav;
    }

}
