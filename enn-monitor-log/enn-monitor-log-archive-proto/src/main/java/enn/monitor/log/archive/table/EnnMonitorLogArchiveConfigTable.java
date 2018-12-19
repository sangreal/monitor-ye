package enn.monitor.log.archive.table;

public class EnnMonitorLogArchiveConfigTable {
	
	private long id = 1l;
	
	private long days = 30;
	
	private String lastUpdateUser = "micklongen";
	private long lastUpdateTime = 0l;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getDays() {
		return days;
	}
	
	public void setDays(long days) {
		this.days = days;
	}
	
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
