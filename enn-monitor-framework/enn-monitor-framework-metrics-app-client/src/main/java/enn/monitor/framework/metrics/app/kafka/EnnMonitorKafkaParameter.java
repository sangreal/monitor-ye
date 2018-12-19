package enn.monitor.framework.metrics.app.kafka;

import enn.monitor.framework.kafka.EnnKafkaMsgValueFilter;

public class EnnMonitorKafkaParameter {
	
	private String kafkaUrl = null;
	private String topics = null;
	private EnnKafkaMsgValueFilter ennKafkaMsgValueFilter;
	
	public EnnMonitorKafkaParameter(String kafkaUrl, String topics, EnnKafkaMsgValueFilter ennKafkaMsgValueFilter) {
		this.kafkaUrl = kafkaUrl;
		this.topics = topics;
		this.ennKafkaMsgValueFilter = ennKafkaMsgValueFilter;
	}

	public String getKafkaUrl() {
		return kafkaUrl;
	}

	public void setKafkaUrl(String kafkaUrl) {
		this.kafkaUrl = kafkaUrl;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public EnnKafkaMsgValueFilter getEnnKafkaMsgValueFilter() {
		return ennKafkaMsgValueFilter;
	}

	public void setEnnKafkaMsgValueFilter(EnnKafkaMsgValueFilter ennKafkaMsgValueFilter) {
		this.ennKafkaMsgValueFilter = ennKafkaMsgValueFilter;
	}

}
