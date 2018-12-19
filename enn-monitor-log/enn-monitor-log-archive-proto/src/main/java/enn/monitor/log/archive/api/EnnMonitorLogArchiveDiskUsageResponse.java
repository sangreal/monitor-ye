package enn.monitor.log.archive.api;

public class EnnMonitorLogArchiveDiskUsageResponse {
	
	private boolean success = false;
	private String error = null;
	
	private long available = 0l;
	private long capacity = 0l;
	
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
	
	public long getAvailable() {
		return available;
	}

	public void setAvailable(long available) {
		this.available = available;
	}

	public long getCapacity() {
		return capacity;
	}
	
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

}
