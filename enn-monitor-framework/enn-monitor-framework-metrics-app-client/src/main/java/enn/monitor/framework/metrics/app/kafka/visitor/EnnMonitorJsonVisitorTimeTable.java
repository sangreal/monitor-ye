package enn.monitor.framework.metrics.app.kafka.visitor;

import java.io.IOException;
import java.util.HashMap;

import org.avaje.metric.GaugeDoubleMetric;

import enn.monitor.framework.common.opentsdb.OpentsdbJsonWithDouble;

public class EnnMonitorJsonVisitorTimeTable extends EnnMonitorJsonVisitor {
	
	private long baseTime = 0;
	private long currentTime = 0;
	private double shortestRoute = 0.0;

	@Override
	protected long getCollection() {
		return baseTime + currentTime * 30;
	}
	
	public void setBaseTime(long baseTime) {
		this.baseTime = baseTime;
	}
	
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public double getShortestRoute() {
		return shortestRoute;
	}

	public void setShortestRoute(double shortestRoute) {
		this.shortestRoute = shortestRoute;
	}

	public void addLable(String key, String value) {
		if (defaultTagMap == null) {
			defaultTagMap = new HashMap<String, String>();
		}
		
		defaultTagMap.put(key, value);
	}
	
	@Override
	public void visit(GaugeDoubleMetric gaugeDoubleMetric) throws IOException {
		initOpentsdbJson(opentsdbJsonWithDouble);
		opentsdbJsonWithDouble.setValue(gaugeDoubleMetric.getName().toString(), shortestRoute);
	}

}
