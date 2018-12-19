package enn.monitor.config.business.topic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.business.EnnMonitorConfigBusiness;
import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;

@Controller
@RequestMapping(value = "topic/view")
public class EnnMonitorConfigBusinessTopicViewController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigBusinessTopicClient client = new EnnMonitorConfigBusinessTopicClient(
			EnnMonitorConfigBusiness.getHost(), EnnMonitorConfigBusiness.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicView");
        
        return mav;
    }
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String userid = request.getParameter("userid");
		List<String> topicList = new ArrayList<String>();
		
		EnnMonitorConfigBusinessTopicGetRequest.Builder requestBuilder = EnnMonitorConfigBusinessTopicGetRequest.newBuilder();
		EnnMonitorConfigBusinessTopicGetResponse topicResponse = null;
		
		logger.info("search");
		
		if (userid != null && userid.trim().equals("") == false) {
			requestBuilder.setCreateUser(userid);
		}
		
		try {
			topicResponse = client.getTopic(EnnMonitorConfigBusinessTopicGetRequest.newBuilder().setCreateUser(userid).build());
			if (topicResponse.getIsSuccess() == false) {
				topicList.add(topicResponse.toString());
			} else {
				for (EnnMonitorConfigBusinessTopicTable topicTable : topicResponse.getTopicList()) {
					topicList.add(topicTable.toString());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("topicList", topicList);
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicView");

        return mav;
    }

}
