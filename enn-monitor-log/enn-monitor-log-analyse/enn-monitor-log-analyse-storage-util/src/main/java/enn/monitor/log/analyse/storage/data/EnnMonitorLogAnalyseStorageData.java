package enn.monitor.log.analyse.storage.data;

public class EnnMonitorLogAnalyseStorageData {
	
	private long id;
	private long seqNo;
	private String nndata = null;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getSeqNo() {
		return seqNo;
	}
	
	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}
	
	public String getNndata() {
		return nndata;
	}
	
	public void setNndata(String nndata) {
		this.nndata = nndata;
	}

}
