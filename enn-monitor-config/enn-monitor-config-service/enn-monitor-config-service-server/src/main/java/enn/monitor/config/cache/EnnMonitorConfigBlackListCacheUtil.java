package enn.monitor.config.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnnMonitorConfigBlackListCacheUtil {
	
	private static Map<Long, String> clusterSet = new ConcurrentHashMap<Long, String>();
	private static Map<Long, String> serviceLineSet = new ConcurrentHashMap<Long, String>();
	
	public static void addClusterId(long id) {
		clusterSet.put(id, "");
	}
	
	public static void addServicLineId(long id) {
		serviceLineSet.put(id, "");
	}
	
	public static boolean containClusterId(long id) {
		return clusterSet.containsKey(id);
	}
	
	public static boolean containServiceLineId(long id) {
		return serviceLineSet.containsKey(id);
	}
	
}
