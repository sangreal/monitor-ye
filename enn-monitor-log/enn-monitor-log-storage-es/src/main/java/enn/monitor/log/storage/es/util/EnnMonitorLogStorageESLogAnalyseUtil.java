package enn.monitor.log.storage.es.util;

import java.util.HashMap;
import java.util.Map;

import enn.monitor.framework.log.proto.ESLog;

public class EnnMonitorLogStorageESLogAnalyseUtil {
	
	private static final String LogNodeName 		= "logNodeName";
	private static final String LogAppName 			= "logAppName";
	private static final String LogNameSpace 		= "logNameSpace";
	private static final String LogPodName 			= "logPodName";
	private static final String LogToken 			= "logToken";
	
	public static String getMetricsName() {
		return "logAnalyse";
	}
	
	public static String generatorKey(ESLog logData, String tag) {
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
    		key.append("-");
    	}
    	
    	if (tag != null && tag.equals("") == false) {
    		key.append(tag);
    	}
    	
    	return key.toString();
    }
	
	public static Map<String, String> generatorTagsMap(ESLog logData, String tag) {
    	Map<String, String> tagMap = new HashMap<String, String>();
    	
    	if (logData.getNodeName() != null && logData.getNodeName().equals("") == false) {
    		if (logData.getClusterName() != null && logData.getClusterName().equals("") == false) {
    			tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogNodeName, logData.getClusterName() + "-" + logData.getNodeName());
        	}
    		tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogNodeName, logData.getNodeName());
    	}
    	
    	if (logData.getAppName() != null && logData.getAppName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogAppName, logData.getAppName());
    	}
    	
    	if (logData.getNameSpace() != null && logData.getNameSpace().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogNameSpace, logData.getNameSpace());
    	}
    	
    	if (logData.getPodName() != null && logData.getPodName().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogPodName, logData.getPodName());
    	}
    	
    	if (logData.getToken() != null && logData.getToken().equals("") == false) {
    		tagMap.put(EnnMonitorLogStorageESLogAnalyseUtil.LogToken, logData.getToken());
    	}
    	
    	if (tag != null && tag.equals("") == false) {
    		tagMap.put("tag", tag);
    	}
    	
    	return tagMap;
    }

}
