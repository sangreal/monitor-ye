package enn.monitor.config.business.topic.pipe;

import java.util.List;

import enn.monitor.config.business.topic.client.EnnMonitorConfigBusinessTopicClient;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedData;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;
import enn.monitor.framework.pipe.client.EnnMonitorFrameworkPipeClientImplBase;

public abstract class EnnMonitorConfigBusinessTopicPipeClientImpl extends EnnMonitorFrameworkPipeClientImplBase {
	
	private EnnMonitorConfigBusinessTopicClient topicClient = null;
	
	public EnnMonitorConfigBusinessTopicPipeClientImpl(EnnMonitorConfigBusinessTopicClient topicClient) {
		this.topicClient = topicClient;
	}

	@Override
	public long getMaxDeletedSeqNo() throws Exception {
		return topicClient.getMaxDeletedSeqNo();
	}

	@Override
	public long getValidId(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicTable topicTable = (EnnMonitorConfigBusinessTopicTable) object;
		return topicTable.getId();
	}

	@Override
	public long getValidSeqNo(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicTable topicTable = (EnnMonitorConfigBusinessTopicTable) object;
		return topicTable.getSeqNo();
	}
	
	@Override
	public long getDeletedId(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicGetDeletedData deletedTable = (EnnMonitorConfigBusinessTopicGetDeletedData) object;
		return deletedTable.getId();
	}
	
	@Override
	public long getDeletedSeqNo(Object object) throws Exception {
		EnnMonitorConfigBusinessTopicGetDeletedData deletedTable = (EnnMonitorConfigBusinessTopicGetDeletedData) object;
		return deletedTable.getSeqNo();
	}

	@Override
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		return topicClient.getValidDataList(startSeqNo, batch);
	}

	@Override
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		return topicClient.getDeletedDataList(startSeqNo, batch);
	}

	@Override
	protected abstract void updateAndInsert(Object object);

	@Override
	protected abstract void delete(Object object);

}
