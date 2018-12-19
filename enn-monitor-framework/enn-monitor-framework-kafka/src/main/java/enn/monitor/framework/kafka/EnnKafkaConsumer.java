package enn.monitor.framework.kafka;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import enn.monitor.framework.tracing.EnnTracer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnnKafkaConsumer<K, V> {
	private static final Logger logger = LogManager.getLogger();
  
	protected EnnKafkaMsgProcessor<K, V> msgProcessor;
	protected ExecutorService executorService = null;
	private Consumer<K, V> consumer;
	
	private EnnKafkaMsgValueFilter<V> ennKafkaMsgValueFilter = null;
	
	private AtomicBoolean isRun = new AtomicBoolean(true);

	public EnnKafkaConsumer(Properties props, String topic) {
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(topic));
	}

	public void start() {
		isRun.set(true);
		executorService = Executors.newSingleThreadExecutor();
		executorService.execute(
				new KafkaConsumerRunnable()
				);
	}
	
	public void start(EnnKafkaCallback callback, Object object) {
		isRun.set(true);
		executorService = Executors.newSingleThreadExecutor();
		executorService.execute(
    		new KafkaConsumerRunnable(callback, object)
  		);
	}

	public void stop() {
		isRun.set(false);
	}

	public void setProcessor(EnnKafkaMsgProcessor<K, V> msgProcessor) {
		this.msgProcessor = msgProcessor;
	}
	
	public void setEnnKafkaValueFilter(EnnKafkaMsgValueFilter<V> ennKafkaValueFilter) {
		this.ennKafkaMsgValueFilter = ennKafkaValueFilter;
	}

	public void releaseProcessor() {
		if (msgProcessor != null) {
			msgProcessor.stop();
			msgProcessor = null;
		}
	}

	public void wrapTracing(EnnTracer ennTracer) {
		consumer = ennTracer.wrapKafkaConsumer(consumer);
	}

	public static class Builder {
		private String url;
		private String topic;
		private String groupId;
		private String keyDeserializer;
		private String valueDeserializer;
		private String offsetReset;
		
		public Builder() {
			url = "localhost:9092";
			groupId = "micklongen";
			keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
			valueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer";
			offsetReset = "latest";
		}

		public EnnKafkaConsumer.Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public EnnKafkaConsumer.Builder setTopic(String topic) {
			this.topic = topic;
			return this;
		}

		public EnnKafkaConsumer.Builder setGroupId(String id) {
			this.groupId = id;
			return this;
		}

		public EnnKafkaConsumer.Builder setKeyDeserializer(String deserializer) {
			this.keyDeserializer = deserializer;
			return this;
		}

		public EnnKafkaConsumer.Builder setValueDeserializer(String deserializer) {
			this.valueDeserializer = deserializer;
			return this;
		}

		public EnnKafkaConsumer.Builder setOffsetReset(String offsetReset) {
			this.offsetReset = offsetReset;
			return this;
		}
		
		public EnnKafkaConsumer<?, ?> build() {
			Properties props = new Properties();
			props.put("bootstrap.servers", url);
			props.put("group.id", groupId);
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer", keyDeserializer);
			props.put("value.deserializer", valueDeserializer);
			props.put("auto.offset.reset", offsetReset);
			return new EnnKafkaConsumer<Object, Object>(props, topic);
		}
	}
	
	private class KafkaConsumerRunnable implements Runnable {
		
		private EnnKafkaCallback callback = null;
		private Object object = null;
		
		public KafkaConsumerRunnable() {
		}
		
		public KafkaConsumerRunnable(EnnKafkaCallback callback, Object object) {
			this.callback = callback;
			this.object = object;
		}

		@Override
		public void run() {
			boolean isContinue = true;
			
			while (isRun.get()) {
	            ConsumerRecords<K, V> records = consumer.poll(1000);
	            for (ConsumerRecord<K, V> record : records) {
	            	if (msgProcessor != null) {
	            		try {
	            			V value = record.value();
	            			if (ennKafkaMsgValueFilter != null) {
	            				value = ennKafkaMsgValueFilter.filterValue(value);
	            			}
	            			
	            			isContinue = msgProcessor.process(record.key(), value);
	            			if (isContinue == false) {
	            				if (callback != null) {
	            					callback.callback(object);
	            				}
	            				return;
	            			}
	            		} catch (Exception e) {
	            			logger.error(e.getMessage(), e);
	            		}
	            	}
	            }
			}
		}
		
	}
}
