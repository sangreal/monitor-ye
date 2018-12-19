package enn.monitor.framework.kafka;

public interface EnnKafkaMsgProcessor<K, V> {
	
	public boolean process(K key, V value) throws Exception;
	public void stop();
	
}
