package enn.monitor.framework.metrics.app.kafka;

import org.avaje.metric.report.HeaderInfo;
import org.avaje.metric.report.MetricReportConfig;
import org.avaje.metric.report.MetricReportManager;

import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.metrics.app.EnnMonitorMetricsInterface;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;
import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitorDefault;

public class EnnMonitorMetricsKafka extends EnnMonitorMetricsInterface {
	
	@Override
	public void startMetricsCollector(boolean enableMetrics, Object parameter, int metricsFreq,
			String token, EnnMonitorJsonVisitor ennMonitorJsonVisitor)
			throws Exception {
		if (enableMetrics == false) {
			return;
		}
		
		EnnMonitorKafkaParameter kafkaParameter = (EnnMonitorKafkaParameter) parameter;
		
		MetricReportConfig metricReportConfig = new MetricReportConfig();
	    HeaderInfo headerInfo = new HeaderInfo();
	    
	    headerInfo.setEnv(EnnMonitorEnvAppUtil.getNameSpace());
	    headerInfo.setServer(EnnMonitorEnvAppUtil.getPodName());
	    headerInfo.setKey(token);
	    metricReportConfig.setHeaderInfo(headerInfo);
	    metricReportConfig.setFreqInSeconds(metricsFreq);
	    metricReportConfig.setLocalReporter(new EnnMonitorKafkaReport(kafkaParameter.getKafkaUrl(), kafkaParameter.getTopics(), 
	    		kafkaParameter.getEnnKafkaMsgValueFilter(), ennMonitorJsonVisitor));
	    
	    metricReportManager = new MetricReportManager(metricReportConfig);
	}

	@Override
	public void startMetricsCollector(boolean enableMetrics, Object parameter, int metricsFreq,
			String token) throws Exception {
		startMetricsCollector(enableMetrics, parameter, metricsFreq, token, new EnnMonitorJsonVisitorDefault());
	}

}
