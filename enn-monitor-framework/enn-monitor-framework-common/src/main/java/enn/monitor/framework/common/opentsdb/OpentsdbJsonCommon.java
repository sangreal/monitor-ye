package enn.monitor.framework.common.opentsdb;

import java.util.HashMap;
import java.util.Map;

public abstract class OpentsdbJsonCommon {
	
	private long timestamp;
	private Map<String, String> tags = new HashMap<String, String>();
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Map<String, String> getTags() {
		return tags;
	}
	
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}
	
	public abstract int size();
}
