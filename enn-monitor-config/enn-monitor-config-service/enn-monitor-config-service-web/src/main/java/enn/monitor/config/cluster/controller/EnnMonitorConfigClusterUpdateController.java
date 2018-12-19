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
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest;

@Controller
@RequestMapping(value = "cluster/update")
public class EnnMonitorConfigClusterUpdateController {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static EnnMonitorConfigClusterClient client = new EnnMonitorConfigClusterClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cluster/EnnMonitorConfigClusterUpdate");
        
        return mav;
    }
	
	@RequestMapping(value = "/update")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String clusterName = request.getParameter("clusterName");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		List<String> clusterList = new ArrayList<String>();
		
		EnnMonitorConfigClusterGetRequest.Builder requestBuilder = EnnMonitorConfigClusterGetRequest.newBuilder();
		EnnMonitorConfigClusterGetResponse responseBuilder = null;
		
		EnnMonitorConfigClusterUpdateRequest.Builder updateBuilder = EnnMonitorConfigClusterUpdateRequest.newBuilder();
		
		
		logger.info("search");
		
		if (id != null && id.trim().equals("") == false) {
			updateBuilder.setId(Long.parseLong(id));
			
			if (clusterName != null && clusterName.trim().equals("") == false) {
				updateBuilder.setClusterName(clusterName);
			}
			
			if (lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
				updateBuilder.setLastUpdateUser(lastUpdateUser);
			}
			
			try {
				client.updateCluster(updateBuilder.build());
				
				requestBuilder.setId(Long.parseLong(id));
				responseBuilder = client.getCluster(requestBuilder.build());
				for (EnnMonitorConfigClusterTable clusterTable : responseBuilder.getClusterTableList()) {
					clusterList.add(clusterTable.toString());
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				clusterList.add(e.getMessage());
			}
		} else {
			clusterList.add("id is null");
		}

        ModelAndView mav = new ModelAndView();
        mav.addObject("clusterList", clusterList);
        mav.setViewName("cluster/EnnMonitorConfigClusterUpdate");

        return mav;
    }

}
