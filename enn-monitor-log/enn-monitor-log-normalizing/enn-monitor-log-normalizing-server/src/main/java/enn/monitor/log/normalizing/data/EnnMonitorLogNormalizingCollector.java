package enn.monitor.log.normalizing.data;

import java.util.concurrent.BlockingQueue;

import enn.monitor.framework.kafka.EnnKafkaConsumer;
import enn.monitor.framework.kafka.EnnKafkaMsgProcessor;
import enn.monitor.framework.log.channel.ChannelWriteData;

public class EnnMonitorLogNormalizingCollector {
	EnnKafkaConsumer<Long, String> ennKafkaConsumer = null;
	private String kafkaUrl = null;
	private String topic = null;
	private String groupId = null;
	private BlockingQueue<ChannelWriteData> dataQueue = null;
	
	public EnnMonitorLogNormalizingCollector(String kafkaUrl, String topic, String groupId, BlockingQueue<ChannelWriteData> dataQueue) {
		this.kafkaUrl = kafkaUrl;
		this.topic = topic;
		this.groupId = groupId;
		this.dataQueue = dataQueue;
	}
	
	public void startJob() {
		
		ennKafkaConsumer = (EnnKafkaConsumer<Long, String>) new EnnKafkaConsumer.Builder()
	    		.setUrl(kafkaUrl)
                .setTopic(topic)
                .setGroupId(groupId)
                .setKeyDeserializer("org.apache.kafka.common.serialization.LongDeserializer")
                .setValueDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
                .build();
		
		ennKafkaConsumer.setProcessor(new ReaderProcessor(dataQueue));
		ennKafkaConsumer.start();
	}
	
	public void stopJob() {
		ennKafkaConsumer.stop();
	}
	
	public static class ReaderProcessor implements EnnKafkaMsgProcessor<Long, String> {
		
		private BlockingQueue<ChannelWriteData> dataQueue = null;
		
		public ReaderProcessor(BlockingQueue<ChannelWriteData> dataQueue) {
			this.dataQueue = dataQueue;
		}
		
		@Override
		public boolean process(Long key, String value) throws Exception {
			ChannelWriteData data = new ChannelWriteData();
			data.setObject(value);
			dataQueue.add(data);
			
			return true;
		}

		@Override
		public void stop() {
		}
		
	}

}
