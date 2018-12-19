package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.train.master.client.EnnMonitorLogTrainMasterClient;

public class EnnMonitorLogTrainMasterClientUtil {
	
private static Parameters parameters = null;
	
	private static EnnMonitorLogTrainMasterClient logTrainMasterClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogTrainMasterClient getEnnMonitorLogTrainMasterClient() {
		if (logTrainMasterClient == null) {
			synchronized (EnnMonitorLogTrainMasterClientUtil.class) {
				if (logTrainMasterClient == null) {
					logTrainMasterClient = new EnnMonitorLogTrainMasterClient(parameters.logGaServer, parameters.logGaPort);
				}
			}
		}
		return logTrainMasterClient;
	}

}
