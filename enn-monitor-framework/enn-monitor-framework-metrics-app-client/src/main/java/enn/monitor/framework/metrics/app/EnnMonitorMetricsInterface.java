package enn.monitor.framework.metrics.app;

import org.avaje.metric.report.MetricReportManager;

import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;

public abstract class EnnMonitorMetricsInterface {
	
	protected MetricReportManager metricReportManager = null;
	
	public abstract void startMetricsCollector(boolean enableMetrics, Object parameter,
			int metricsFreq, String token, EnnMonitorJsonVisitor ennMonitorJsonVisitor)
		      throws Exception;
	
	public abstract void startMetricsCollector(boolean enableMetrics, Object parameter,
			int metricsFreq, String token) throws Exception;
	
	public void closeMetricsCollector() throws Exception {
		if (metricReportManager != null) {
			metricReportManager.shutdown();
			metricReportManager = null;
		}
	}

}
