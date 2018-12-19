package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;

public class EnnMonitorLogConfigEventClientUtil {
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogConfigEventClient logConfigEventClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogConfigEventClient getEnnMonitorLogConfigEventClient() {
		if (logConfigEventClient == null) {
			synchronized (EnnMonitorLogConfigEventClientUtil.class) {
				if (logConfigEventClient == null) {
					logConfigEventClient = new EnnMonitorLogConfigEventClient(parameters.logConfigServer, parameters.logConfigPort);
				}
			}
		}
		return logConfigEventClient;
	}

}
