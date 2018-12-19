package enn.monitor.log.config.logformat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;

@Controller
@RequestMapping(value = "logformat/view")
public class EnnMonitorLogConfigLogformatViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorLogConfigLogformatClient client = new EnnMonitorLogConfigLogformatClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String belongToServiceId = request.getParameter("belongToServiceId");
		
		String regex = null;
		String logformat = null;
		
		EnnMonitorLogConfigLogformatTable logformatTable = null;
		List<EnnMonitorLogConfigLogformatTable> logformatTableList = null;
		
		EnnMonitorLogConfigLogformatGetRequest.Builder requestBuilder = EnnMonitorLogConfigLogformatGetRequest.newBuilder();
		EnnMonitorLogConfigLogformatGetResponse responseBuilder = null;
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			requestBuilder.setId(Long.parseLong(id));
		}
		
		if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
			requestBuilder.setBelongToServiceId(Long.parseLong(belongToServiceId));
		}
		
		try {
			responseBuilder = client.getLogformat(requestBuilder.build());
			if (responseBuilder.getLogformatList() != null && responseBuilder.getLogformatList().size() > 0) {
				logformatTableList = new ArrayList<EnnMonitorLogConfigLogformatTable>();
				
				for (int i = 0; i < responseBuilder.getLogformatList().size(); ++i) {
					logformatTable = responseBuilder.getLogformatList().get(i);
					
					regex = logformatTable.getRegex();
					regex = regex.replaceAll("<", "&lt");
					regex = regex.replaceAll(">", "&gt");
					
					logformat = logformatTable.getLogformat();
					logformat = logformat.replaceAll("<", "&lt");
					logformat = logformat.replaceAll(">", "&gt");
					
					logformatTable = EnnMonitorLogConfigLogformatTable.newBuilder(logformatTable).setRegex(regex).setLogformat(logformat).build();
					logformatTableList.add(logformatTable);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("logformatTableList", logformatTableList);
        mav.setViewName("logformat/EnnMonitorLogConfigLogformatView");

        return mav;
    }

}
