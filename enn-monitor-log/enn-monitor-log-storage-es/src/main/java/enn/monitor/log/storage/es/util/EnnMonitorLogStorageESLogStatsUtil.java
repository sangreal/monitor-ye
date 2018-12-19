package enn.monitor.log.storage.es.util;

import java.util.HashMap;
import java.util.Map;

import enn.monitor.framework.log.proto.ESLog;

public class EnnMonitorLogStorageESLogStatsUtil {
	
	private static final String LogClusterName 		= "logClusterName";
	private static final String LogNodeName 		= "logNodeName";
	private static final String LogAppName 			= "logAppName";
	private static final String LogNameSpace 		= "logNameSpace";
	private static final String LogPodName 			= "logPodName";
	private static final String LogToken 			= "logToken";
	
	public static String getMetricsName() {
		return "logStats";
	}
	
	public static String generatorKey(ESLog logData) {
    	StringBuilder key = new StringBuilder();
    	
    	if (logData.getClusterName() != null && logData.getClusterName().equals("") == false) {
    		key.append(logData.getClusterName());
    		key.append("-");
    	}
    	
    	if (logData.getNodeName() != null && logData.getNodeName().equals("") == false) {
    		key.append(logData.getNodeName());
    		key.append("-");
    	}
    	
    	if (logData.getAppName() != null && logData.getAppName().equals("") == false) {
    		key.append(logData.getAppName());
    		key.append("-");
    	}
    	
    	if (logData.getNameSpace() != null && logData.getNameSpace().equals("") == false) {
    		key.append(logData.getNameSpace());
    		key.append("-");
    	}
    	
    	if (logData.getPodName() != null && logData.getPodName().equals("") == false) {
    		key.append(logData.getPodName());
    		key.append("-");
    	}
    	
    	if (logData.getToken() != null && logData.getToken().equals("") == false) {
    		key.append(logData.getToken());
    	}
    	
    	return key.toString();
    }
	
	public static Map<String, String> generatorTagsMap(ESLog logData) {
    	Map<String, String> tagMap = new HashMap<String, String>();
    	
    	if (logData.getClusterName() != null && logData.getClusterName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogClusterName, logData.getClusterName());
    	}
    	
    	if (logData.getNodeName() != null && logData.getNodeName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogNodeName, logData.getNodeName());
    	}
    	
    	if (logData.getAppName() != null && logData.getAppName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogAppName, logData.getAppName());
    	}
    	
    	if (logData.getNameSpace() != null && logData.getNameSpace().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogNameSpace, logData.getNameSpace());
    	}
    	
    	if (logData.getPodName() != null && logData.getPodName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogPodName, logData.getPodName());
    	}
    	
    	if (logData.getToken() != null && logData.getToken().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogStatsUtil.LogToken, logData.getToken());
    	}
    	
    	return tagMap;
    }

}
