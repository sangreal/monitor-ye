package enn.monitor.framework.common.opentsdb;

import java.util.HashMap;
import java.util.Map;

public class OpentsdbJsonWithDouble extends OpentsdbJsonCommon {
	
	private Map<String, Double> metricsMap = new HashMap<String, Double>();

	public Double getValue(String metric) {
		return metricsMap.get(metric);
	}
	
	public void setValue(String metric, Double value) {
		metricsMap.put(metric, value);
	}
	
	public int size() {
		return metricsMap.size();
	}

	public Map<String, Double> getValueMap() {
		return metricsMap;
	}

}
