package enn.monitor.log.config.logformat.pipe;

import java.util.List;

import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;
import enn.monitor.log.config.logformat.client.EnnMonitorLogConfigLogformatClient;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedData;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;

public abstract class EnnMonitorLogConfigLogformatPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorLogConfigLogformatClient logformatClient = null;
	
	public EnnMonitorLogConfigLogformatPipeClientImpl(EnnMonitorLogConfigLogformatClient logformatClient) {
		this.logformatClient = logformatClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return logformatClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorLogConfigLogformatTable logformatTable = (EnnMonitorLogConfigLogformatTable) object;
		return logformatTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigLogformatTable logformatTable = (EnnMonitorLogConfigLogformatTable) object;
		return logformatTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return logformatClient.getValidDataList(startSeqNo, batch);
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorLogConfigLogformatGetDeletedData deletedData = (EnnMonitorLogConfigLogformatGetDeletedData) object;
		return deletedData.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorLogConfigLogformatGetDeletedData deletedData = (EnnMonitorLogConfigLogformatGetDeletedData) object;
		return deletedData.getSeqNo();
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return logformatClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
