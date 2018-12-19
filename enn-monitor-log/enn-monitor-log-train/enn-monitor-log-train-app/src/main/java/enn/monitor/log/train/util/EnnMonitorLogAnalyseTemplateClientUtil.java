package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.analyse.template.client.EnnMonitorLogAnalyseTemplateClient;

public class EnnMonitorLogAnalyseTemplateClientUtil {
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogAnalyseTemplateClient logAnalyseTemplateClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogAnalyseTemplateClient getEnnMonitorLogAnalyseTemplateClient() {
		if (logAnalyseTemplateClient == null) {
			synchronized (EnnMonitorLogAnalyseTemplateClientUtil.class) {
				if (logAnalyseTemplateClient == null) {
					logAnalyseTemplateClient = new EnnMonitorLogAnalyseTemplateClient(parameters.analyseTemplateServer, parameters.analyseTemplatePort);
				}
			}
		}
		return logAnalyseTemplateClient;
	}

}
