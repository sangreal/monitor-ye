package enn.monitor.log.train.util;

import enn.monitor.log.ai.parameters.Parameters;
import enn.monitor.log.analyse.storge.client.EnnMonitorLogAnalyseStorageClient;

public class EnnMonitorLogAnalyseStorageClientUtil {

	private static Parameters parameters = null;
	
	private static EnnMonitorLogAnalyseStorageClient logAnalyseStorageClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorLogAnalyseStorageClient getEnnMonitorLogAnalyseStorageClient() {
		if (logAnalyseStorageClient == null) {
			synchronized (EnnMonitorLogAnalyseStorageClientUtil.class) {
				if (logAnalyseStorageClient == null) {
					logAnalyseStorageClient = new EnnMonitorLogAnalyseStorageClient(parameters.analyseStorageServer, parameters.analyseStoragePort);
				}
			}
		}
		return logAnalyseStorageClient;
	}
	
}
