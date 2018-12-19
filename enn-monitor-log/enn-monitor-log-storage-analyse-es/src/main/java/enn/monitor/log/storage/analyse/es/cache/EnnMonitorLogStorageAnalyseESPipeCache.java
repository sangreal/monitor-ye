package enn.monitor.log.storage.analyse.es.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import enn.monitor.log.analyse.data.EnnMonitorLogAnalyseData;

public class EnnMonitorLogStorageAnalyseESPipeCache {
	
	private static Map<Long, EnnMonitorLogAnalyseData> tokenIdMap = new ConcurrentHashMap<Long, EnnMonitorLogAnalyseData>();

	public static void add(long tokenId, EnnMonitorLogAnalyseData data) {
		tokenIdMap.put(tokenId, data);
	}
	
	public static EnnMonitorLogAnalyseData get(long tokenId) {
		return tokenIdMap.get(tokenId);
	}
	
}
