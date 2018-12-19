package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.config.cache.client.EnnMonitorLogConfigCacheClient;

public class EnnMonitorLogConfigCacheClientUtil {
	
	private static Parameters parameters = null;
	
	private static EnnMonitorLogConfigCacheClient logConfigCacheClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogConfigCacheClient getEnnMonitorLogConfigCacheClient() {
		if (logConfigCacheClient == null) {
			synchronized (EnnMonitorLogConfigCacheClientUtil.class) {
				if (logConfigCacheClient == null) {
					logConfigCacheClient = new EnnMonitorLogConfigCacheClient(parameters.logConfigCacheServer, parameters.logConfigCachePort);
				}
			}
		}
		return logConfigCacheClient;
	}

}
