package enn.monitor.framework.pipe.server;

public abstract class EnnMonitorFrameworkPipeServerImplBase {
	public long getTagId() {
		return -1;
	}
	
	// 获取id和seqNo
	public abstract long getId(Object object) throws Exception;
	public abstract long getSeqNo(Object object) throws Exception;
	
	public abstract long incDataId() throws Exception;
	public abstract long incDataSeqNo() throws Exception;
	
	public abstract void insert(long id, long seqNo, Object object) throws Exception;
	public abstract void update(long id, long seqNo, Object object) throws Exception;
	public abstract void updateAndInsert(long id, long seqNo) throws Exception;
	public abstract void delete(long id, long seqNo, Object object) throws Exception;
}
