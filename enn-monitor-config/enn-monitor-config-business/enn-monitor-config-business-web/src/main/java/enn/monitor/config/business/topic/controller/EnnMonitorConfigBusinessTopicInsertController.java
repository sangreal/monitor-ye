package enn.monitor.config.business.topic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.business.EnnMonitorConfigBusiness;
import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse;

@Controller
@RequestMapping(value = "topic/insert")
public class EnnMonitorConfigBusinessTopicInsertController {
	
	private static EnnMonitorConfigBusinessTopicClient client = new EnnMonitorConfigBusinessTopicClient(
			EnnMonitorConfigBusiness.getHost(), EnnMonitorConfigBusiness.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userid");
		String source = request.getParameter("source");
		String topic = request.getParameter("topic");
		
		EnnMonitorConfigBusinessTopicCreateRequest.Builder requestBuidler = EnnMonitorConfigBusinessTopicCreateRequest.newBuilder();
		EnnMonitorConfigBusinessTopicGetResponse topicResponse = null;
		
		requestBuidler.setCreateUser(userId);
		requestBuidler.setSource(source);
		requestBuidler.setTopic(topic);
		
		client.createTopic(requestBuidler.build());
		Thread.sleep(100);
		topicResponse = client.getTopic(EnnMonitorConfigBusinessTopicGetRequest.newBuilder().setCreateUser(userId).build());

        ModelAndView mav = new ModelAndView();
        mav.addObject("topicList", topicResponse.toString());
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicInsert");

        return mav;
    }
    
}
