package enn.monitor.framework.common.opentsdb;

import java.util.HashMap;
import java.util.Map;

public class OpentsdbJsonWithString extends OpentsdbJsonCommon {
	
	private Map<String, String> metricsMap = new HashMap<String, String>();

	public String getValue(String metric) {
		return metricsMap.get(metric);
	}
	
	public void setValue(String metric, String value) {
		metricsMap.put(metric, value);
	}
	
	public int size() {
		return metricsMap.size();
	}
	
	public Map<String, String> getValueMap() {
		return metricsMap;
	}
	
}
