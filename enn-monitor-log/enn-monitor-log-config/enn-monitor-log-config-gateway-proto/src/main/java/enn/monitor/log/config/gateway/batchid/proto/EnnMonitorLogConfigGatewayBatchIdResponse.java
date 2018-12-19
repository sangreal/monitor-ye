package enn.monitor.log.config.gateway.batchid.proto;

public class EnnMonitorLogConfigGatewayBatchIdResponse {
	
	private long batchId = 0l;
	
	private boolean success = false;
	private String error = null;
	
	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
}
