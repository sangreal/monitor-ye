package enn.monitor.log.normalizing.logformat.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedData;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;
import enn.monitor.log.config.logformat.pipe.EnnMonitorLogConfigLogformatPipeClientImpl;
import enn.monitor.log.normalizing.logformat.cache.EnnMonitorLogNormalizingLogformatCache;

public class EnnMonitorLogNormalizingLogformatPipeClientImpl extends EnnMonitorLogConfigLogformatPipeClientImpl {
	
	private static final Logger logger = LogManager.getLogger();
	
	public EnnMonitorLogNormalizingLogformatPipeClientImpl(EnnMonitorLogConfigLogformatClient topicClient) {
		super(topicClient);
	}

	@Override
	protected void updateAndInsert(Object object) {
		EnnMonitorLogConfigLogformatTable logformatTable = (EnnMonitorLogConfigLogformatTable) object;
		
		logger.info("logformat Serviceid is " + logformatTable.getBelongToServiceId());
		logger.info("logformat regex is " + logformatTable.getRegex());
		logger.info("logformat logformat is " + logformatTable.getLogformat());
		
		EnnMonitorLogNormalizingLogformatCache.add(logformatTable.getBelongToServiceId(), logformatTable.getRegex(), logformatTable.getLogformat());
	}

	@Override
	protected void delete(Object object) {
		EnnMonitorLogConfigLogformatGetDeletedData deletedTable = (EnnMonitorLogConfigLogformatGetDeletedData) object;
		EnnMonitorLogNormalizingLogformatCache.delete(deletedTable.getId());
		
		logger.info("delete logformat " + deletedTable.getId());
	}

}
