package enn.monitor.config.cluster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.cluster.client.EnnMonitorConfigClusterClient;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse;

@Controller
@RequestMapping(value = "cluster/delete")
public class EnnMonitorConfigClusterDeleteController {

	private static EnnMonitorConfigClusterClient client = new EnnMonitorConfigClusterClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cluster/EnnMonitorConfigClusterDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		EnnMonitorConfigClusterDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false &&
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			rsp = client.deleteCluster(Long.parseLong(id), lastUpdateUser);
			mav.addObject("response",  rsp.toString());
		}

        mav.setViewName("cluster/EnnMonitorConfigClusterDelete");

        return mav;
    }

}
