package enn.monitor.log.config.analyse.term.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.log.config.EnnMonitorLogConfig;
import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;

@Controller
@RequestMapping(value = "analyseTerm/insert")
public class EnnMonitorLogConfigAnalyseTermInsertController {
	
	private static EnnMonitorLogConfigAnalyseTermClient client = new EnnMonitorLogConfigAnalyseTermClient(
			EnnMonitorLogConfig.getHost(), EnnMonitorLogConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermInsert");

        return mav;
    }
	
	@RequestMapping(value = "/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		String addTerm = request.getParameter("addTerm");
		String filterTerm = request.getParameter("filterTerm");
		String belongToServiceId = request.getParameter("belongToServiceId");
		
		List<String> analyseTermList = new ArrayList<String>();
		
		EnnMonitorLogConfigAnalyseTermCreateRequest.Builder createRequest = EnnMonitorLogConfigAnalyseTermCreateRequest.newBuilder();
		
		EnnMonitorLogConfigAnalyseTermGetResponse analyseTermResponse = null;
		
		if (userid != null && userid.trim().equals("") == false &&
				addTerm != null &&
				filterTerm != null) {
			
			createRequest.setCreateUser(userid);
			createRequest.setAddTerm(addTerm);
			createRequest.setFilterTerm(filterTerm);
			if (belongToServiceId != null && belongToServiceId.equals("") == false) {
				createRequest.setBelongToServiceId(Long.parseLong(belongToServiceId));
			} else {
				createRequest.setBelongToServiceId(-1l);
			}
			
			client.createAnalyseTerm(createRequest.build());
			Thread.sleep(100);
			analyseTermResponse = client.getAnalyseTerm(EnnMonitorLogConfigAnalyseTermGetRequest.newBuilder().setLastUpdateUser(userid).build());
			
			if (analyseTermResponse.getIsSuccess() == false) {
				analyseTermList.add(analyseTermResponse.toString());
			} else {
				for (EnnMonitorLogConfigAnalyseTermTable analyseTermTable : analyseTermResponse.getAnalyseTermTableList()) {
					analyseTermList.add(analyseTermTable.toString());
				}
			}
		} else {
			userid = "";
		}
		
        ModelAndView mav = new ModelAndView();
        mav.addObject("analyseTermList", analyseTermList);
        mav.setViewName("analyseTerm/EnnMonitorLogConfigAnalyseTermInsert");

        return mav;
    }

}
