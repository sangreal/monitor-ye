package enn.monitor.log.config.cache.event;

import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedData;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;
import enn.monitor.log.config.event.pipe.EnnMonitorLogConfigEventPipeClientImpl;

public class EnnMonitorLogConfigCacheEventPipeClientImpl extends EnnMonitorLogConfigEventPipeClientImpl {

	public EnnMonitorLogConfigCacheEventPipeClientImpl(EnnMonitorLogConfigEventClient eventClient) {
		super(eventClient);
	}

	@Override
	protected void updateAndInsert(Object object) {
		EnnMonitorLogConfigEventTable eventTable = (EnnMonitorLogConfigEventTable) object;
		EnnMonitorLogConfigCacheEventUtil.add(eventTable.getId(), eventTable.getEventName());
	}

	@Override
	protected void delete(Object object) {
		EnnMonitorLogConfigEventGetDeletedData deletedTable = (EnnMonitorLogConfigEventGetDeletedData) object;
		EnnMonitorLogConfigCacheEventUtil.delete(deletedTable.getId());
	}

}
