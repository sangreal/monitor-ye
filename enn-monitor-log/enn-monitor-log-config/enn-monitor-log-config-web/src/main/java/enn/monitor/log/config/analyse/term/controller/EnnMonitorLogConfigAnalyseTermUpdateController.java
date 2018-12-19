package enn.monitor.log.config.analyse.term.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest;

@Controller
@RequestMapping(value = "analyseTerm/update")
public class EnnMonitorLogConfigAnalyseTermUpdateController {
	
	private static EnnMonitorLogConfigAnalyseTermClient client = new EnnMonitorLogConfigAnalyseTermClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());

	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermUpdate");

        return mav;
    }
	
	@RequestMapping(value = "/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String addTerm = request.getParameter("addTerm");
		String filterTerm = request.getParameter("filterTerm");
		String belongToServiceId = request.getParameter("belongToServiceId");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		String analyseTermDisplay = null;
		
		EnnMonitorLogConfigAnalyseTermUpdateRequest.Builder analyseTermTable = EnnMonitorLogConfigAnalyseTermUpdateRequest.newBuilder();
		
		if (id != null && id.trim().equals("") == false && 
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			analyseTermTable.setId(Long.parseLong(id));
			analyseTermTable.setLastUpdateUser(lastUpdateUser);
			
			if (addTerm != null) {
				analyseTermTable.setAddTerm(addTerm);
			}
			if (filterTerm != null) {
				analyseTermTable.setFilterTerm(filterTerm);
			}
			
			if (belongToServiceId != null && belongToServiceId.trim().equals("") == false) {
				analyseTermTable.setBelongToServiceId(Long.parseLong(belongToServiceId));
			}
			
			client.updateAnalyseTerm(analyseTermTable.build());
			Thread.sleep(100);
			analyseTermDisplay = client.getAnalyseTerm(EnnMonitorLogConfigAnalyseTermGetRequest.newBuilder().setId(analyseTermTable.getId()).build()).toString();
		} else {
			analyseTermDisplay = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("AnalyseTerm", analyseTermDisplay);
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermUpdate");

        return mav;
    }
	
}
