package enn.monitor.framework.pipe.common;

public class EnnMonitorFrameworkPipeDeletedTable {
	/*
	 uint64 seqNo = 1;
     uint64 id = 2; 
	 */

	public static final String SeqNoFieldName = "seqNo";
	private long seqNo = -1;
	
	public static final String IdFieldName = "id";
	private long id = -1;
	
	public long getSeqNo() {
		return seqNo;
	}
	
	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
