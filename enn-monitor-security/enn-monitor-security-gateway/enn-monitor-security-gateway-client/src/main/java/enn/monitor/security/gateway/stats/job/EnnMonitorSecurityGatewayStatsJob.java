package enn.monitor.security.gateway.stats.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.common.constval.OpentsdbMetricsConst;
import enn.monitor.framework.common.opentsdb.OpentsdbJsonWithLong;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.log.channel.ChannelWriteTask;

public abstract class EnnMonitorSecurityGatewayStatsJob extends ChannelWriteTask {
	
	private static final Logger logger = LogManager.getLogger();
	
	private String nameSpace = null;
	private String podName = null;
	private String token = null;
	
	private Gson gson = new Gson();
	
	private Map<String, Map<String, String>> keyTagsMap = new HashMap<String, Map<String, String>>();
	private Map<String, String> metricsNameMap = new HashMap<String, String>();
	private Map<String, Long> countMap = new HashMap<String, Long>();
	private long lastUpdateMills = -1l;
	private long frequency;
	
	abstract protected void put(String source, String token, String json);
	
	public EnnMonitorSecurityGatewayStatsJob(String host, int port, String token, String nameSpace, String podName, long frequency) {
		super(50);
		
		this.nameSpace = nameSpace;
		this.podName = podName;
		this.token = token;
		
		this.frequency = frequency * 1000;
		
		lastUpdateMills = System.currentTimeMillis();
	}

	@Override
	protected void operator(ChannelWriteData stockWriteData) throws Exception {
		long currentMills = 0l;
		EnnMonitorSecurityGatewayStatsOpType opType = null;
		
		EnnMonitorSecurityGatewayStatsJobData data = null;
		
		String key = null;
		
		long count = -1;
		long value = -1;
		
		key = stockWriteData.getKey();
		
		opType = (EnnMonitorSecurityGatewayStatsOpType) stockWriteData.getOpEnum();
		switch (opType) {
		case Register:
			data = (EnnMonitorSecurityGatewayStatsJobData) stockWriteData.getObject();
			data.tagsMap.put(OpentsdbMetricsConst.NAMESPACE, nameSpace);
			data.tagsMap.put(OpentsdbMetricsConst.PODNAME, podName);
			keyTagsMap.put(key, data.tagsMap);
			metricsNameMap.put(key, data.metricsName);
			break;
		case Count:
			count = (long) stockWriteData.getObject();
			if (countMap.get(key) != null) {
				countMap.put(key, countMap.get(key) + count);
			} else {
				countMap.put(key, count);
			}
			break;
		case Set:
			value = (long) stockWriteData.getObject();
			countMap.put(key, value);
			break;
		default:
			logger.error("unexpect opType, it is " + opType.name());
			break;
		}
		
		currentMills = System.currentTimeMillis();
		if (currentMills - lastUpdateMills >= frequency) {
			send(currentMills);
		}
	}
	
	protected void operatorNull() throws Exception {
		long currentMills = System.currentTimeMillis();
		
		if (currentMills - lastUpdateMills >= frequency) {
			send(currentMills);
		}
	}
	
	private void send(long currentMills) throws Exception {
		OpentsdbJsonWithLong opentsdb = null;
		
		for (Entry<String, Long> entry : countMap.entrySet()) {
			opentsdb = new OpentsdbJsonWithLong();
			
			opentsdb.setTimestamp(currentMills);
			opentsdb.setTags(keyTagsMap.get(entry.getKey()));
			opentsdb.setValue(metricsNameMap.get(entry.getKey()), entry.getValue());
			
			put("monitor-app", token, gson.toJson(opentsdb));
		}
		
		countMap.clear();
		
		lastUpdateMills = currentMills;
	}
	
	public static class EnnMonitorSecurityGatewayStatsJobData {
		public String metricsName = null;
		public Map<String, String> tagsMap = null;
	}

}
