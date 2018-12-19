package enn.monitor.log.config.event.pipe;

import java.util.List;

import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;
import enn.monitor.log.config.event.client.EnnMonitorLogConfigEventClient;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedData;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;

public abstract class EnnMonitorLogConfigEventPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorLogConfigEventClient eventClient = null;
	
	public EnnMonitorLogConfigEventPipeClientImpl(EnnMonitorLogConfigEventClient eventClient) {
		this.eventClient = eventClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return eventClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorLogConfigEventTable eventTable = (EnnMonitorLogConfigEventTable) object;
		return eventTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigEventTable eventTable = (EnnMonitorLogConfigEventTable) object;
		return eventTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return eventClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorLogConfigEventGetDeletedData deletedData = (EnnMonitorLogConfigEventGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigEventGetDeletedData deletedData = (EnnMonitorLogConfigEventGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return eventClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
