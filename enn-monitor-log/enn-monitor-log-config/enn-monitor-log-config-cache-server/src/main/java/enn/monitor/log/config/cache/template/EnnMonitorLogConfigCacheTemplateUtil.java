package enn.monitor.log.config.cache.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnnMonitorLogConfigCacheTemplateUtil {
	
	private static Map<String, Long> template2TagIdMap = new ConcurrentHashMap<String, Long>();
	private static Map<Long, String> templateMap = new ConcurrentHashMap<Long, String>();
	
	public static void add(Long id, String template, Long eventId) {
		if (eventId > 0) {
			if (templateMap.containsKey(id) == true) {
				delete(id);
			}
			
			template2TagIdMap.put(template, eventId);
			templateMap.put(id, template);
		} else {
			delete(id);
		}
	}
	
	public static void delete(long id) {
		if (templateMap.containsKey(id) == true) {
			template2TagIdMap.remove(templateMap.get(id));
			templateMap.remove(id);
		}
	}
	
	public static Long getEventId(String template) {
		return template2TagIdMap.get(template);
	}

}
