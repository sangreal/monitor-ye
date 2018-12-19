package enn.monitor.framework.kafka;

public interface EnnKafkaMsgValueFilter<V> {

	public V filterValue(V value);

}
