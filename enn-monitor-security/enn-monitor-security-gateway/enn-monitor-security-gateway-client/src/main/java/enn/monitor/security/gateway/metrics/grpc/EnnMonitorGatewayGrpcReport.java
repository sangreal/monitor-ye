package enn.monitor.security.gateway.metrics.grpc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.report.MetricReporter;
import org.avaje.metric.report.ReportMetrics;

import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.grpc.client.EnnMonitorSecurityGatewayGrpcClient;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest;

public class EnnMonitorGatewayGrpcReport implements MetricReporter {
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorSecurityGatewayGrpcClient gatewayClient = null;
	private String token = null;
	private EnnMonitorJsonVisitor visitor = null;

	public EnnMonitorGatewayGrpcReport(String tokenServer, int port, String token, EnnMonitorJsonVisitor visitor) {
		gatewayClient = new EnnMonitorSecurityGatewayGrpcClient(tokenServer, port);
		this.token = token;
		this.visitor = visitor;
	}

	public EnnMonitorGatewayGrpcReport(String tokenServer, int port, String token, EnnMonitorJsonVisitor visitor, EnnTracer tracer) {
		gatewayClient = new EnnMonitorSecurityGatewayGrpcClient(tokenServer, port, tracer);
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
		EnnMonitorSecurityGatewayRequest.Builder request = EnnMonitorSecurityGatewayRequest.newBuilder();

		try {
			visitor.setReportMetrics(reportMetrics);
			
			jsonString = visitor.getJsonString();
			
			request.setSource("monitor-app");
			request.setToken(token);
			request.setJsonList(jsonString);
			gatewayClient.put(request.build(), null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
