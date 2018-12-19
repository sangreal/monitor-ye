package enn.monitor.framework.pipe.client;

import java.util.List;

public abstract class EnnMonitorFrameworkPipeClientImplBase {
	public long getTagId() throws Exception {
		return -1l;
	}
	
	public void updateAndInsertData(Object object) throws Exception {
		if (getValidId(object) == getTagId()) {
			return;
		}
		
		updateAndInsert(object);
	}
	
	public void deleteData(Object object) throws Exception {
		delete(object);
	}

	// 已经删除的表的最大SeqNo
	public abstract long getMaxDeletedSeqNo() throws Exception;
	
	// 获取id和seqNo
	public abstract long getValidId(Object object) throws Exception;
	public abstract long getValidSeqNo(Object object) throws Exception;
	
	public abstract long getDeletedId(Object object) throws Exception;
	public abstract long getDeletedSeqNo(Object object) throws Exception;
	
	// get data, sort by seqNo
	public abstract List<Object> getValidDataList(long startSeqNo, int batch) throws Exception;
	public abstract List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception;
	
	// 数据回调
	protected abstract void updateAndInsert(Object object) throws Exception;
	protected abstract void delete(Object object) throws Exception;
}
