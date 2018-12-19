package enn.monitor.security.gateway.metrics.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.report.MetricReporter;
import org.avaje.metric.report.ReportMetrics;

import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;
import enn.monitor.security.gateway.http.client.EnnMonitorSecurityGatewayHttpClient;

public class EnnMonitorGatewayHttpReport implements MetricReporter {
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorSecurityGatewayHttpClient gatewayClient = null;
	private String token = null;
	private EnnMonitorJsonVisitor visitor = null;
	
	public EnnMonitorGatewayHttpReport(String tokenServer, int port, String token, EnnMonitorJsonVisitor visitor) {
		gatewayClient = new EnnMonitorSecurityGatewayHttpClient(tokenServer, port);
		this.token = token;
		this.visitor = visitor;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report(ReportMetrics reportMetrics) {
		String jsonString = null;

		try {
			visitor.setReportMetrics(reportMetrics);
			
			jsonString = visitor.getJsonString();
			
			gatewayClient.put("monitor-app", token, jsonString);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
