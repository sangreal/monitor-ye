package enn.monitor.config.business.topic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.business.EnnMonitorConfigBusiness;
import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest;

@Controller
@RequestMapping(value = "topic/update")
public class EnnMonitorConfigBusinessTopicUpdateController {
	
	private static EnnMonitorConfigBusinessTopicClient client = new EnnMonitorConfigBusinessTopicClient(
			EnnMonitorConfigBusiness.getHost(), EnnMonitorConfigBusiness.getPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicUpdate");

        return mav;
    }

	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String source = request.getParameter("source");
		String topic = request.getParameter("topic");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		String topicDisplay = null;
		
		EnnMonitorConfigBusinessTopicUpdateRequest.Builder topicTable = EnnMonitorConfigBusinessTopicUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false &&
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			topicTable.setId(Long.parseLong(id));
			topicTable.setLastUpdateUser(lastUpdateUser);
			
			if (source != null && source.trim().equals("") == false) {
				topicTable.setSource(source);
			}
			if (topic != null && topic.trim().equals("") == false) {
				topicTable.setTopic(topic);
			}
			
			client.updateTopic(topicTable.build());
			Thread.sleep(100);
			topicDisplay = client.getTopic(EnnMonitorConfigBusinessTopicGetRequest.newBuilder().setId(topicTable.getId()).build()).toString();
		} else {
			topicDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("Topic", topicDisplay);
        mav.setViewName("topic/EnnMonitorConfigBusinessTopicUpdate");

        return mav;
    }
	
}
