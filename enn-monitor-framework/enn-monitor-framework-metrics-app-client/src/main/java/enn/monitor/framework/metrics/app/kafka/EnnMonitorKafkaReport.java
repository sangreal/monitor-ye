package enn.monitor.framework.metrics.app.kafka;

import enn.monitor.framework.kafka.EnnKafkaMsgValueFilter;
import enn.monitor.framework.kafka.EnnKafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;
import org.avaje.metric.report.MetricReporter;
import org.avaje.metric.report.ReportMetrics;

import enn.monitor.framework.metrics.app.kafka.visitor.EnnMonitorJsonVisitor;

public class EnnMonitorKafkaReport implements MetricReporter {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnKafkaProducer<Long, String> ennKafkaProducer = null;
	
	private EnnMonitorJsonVisitor visitor = null;
	
	private CounterMetric kafkaProducerMetrics = MetricManager.getCounterMetric(EnnMonitorKafkaReport.class, "kafkaProducer");
	
	public EnnMonitorKafkaReport(String kafkaUrl, String topics, EnnKafkaMsgValueFilter ennKafkaMsgValueFilter, EnnMonitorJsonVisitor visitor) {
		ennKafkaProducer =
                (EnnKafkaProducer<Long, String>) new EnnKafkaProducer.Builder()
                        .setUrl(kafkaUrl)
                        .setTopic(topics)
                        .setKeySerializer("org.apache.kafka.common.serialization.LongSerializer")
                        .setValueSerializer("org.apache.kafka.common.serialization.StringSerializer")
                        .build();
		
		this.visitor = visitor;
		
		if (ennKafkaMsgValueFilter != null) {
			ennKafkaProducer.setEnnKafkaValueFilter(ennKafkaMsgValueFilter);
		}
	}

	@Override
	public void report(ReportMetrics reportMetrics) {
		String jsonString = null;

		try {
			visitor.setReportMetrics(reportMetrics);
			
			jsonString = visitor.getJsonString();
			kafkaProducerMetrics.markEvent();
			ennKafkaProducer.send(System.currentTimeMillis(), jsonString);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
	}

}
