package enn.monitor.config.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import enn.monitor.config.EnnMonitorConfig;
import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse;

@Controller
@RequestMapping(value = "service/generatortoken")
public class EnnMonitorConfigTokenGeneratorController {
	
	private static EnnMonitorConfigServiceClient client = new EnnMonitorConfigServiceClient(
			EnnMonitorConfig.getHost(), EnnMonitorConfig.getPort());
	
	@RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ModelAndView mav = new ModelAndView();
        mav.setViewName("service/EnnMonitorConfigTokenGenerator");

        return mav;
    }
	
	@RequestMapping(value = "/generator")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		
		EnnMonitorConfigServiceTokenGeneratorResponse rsp = null;
		
		ModelAndView mav = new ModelAndView();
		
		if (userid != null && userid.trim().equals("") == false) {
			rsp = client.generateServiceToken(userid);
			if (rsp.getIsSuccess() == true) {
				mav.addObject("token", rsp.getToken());
			} else {
				mav.addObject("token", rsp.getError());
			}
		}

        mav.setViewName("service/EnnMonitorConfigTokenGenerator");

        return mav;
    }

}
