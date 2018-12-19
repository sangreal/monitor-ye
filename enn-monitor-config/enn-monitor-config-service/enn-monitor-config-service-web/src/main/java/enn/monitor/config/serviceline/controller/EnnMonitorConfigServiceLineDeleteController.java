package enn.monitor.config.serviceline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse;
import enn.monitor.config.serviceline.client.EnnMonitorConfigServiceLineClient;

@Controller
@RequestMapping(value = "serviceLine/delete")
public class EnnMonitorConfigServiceLineDeleteController {

	private static EnnMonitorConfigServiceLineClient client = new EnnMonitorConfigServiceLineClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineDelete");

        return mav;
    }
	
	@RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String lastUpdateUser = request.getParameter("lastUpdateUser");
		
		EnnMonitorConfigServiceLineDeleteResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (id != null && id.trim().equals("") == false &&
				lastUpdateUser != null && lastUpdateUser.trim().equals("") == false) {
			rsp = client.deleteServiceLine(Long.parseLong(id), lastUpdateUser);
			mav.addObject("response",  rsp.toString());
		}

        mav.setViewName("serviceLine/EnnMonitorConfigServiceLineDelete");

        return mav;
    }

}
