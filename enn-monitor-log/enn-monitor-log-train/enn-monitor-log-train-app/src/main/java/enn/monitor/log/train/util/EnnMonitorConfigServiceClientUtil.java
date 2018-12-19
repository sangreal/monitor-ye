package enn.monitor.log.train.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.log.ai.parameters.Parameters;

public class EnnMonitorConfigServiceClientUtil {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static Parameters parameters = null;
	
	private static EnnMonitorConfigServiceClient configServiceClient = null;
	
	public static void init(Parameters parameter) {
		parameters = parameter;
	}
	
	public static EnnMonitorConfigServiceClient getEnnMonitorConfigServiceClient() {
		if (configServiceClient == null) {
			synchronized (EnnMonitorConfigServiceClientUtil.class) {
				if (configServiceClient == null) {
					configServiceClient = new EnnMonitorConfigServiceClient(parameters.configServer, parameters.configPort);
				}
			}
		}
		return configServiceClient;
	}
	
	public static long getTokenId(String token) throws Exception {
		EnnMonitorConfigServiceGetResponse response = null;
		
		if (token == null || token.equals("") == true) {
			return -1l;
		}
		
		response = getEnnMonitorConfigServiceClient().getService(EnnMonitorConfigServiceGetRequest.newBuilder().setToken(token).build());
		if (response.getIsSuccess() == false) {
			logger.error(response.getError());
			return -1l;
		}
		
		if (response.getServiceTableList() == null || (response.getServiceTableList() != null && response.getServiceTableList().size() == 0)) {
			logger.error(token + "is not exists");
			return -1l;
		}
		
		if (response.getServiceTableList() != null && response.getServiceTableList().size() > 1) {
			logger.error(token + "is illegal");
			return -1l;
		}
		
		return response.getServiceTableList().get(0).getId();
	}

}
