package enn.monitor.framework.common.opentsdb;

import java.util.HashMap;
import java.util.Map;

public class OpentsdbJsonWithLong extends OpentsdbJsonCommon {
	
	private Map<String, Long> metricsMap = new HashMap<String, Long>();

	public Long getValue(String metric) {
		return metricsMap.get(metric);
	}
	
	public void setValue(String metric, Long value) {
		metricsMap.put(metric, value);
	}
	
	public int size() {
		return metricsMap.size();
	}
	
	public Map<String, Long> getValueMap() {
		return metricsMap;
	}
	
}
