package enn.monitor.log.archive.api;

public class EnnMonitorLogArchiveConfigGetResponse {
	
	private long days = 30;

	private boolean success = false;
	private String error = null;
	
	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
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
