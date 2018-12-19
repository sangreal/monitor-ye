package enn.monitor.log.storage.es.data;

import java.util.concurrent.BlockingQueue;

import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.framework.kafka.EnnKafkaConsumer;
import enn.monitor.framework.kafka.EnnKafkaMsgProcessor;
import enn.monitor.framework.log.channel.ChannelWriteData;

public class EnnMonitorLogStorageESDataCollector {
	EnnKafkaConsumer<Long, String> ennKafkaConsumer = null;
	private String kafkaUrl = null;
	private String topic = null;
	private String groupId = null;
	private BlockingQueue<ChannelWriteData> dataQueue = null;
	
	private int queueSize = 100000;
	
	private CounterMetric logMetrics = null;
	
	public EnnMonitorLogStorageESDataCollector(String kafkaUrl, String topic, String groupId, BlockingQueue<ChannelWriteData> dataQueue, int queueSize) {
		this.kafkaUrl = kafkaUrl;
		this.topic = topic;
		this.groupId = groupId;
		this.dataQueue = dataQueue;
		this.queueSize = queueSize;
		
		logMetrics = MetricManager.getCounterMetric(EnnMonitorLogStorageESDataCollector.class, "logKafka_"+topic);
	}
	
	public void startJob() {
		
		ennKafkaConsumer = (EnnKafkaConsumer<Long, String>) new EnnKafkaConsumer.Builder()
	    		.setUrl(kafkaUrl)
                .setTopic(topic)
                .setGroupId(groupId)
                .setKeyDeserializer("org.apache.kafka.common.serialization.LongDeserializer")
                .setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
                .build();
		
		ennKafkaConsumer.setProcessor(new ReaderProcessor(dataQueue, queueSize, logMetrics));
		ennKafkaConsumer.start();
	}
	
	public void stopJob() {
		ennKafkaConsumer.stop();
	}
	
	public static class ReaderProcessor implements EnnKafkaMsgProcessor<Long, String> {
		
		private BlockingQueue<ChannelWriteData> dataQueue = null;
		
		private int queueSize = 100000;
		private CounterMetric logMetrics = null;
		
		public ReaderProcessor(BlockingQueue<ChannelWriteData> dataQueue, int queueSize, CounterMetric logMetrics) {
			this.dataQueue = dataQueue;
			this.queueSize = queueSize;
			this.logMetrics = logMetrics;
		}
		
		@Override
		public boolean process(Long key, String value) throws Exception {
			ChannelWriteData data = new ChannelWriteData();
			data.setObject(value);
			dataQueue.add(data);
			
			logMetrics.markEvent();
			
			if (dataQueue.size() >= queueSize) {
				Thread.sleep(100);
			}
			
			return true;
		}

		@Override
		public void stop() {
		}
		
	}

}
