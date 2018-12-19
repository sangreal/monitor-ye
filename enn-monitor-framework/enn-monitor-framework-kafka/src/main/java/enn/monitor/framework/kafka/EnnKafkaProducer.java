package enn.monitor.framework.kafka;

import java.util.Properties;

import enn.monitor.framework.tracing.EnnTracer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class EnnKafkaProducer<K, V> {
	//private static final Logger logger = LogManager.getLogger();
	
	private Producer<K, V> producer;
	private Producer<K, V> wrappedProducer;
	private String topic;
	
	private EnnKafkaMsgValueFilter<V> ennKafkaMsgValueFilter = null;

	public EnnKafkaProducer(Properties props, String topic) {
		this.producer = new KafkaProducer<>(props);
		this.topic = topic;
	}
	
	public void setEnnKafkaValueFilter(EnnKafkaMsgValueFilter<V> ennKafkaValueFilter) {
		this.ennKafkaMsgValueFilter = ennKafkaValueFilter;
	}

	public void send(K messageKey, V messageValue) {
		send(messageKey, messageValue, true);
	}

	public void send(K messageKey, V messageValue, boolean sendSpan) {
		if (ennKafkaMsgValueFilter != null) {
			messageValue = ennKafkaMsgValueFilter.filterValue(messageValue);
		}
		if (sendSpan) {
			wrappedProducer.send(new ProducerRecord<>(topic, messageKey, messageValue));
		} else {
			producer.send(new ProducerRecord<>(topic, messageKey, messageValue));
		}
	}

	public void close() {
		if (producer != null) {
			producer.flush();
			producer.close();
		}
	}

	public void wrapTracing(EnnTracer ennTracer) {
		wrappedProducer = ennTracer.wrapKafkaProducer(producer);
	}

	public static class Builder {
	    private String url;
	    private String keySerializer;
	    private String valueSerializer;
	    private String topic;

	    public Builder() {
	    	url = "localhost:9092";
	    	keySerializer = "org.apache.kafka.common.serialization.ByteArraySerializer";
	    	valueSerializer = "org.apache.kafka.common.serialization.ByteArraySerializer";
	    }

	    public EnnKafkaProducer.Builder setUrl(String url) {
	    	this.url = url;
	    	return this;
	    }

	    public EnnKafkaProducer.Builder setKeySerializer(String serializer) {
	    	this.keySerializer = serializer;
	    	return this;
	    }

	    public EnnKafkaProducer.Builder setValueSerializer(String serializer) {
	    	this.valueSerializer = serializer;
	    	return this;
	    }

	    public EnnKafkaProducer.Builder setTopic(String topic) {
	    	this.topic = topic;
	    	return this;
	    }

	    public EnnKafkaProducer<?, ?> build() {
	    	Properties props = new Properties();
	    	props.put("bootstrap.servers", url);
	    	props.put("key.serializer", keySerializer);
	    	props.put("value.serializer", valueSerializer);
	    	props.put("retries", 5);
	    	props.put("retry.backoff.ms", 300);
	    	return new EnnKafkaProducer<Object, Object>(props, topic);
	    }
	}
}
