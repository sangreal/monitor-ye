package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.config.analyse.term.client.EnnMonitorLogConfigAnalyseTermClient;

public class EnnMonitorLogConfigAnalyseTermClientUtil {
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogConfigAnalyseTermClient logConfigAnalyseTermClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogConfigAnalyseTermClient getEnnMonitorLogConfigAnalyseTermClient() {
		if (logConfigAnalyseTermClient == null) {
			synchronized (EnnMonitorLogConfigAnalyseTermClientUtil.class) {
				if (logConfigAnalyseTermClient == null) {
					logConfigAnalyseTermClient = new EnnMonitorLogConfigAnalyseTermClient(parameters.logConfigServer, parameters.logConfigPort);
				}
			}
		}
		return logConfigAnalyseTermClient;
	}

}
