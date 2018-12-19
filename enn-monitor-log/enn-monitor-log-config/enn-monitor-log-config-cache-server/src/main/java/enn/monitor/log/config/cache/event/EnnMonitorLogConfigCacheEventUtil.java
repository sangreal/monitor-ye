package enn.monitor.log.config.cache.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnnMonitorLogConfigCacheEventUtil {
	
	private static Map<Long, String> eventIdMap = new ConcurrentHashMap<Long, String>();
	private static Map<String, Long> eventMap = new ConcurrentHashMap<String, Long>();
	
	public static void add(Long eventId, String event) {
		eventIdMap.put(eventId, event);
		eventMap.put(event, eventId);
	}
	
	public static void delete(Long eventId) {
		String event = null;
		
		event = eventIdMap.get(eventId);
		if (event != null) {
			eventIdMap.remove(eventId);
			eventMap.remove(event);
		}
	}
	
	public static String getEvent(long eventId) {
		return eventIdMap.get(eventId);
	}
	
	public static Long getEventId(String event) {
		return eventMap.get(event);
	}
	
}
