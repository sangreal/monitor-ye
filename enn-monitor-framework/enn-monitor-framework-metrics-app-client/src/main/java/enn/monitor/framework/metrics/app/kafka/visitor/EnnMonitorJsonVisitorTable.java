package enn.monitor.framework.metrics.app.kafka.visitor;

import java.util.HashMap;

public class EnnMonitorJsonVisitorTable extends EnnMonitorJsonVisitor {
	
	private long baseTime = 0;
	private long firstTime = 0;

	@Override
	protected long getCollection() {
		if (firstTime == 0) {
			firstTime = collectionTime;
		} else {
			return baseTime + collectionTime - firstTime;
		}
		
		return 0;
	}
	
	public void setBaseTime(long baseTime) {
		this.baseTime = baseTime;
	}
	
	public void setFirstTime(long firstTime) {
		this.firstTime = firstTime;
	}
	
	public long getFirstTime() {
		return firstTime;
	}
	
	public void addLable(String key, String value) {
		if (defaultTagMap == null) {
			defaultTagMap = new HashMap<String, String>();
		}
		
		defaultTagMap.put(key, value);
	}

}
