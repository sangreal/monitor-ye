package enn.monitor.log.analyse.tester.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.analyse.tester.parameters.Parameters;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.http.EnnMonitorMetricsGatewayHttp;

public class EnnMonitorLogAnalyseTesterMetricsUtil {
	
	private volatile static EnnMonitorMetricsGatewayHttp metricsGrpc = null;
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void initMetrics(Parameters parameters) throws Exception{
		if (metricsGrpc == null) {
			synchronized (EnnMonitorLogAnalyseTesterMetricsUtil.class) {
				if (metricsGrpc == null) {
					metricsGrpc = new EnnMonitorMetricsGatewayHttp();
					logger.info("enableMetrics: " + parameters.enableMetrics);
					logger.info("gatewayHost: " + parameters.gatewayHost);
					logger.info("gatewayPort: " + parameters.gatewayPort);
					logger.info("token: " + parameters.token);
				    metricsGrpc.startMetricsCollector(
				    		parameters.enableMetrics, (Object)(new EnnMonitorGatewayParameter(parameters.gatewayHost, parameters.gatewayPort)), 1, parameters.token);
				}
			}
		}
	}
	
	public static void closeMetrics() throws Exception {
		if (metricsGrpc != null) {
			synchronized (EnnMonitorLogAnalyseTesterMetricsUtil.class) {
				if (metricsGrpc != null) {
					metricsGrpc.closeMetricsCollector();
					metricsGrpc = null;
				}
			}
		}
	}

}
