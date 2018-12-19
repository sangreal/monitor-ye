package enn.monitor.log.archive.api;

public class EnnMonitorLogArchiveConfigPutResponse {
	
	private boolean success = false;
	private String error = null;
	
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
