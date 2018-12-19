package enn.monitor.config.cluster.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.cluster.client.EnnMonitorConfigClusterClient;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;

@Controller
@RequestMapping(value = "cluster/insert")
public class EnnMonitorConfigClusterInsertController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigClusterClient client = new EnnMonitorConfigClusterClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cluster/EnnMonitorConfigClusterInsert");
        
        return mav;
    }
	
	@RequestMapping(value = "/insert")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String clusterName = request.getParameter("clusterName");
		String createUser = request.getParameter("createUser");
		
		List<String> clusterList = new ArrayList<String>();
		
		EnnMonitorConfigClusterCreateRequest.Builder createRequestBuilder = EnnMonitorConfigClusterCreateRequest.newBuilder();
		
		EnnMonitorConfigClusterGetRequest.Builder requestBuilder = EnnMonitorConfigClusterGetRequest.newBuilder();
		EnnMonitorConfigClusterGetResponse responseBuilder = null;
		
		
		logger.info("insert");
		
		if (clusterName != null && clusterName.trim().equals("") == false) {
			createRequestBuilder.setClusterName(clusterName);
			
			if (createUser != null && createUser.trim().equals("") == false) {
				createRequestBuilder.setCreateUser(createUser);
			}
			
			try {
				client.createCluster(createRequestBuilder.build());
				
				requestBuilder.setClusterName(clusterName);
				
				responseBuilder = client.getCluster(requestBuilder.build());
				for (EnnMonitorConfigClusterTable clusterTable : responseBuilder.getClusterTableList()) {
					clusterList.add(clusterTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				clusterList.add(e.getMessage());
			}
		} else {
			clusterList.add("clusterName or status is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("clusterList", clusterList);
        mav.setViewName("cluster/EnnMonitorConfigClusterInsert");

        return mav;
    }

}
