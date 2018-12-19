package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.config.template.client.EnnMonitorLogConfigTemplateClient;

public class EnnMonitorLogConfigTemplateClientUtil {
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogConfigTemplateClient logConfigTemplateClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogConfigTemplateClient getEnnMonitorLogConfigTemplateClient() {
		if (logConfigTemplateClient == null) {
			synchronized (EnnMonitorLogConfigTemplateClientUtil.class) {
				if (logConfigTemplateClient == null) {
					logConfigTemplateClient = new EnnMonitorLogConfigTemplateClient(parameters.logConfigServer, parameters.logConfigPort);
				}
			}
		}
		return logConfigTemplateClient;
	}

}
