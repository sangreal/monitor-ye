package enn.monitor.config.serviceline.pipe;

import java.util.List;

import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedData;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceline.client.EnnMonitorConfigServiceLineClient;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorConfigServiceLinePipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorConfigServiceLineClient serviceLineClient = null;
	
	public EnnMonitorConfigServiceLinePipeClientImpl(EnnMonitorConfigServiceLineClient tokenClient) {
		this.serviceLineClient = tokenClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return serviceLineClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorConfigServiceLineTable serviceTable = (EnnMonitorConfigServiceLineTable) object;
		return serviceTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceLineTable tokenTable = (EnnMonitorConfigServiceLineTable) object;
		return tokenTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return serviceLineClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorConfigServiceLineGetDeletedData deletedData = (EnnMonitorConfigServiceLineGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceLineGetDeletedData deletedData = (EnnMonitorConfigServiceLineGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return serviceLineClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
