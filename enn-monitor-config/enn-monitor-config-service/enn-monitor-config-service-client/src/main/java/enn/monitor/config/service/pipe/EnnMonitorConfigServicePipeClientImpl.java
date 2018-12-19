package enn.monitor.config.service.pipe;

import java.util.List;

import enn.monitor.config.service.client.EnnMonitorConfigServiceClient;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorConfigServicePipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorConfigServiceClient serviceClient = null;
	
	public EnnMonitorConfigServicePipeClientImpl(EnnMonitorConfigServiceClient tokenClient) {
		this.serviceClient = tokenClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return serviceClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorConfigServiceTable serviceTable = (EnnMonitorConfigServiceTable) object;
		return serviceTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceTable tokenTable = (EnnMonitorConfigServiceTable) object;
		return tokenTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return serviceClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorConfigServiceGetDeletedData deletedData = (EnnMonitorConfigServiceGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorConfigServiceGetDeletedData deletedData = (EnnMonitorConfigServiceGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return serviceClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
