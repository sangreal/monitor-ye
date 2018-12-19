package enn.monitor.security.gateway.token.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceStatus;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.pipe.EnnMonitorConfigServicePipeClientImpl;
import enn.monitor.security.gateway.token.cache.EnnMonitorConfigServiceTokenCache;

public class EnnMonitorSecurityGatewayConfigTokenPipeClientImpl extends EnnMonitorConfigServicePipeClientImpl {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorConfigServiceTokenCache tokenCache = null;

	public EnnMonitorSecurityGatewayConfigTokenPipeClientImpl(EnnMonitorConfigServiceClient tokenClient, EnnMonitorConfigServiceTokenCache tokenCache) {
		super(tokenClient);
		this.tokenCache = tokenCache;
	}

	@Override
	protected void updateAndInsert(Object object) {
		EnnMonitorConfigServiceTable tokenTable = (EnnMonitorConfigServiceTable) object;
		
		if (tokenTable.getStatus().equals(EnnMonitorConfigServiceStatus.ServiceRunning) == false) {
			tokenCache.delete(tokenTable.getId());
			return;
		}
		
		logger.info("add token " + tokenTable.getToken());
		tokenCache.add(tokenTable.getToken(), tokenTable.getId());
	}

	@Override
	protected void delete(Object object) {
		EnnMonitorConfigServiceGetDeletedData deletedTable = (EnnMonitorConfigServiceGetDeletedData) object;
		tokenCache.delete(deletedTable.getId());
		
		logger.info("delete token " + deletedTable.getId());
	}

}
