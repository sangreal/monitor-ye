package enn.monitor.security.gateway.topic.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.kafka.EnnKafkaMsgValueFilter;
import enn.monitor.framework.kafka.EnnKafkaProducer;
import enn.monitor.security.gateway.trace.util.TraceManager;

public class EnnMonitorConfigBussinessTopicCache {
	
private static final Logger logger = LogManager.getLogger();
	
	private Map<String, EnnKafkaProducer<Long, String>> ennKafkaProducerMap = new ConcurrentHashMap<String, EnnKafkaProducer<Long, String>>();
	private Map<Long, String> idSourceMap = new ConcurrentHashMap<Long, String>();
	
	private String kafkaUrl = null;
	private EnnKafkaMsgValueFilter ennKafkaMsgValueFilter = null;
	
	public EnnMonitorConfigBussinessTopicCache(String kafkaUrl, EnnKafkaMsgValueFilter ennKafkaMsgValueFilter) {
		this.kafkaUrl = kafkaUrl;
		this.ennKafkaMsgValueFilter = ennKafkaMsgValueFilter;
	}
	
	public EnnKafkaProducer<Long, String> getEnnKafkaProducer(String source) {
		EnnKafkaProducer<Long, String> ennKafkaProducer = null;
		ennKafkaProducer = ennKafkaProducerMap.get(source);
		
		if (ennKafkaProducer == null) {
			logger.error("the topic is not exists, the source is " + source);
		}
		
		return ennKafkaProducer;
	}
	
	public void add(Long id, String source, String topic) {
		EnnKafkaProducer<Long, String> ennKafkaProducer = null;
		
		ennKafkaProducer = ennKafkaProducerMap.get(source);
		
		if (ennKafkaProducer != null) {
			ennKafkaProducer.close();
		}
		
		ennKafkaProducer = (EnnKafkaProducer<Long, String>) new EnnKafkaProducer.Builder()
                .setUrl(kafkaUrl)
                .setTopic(topic)
                .setKeySerializer("org.apache.kafka.common.serialization.LongSerializer")
                .setValueSerializer("org.apache.kafka.common.serialization.StringSerializer")
                .build();
		if (ennKafkaMsgValueFilter != null) {
			ennKafkaProducer.setEnnKafkaValueFilter(ennKafkaMsgValueFilter);
		}
		ennKafkaProducer.wrapTracing(TraceManager.getInstance());
		
		ennKafkaProducerMap.put(source, ennKafkaProducer);
		idSourceMap.put(id, source);
	}
	
	public void delete(Long id) {
		String source = null;
		EnnKafkaProducer<Long, String> ennKafkaProducer = null;
		
		source = idSourceMap.get(id);
		if (source == null) {
			return;
		}
		idSourceMap.remove(id);
		
		ennKafkaProducer = ennKafkaProducerMap.get(source);
		if (ennKafkaProducer != null) {
			ennKafkaProducerMap.remove(source);
			ennKafkaProducer.close();
		}
	}

}
