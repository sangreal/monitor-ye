package enn.monitor.security.gateway.channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import brave.Span;
import enn.monitor.framework.kafka.EnnKafkaProducer;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;
import enn.monitor.security.gateway.topic.cache.EnnMonitorConfigBussinessTopicCache;
import enn.monitor.security.gateway.trace.util.TraceManager;

public class GatewayChannelWriteTask extends ChannelWriteTask {
	private static final Logger logger = LogManager.getLogger();
	
	private boolean enableTokenFilter = false;
	private EnnMonitorConfigBussinessTopicCache ennMonitorSecurityConfigTopicCache = null;
	
	private CounterMetric kafkaPutMetrics = MetricManager.getCounterMetric(GatewayChannelWriteTask.class, "kafka_send");
	
	public GatewayChannelWriteTask(EnnMonitorConfigBussinessTopicCache ennMonitorSecurityConfigTopicCache, boolean enableTokenFilter) {
		this.ennMonitorSecurityConfigTopicCache = ennMonitorSecurityConfigTopicCache;
		this.enableTokenFilter = enableTokenFilter;
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		Span span = TraceManager.getInstance().extract(stockWriteData);
		Span span1 = null;
		if (span != null) {
			logger.info("Data dequeue, Send to kafka");
			span1 = TraceManager.getInstance().newChild(span, "kafka_send");
			span1.start();
		}
		try {
			String jsonString = null;
			String tokenId = null;

			EnnKafkaProducer<Long, String> ennKafkaProducer = null;

			GatewayData data = (GatewayData) stockWriteData.getObject();

			jsonString = data.getJsonList();
			tokenId = data.getTokenId();

			if (enableTokenFilter == true) {
				if (tokenId == null || tokenId.trim().equals("") == true) {
					return;
				}
			}

			ennKafkaProducer = ennMonitorSecurityConfigTopicCache.getEnnKafkaProducer(data.getSource());
			if (ennKafkaProducer == null) {
				logger.error("the topic of source is not exists, the source is " + data.getSource());
				return;
			}

			if (jsonString != null && jsonString.trim().equals("") == false) {
				ennKafkaProducer.send(null, tokenId + "|" + jsonString, false);
				kafkaPutMetrics.markEvent();
			}
		} finally {
			if (span1 != null) {
				span1.finish();
			}
		}
	}

}
