package enn.monitor.config.cluster.pipe;

import java.util.List;

import enn.monitor.config.cluster.client.EnnMonitorConfigClusterClient;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedData;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorConfigClusterPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorConfigClusterClient clusterClient = null;
	
	public EnnMonitorConfigClusterPipeClientImpl(EnnMonitorConfigClusterClient tokenClient) {
		this.clusterClient = tokenClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return clusterClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorConfigClusterTable clusterTable = (EnnMonitorConfigClusterTable) object;
		return clusterTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorConfigClusterTable clusterTable = (EnnMonitorConfigClusterTable) object;
		return clusterTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return clusterClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorConfigClusterGetDeletedData deletedData = (EnnMonitorConfigClusterGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorConfigClusterGetDeletedData deletedData = (EnnMonitorConfigClusterGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return clusterClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
