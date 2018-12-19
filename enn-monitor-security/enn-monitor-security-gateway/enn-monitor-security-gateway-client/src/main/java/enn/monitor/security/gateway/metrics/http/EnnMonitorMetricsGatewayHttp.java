package enn.monitor.security.gateway.metrics.http;

import org.avaje.metric.report.HeaderInfo;
import org.avaje.metric.report.MetricReportConfig;
import org.avaje.metric.report.MetricReportManager;

import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.metrics.app.EnnMonitorMetricsInterface;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitorDefault;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.http.EnnMonitorGatewayHttpReport;

public class EnnMonitorMetricsGatewayHttp extends EnnMonitorMetricsInterface {
	
	@Override
	public void startMetricsCollector(boolean enableMetrics, Object parameter, int metricsFreq,
			String token, EnnMonitorJsonVisitor ennMonitorJsonVisitor)
			throws Exception {
		if (enableMetrics == false) {
			return;
		}
		
		EnnMonitorGatewayParameter gatewayParameter = (EnnMonitorGatewayParameter) parameter;
		
		MetricReportConfig metricReportConfig = new MetricReportConfig();
	    HeaderInfo headerInfo = new HeaderInfo();
	    
	    headerInfo.setEnv(EnnMonitorEnvAppUtil.getNameSpace());
	    headerInfo.setServer(EnnMonitorEnvAppUtil.getPodName());
	    headerInfo.setKey(token);
	    metricReportConfig.setHeaderInfo(headerInfo);
	    metricReportConfig.setFreqInSeconds(metricsFreq);
	    metricReportConfig.setLocalReporter(new EnnMonitorGatewayHttpReport(gatewayParameter.getTokenServer(), gatewayParameter.getPort(), token, ennMonitorJsonVisitor));
	    
	    metricReportManager = new MetricReportManager(metricReportConfig);
	}

	@Override
	public void startMetricsCollector(boolean enableMetrics, Object parameter, int metricsFreq,
			String token) throws Exception {
		startMetricsCollector(enableMetrics, parameter, metricsFreq, token, new EnnMonitorJsonVisitorDefault());
	}

}
