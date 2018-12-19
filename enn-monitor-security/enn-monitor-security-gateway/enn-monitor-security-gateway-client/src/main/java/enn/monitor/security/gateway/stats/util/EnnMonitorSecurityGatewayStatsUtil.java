package enn.monitor.security.gateway.stats.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.security.gateway.grpc.client.EnnMonitorSecurityGatewayGrpcClient;
import enn.monitor.security.gateway.http.client.EnnMonitorSecurityGatewayHttpClient;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest;
import enn.monitor.security.gateway.stats.job.EnnMonitorSecurityGatewayStatsJob;
import enn.monitor.security.gateway.stats.job.EnnMonitorSecurityGatewayStatsJob.EnnMonitorSecurityGatewayStatsJobData;
import enn.monitor.security.gateway.stats.job.EnnMonitorSecurityGatewayStatsOpType;

public class EnnMonitorSecurityGatewayStatsUtil {
	
	private volatile static EnnMonitorSecurityGatewayStatsJob job = null;
	
	public static void init(String host, int port, String token, long frequency, boolean isHttp) throws Exception {
		if (job == null) {
			synchronized (EnnMonitorSecurityGatewayStatsUtil.class) {
				if (job == null) {
					if (isHttp == true) {
						job = new EnnMonitorSecurityGatewayStatsJobHttp(host, port, token, EnnMonitorEnvAppUtil.getNameSpace(), EnnMonitorEnvAppUtil.getPodName(), frequency);
					} else {
						job = new EnnMonitorSecurityGatewayStatsJobGrpc(host, port, token, EnnMonitorEnvAppUtil.getNameSpace(), EnnMonitorEnvAppUtil.getPodName(), frequency);
					}
					
					job.start();
				}
			}
		}
	}
	
	public static void register(String key, String metricsName, Map<String, String> tagsMap) throws Exception {
		EnnMonitorSecurityGatewayStatsJobData data = new EnnMonitorSecurityGatewayStatsJobData();
		ChannelWriteData channelWriteData = new ChannelWriteData();
		
		channelWriteData.setOpEnum(EnnMonitorSecurityGatewayStatsOpType.Register);
		channelWriteData.setKey(key);
		data.metricsName = metricsName;
		data.tagsMap = tagsMap;
		channelWriteData.setObject(data);
		
		job.write(channelWriteData);
	}
	
	public static void count(String key, long counts) throws Exception {
		ChannelWriteData channelWriteData = new ChannelWriteData();
		
		channelWriteData.setOpEnum(EnnMonitorSecurityGatewayStatsOpType.Count);
		channelWriteData.setKey(key);
		channelWriteData.setObject(counts);
		
		job.write(channelWriteData);
	}
	
	public static void count(String key) throws Exception {
		ChannelWriteData channelWriteData = new ChannelWriteData(EnnMonitorSecurityGatewayStatsOpType.Count, 1l);
		
		channelWriteData.setOpEnum(EnnMonitorSecurityGatewayStatsOpType.Count);
		channelWriteData.setKey(key);
		channelWriteData.setObject(1l);
		
		job.write(channelWriteData);
	}
	
	public static void set(String key, long value) throws Exception {
		ChannelWriteData channelWriteData = new ChannelWriteData();
		
		channelWriteData.setOpEnum(EnnMonitorSecurityGatewayStatsOpType.Set);
		channelWriteData.setKey(key);
		channelWriteData.setObject(value);
		
		job.write(channelWriteData);
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> tagsMap = null;
		
		EnnMonitorSecurityGatewayStatsUtil.init("10.19.248.200", 30112, "test", 1l, true);
		
		tagsMap = new HashMap<String, String>();
		tagsMap.put("tag", "1");
		EnnMonitorSecurityGatewayStatsUtil.register("1", "1", tagsMap);
		tagsMap = new HashMap<String, String>();
		tagsMap.put("tag", "2");
		EnnMonitorSecurityGatewayStatsUtil.register("2", "1", tagsMap);
		tagsMap = new HashMap<String, String>();
		tagsMap.put("tag", "3");
		EnnMonitorSecurityGatewayStatsUtil.register("3", "1", tagsMap);
		while (true) {
			EnnMonitorSecurityGatewayStatsUtil.count("1", 1);
			EnnMonitorSecurityGatewayStatsUtil.count("2", 2);
			EnnMonitorSecurityGatewayStatsUtil.count("3", 3);
			
			Thread.sleep(1);
		}
	}
	
	private static class EnnMonitorSecurityGatewayStatsJobGrpc extends EnnMonitorSecurityGatewayStatsJob {
		
		private EnnMonitorSecurityGatewayGrpcClient client = null;

		public EnnMonitorSecurityGatewayStatsJobGrpc(String host, int port, String token, String nameSpace,
				String podName, long frequency) {
			super(host, port, token, nameSpace, podName, frequency);
			client = new EnnMonitorSecurityGatewayGrpcClient(host, port);
		}

		@Override
		protected void put(String source, String token, String json) {
			client.put(EnnMonitorSecurityGatewayRequest.newBuilder()
					.setSource("monitor-app")
					.setToken(token)
					.setJsonList(json)
					.build()
					);
		}
		
	}
	
	private static class EnnMonitorSecurityGatewayStatsJobHttp extends EnnMonitorSecurityGatewayStatsJob {
		
		private EnnMonitorSecurityGatewayHttpClient client = null;
		
		private static final Logger logger = LogManager.getLogger();

		public EnnMonitorSecurityGatewayStatsJobHttp(String host, int port, String token, String nameSpace,
				String podName, long frequency) {
			super(host, port, token, nameSpace, podName, frequency);
			client = new EnnMonitorSecurityGatewayHttpClient(host, port);
		}

		@Override
		protected void put(String source, String token, String json) {
			try {
				client.put(source, token, json);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
	}

}
