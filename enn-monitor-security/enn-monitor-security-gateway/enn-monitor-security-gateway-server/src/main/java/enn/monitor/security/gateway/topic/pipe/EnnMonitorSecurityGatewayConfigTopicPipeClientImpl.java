package enn.monitor.security.gateway.topic.pipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedData;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;
import enn.monitor.config.business.topic.pipe.EnnMonitorConfigBusinessTopicPipeClientImpl;
import enn.monitor.security.gateway.topic.cache.EnnMonitorConfigBussinessTopicCache;

public class EnnMonitorSecurityGatewayConfigTopicPipeClientImpl extends EnnMonitorConfigBusinessTopicPipeClientImpl {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorConfigBussinessTopicCache topicCache = null;

	public EnnMonitorSecurityGatewayConfigTopicPipeClientImpl(EnnMonitorConfigBusinessTopicClient topicClient, EnnMonitorConfigBussinessTopicCache topicCache) {
		super(topicClient);
		this.topicCache = topicCache;
	}

	@Override
	protected void updateAndInsert(Object object) {
		EnnMonitorConfigBusinessTopicTable topicTable = (EnnMonitorConfigBusinessTopicTable) object;
		
		logger.info("source is " + topicTable.getSource() + ", topic is " + topicTable.getTopic());
		topicCache.add(topicTable.getId(), topicTable.getSource(), topicTable.getTopic());
	}

	@Override
	protected void delete(Object object) {
		EnnMonitorConfigBusinessTopicGetDeletedData deletedTable = (EnnMonitorConfigBusinessTopicGetDeletedData) object;
		topicCache.delete(deletedTable.getId());
		
		logger.info("delete source " + deletedTable.getId());
	}

}
