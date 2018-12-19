package enn.monitor.security.gateway.metrics.grpc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.avaje.metric.report.HeaderInfo;
import org.avaje.metric.report.MetricReportConfig;
import org.avaje.metric.report.MetricReportManager;

import com.google.common.io.Resources;

import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.metrics.app.EnnMonitorMetricsInterface;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitorDefault;
import enn.monitor.framework.tracing.EnnTracer;
import enn.monitor.security.gateway.metrics.EnnMonitorGatewayParameter;
import enn.monitor.security.gateway.metrics.EnnMonitorMetricsConfig;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;
import enn.monitor.security.gateway.trace.TracerBuilder;

public class EnnMonitorMetricsGatewayGrpc extends EnnMonitorMetricsInterface {
	private EnnMonitorMetricsConfig config;
	private EnnTracer tracer;

	public EnnMonitorMetricsGatewayGrpc() {}

	public EnnMonitorMetricsGatewayGrpc(EnnTracer tracer) {
		this.tracer = tracer;
	}

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
		EnnMonitorGatewayGrpcReport reporter;
		if (tracer == null || config == null || !config.enableTrace()) {
			reporter = new EnnMonitorGatewayGrpcReport(gatewayParameter.getTokenServer(),
					gatewayParameter.getPort(), token, ennMonitorJsonVisitor);
		} else {
			reporter = new EnnMonitorGatewayGrpcReport(gatewayParameter.getTokenServer(),
					gatewayParameter.getPort(), token, ennMonitorJsonVisitor, tracer);
		}
	    metricReportConfig.setLocalReporter(reporter);
	    
	    metricReportManager = new MetricReportManager(metricReportConfig);
	}

	@Override
	public void startMetricsCollector(boolean enableMetrics, Object parameter, int metricsFreq,
			String token) throws Exception {
		startMetricsCollector(enableMetrics, parameter, metricsFreq, token, new EnnMonitorJsonVisitorDefault());
	}

	public void startMetricsCollector() throws Exception {
		loadConfig();
		if (config.enableTrace()) {
			tracer = TracerBuilder.get();
		}
		
		if (config.getGatewayHost() != null && config.getGatewayHost().equals("") == false &&
				config.getGatewayPort() != null && config.getGatewayPort().equals("") == false) {
			if (config.enableStats()) {
				EnnMonitorSecurityGatewayStatsUtil.init(
						config.getGatewayHost(), Integer.parseInt(config.getGatewayPort()), 
						config.getToken(), 
						config.getStatsFrequency(), false);
			}
			
			startMetricsCollector(
					config.enableMetrics(),
					new EnnMonitorGatewayParameter(config.getGatewayHost(), Integer.parseInt(config.getGatewayPort())),
					config.getMetricsFrequency(),
					config.getToken()
				);
		}
	}

	private void loadConfig() {
		Properties defaults = new Properties();
		defaults.setProperty("trace.switch", "off");
		defaults.setProperty("metric.switch", "off");
		defaults.setProperty("stats.switch", "off");
		
		Properties props = new Properties(defaults);
		try {
			File file = new File("./monitor.properties");
			if (file.exists()) {
				FileInputStream fis;
				fis = new FileInputStream(file);
				props.load(fis);
				fis.close();
			} else {
				props.load((BufferedInputStream) Resources.getResource("monitor.properties").getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		config = new EnnMonitorMetricsConfig(
				props.getProperty("trace.switch"),
				props.getProperty("metric.switch"),
				props.getProperty("stats.switch"),
				props.getProperty("gateway.grpcHost"),
				props.getProperty("gateway.grpcPort"),
				props.getProperty("gateway.token"),
				Integer.parseInt(props.getProperty("metric.frequency", "15")),
				Integer.parseInt(props.getProperty("stats.frequency", "1"))
		);
	}

}
