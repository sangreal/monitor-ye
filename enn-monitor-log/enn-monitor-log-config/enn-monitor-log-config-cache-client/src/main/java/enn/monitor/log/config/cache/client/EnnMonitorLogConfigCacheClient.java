package enn.monitor.log.config.cache.client;

import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse;
import enn.monitor.log.config.cache.server.EnnMonitorLogConfigCacheServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigCacheClient {
	
	private ManagedChannel channel;
	private EnnMonitorLogConfigCacheServerGrpc.EnnMonitorLogConfigCacheServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigCacheClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigCacheServerGrpc.newBlockingStub(channel);
	}
	
	public EnnMonitorLogConfigCacheResponse getTag(EnnMonitorLogConfigCacheRequest request) {
		return futureBlockingStub.getTagByTemplateKey(request);
	}
	
	public EnnMonitorLogConfigCacheTagIdResponse getTagId(EnnMonitorLogConfigCacheTagIdRequest request) {
		return futureBlockingStub.getTagIdByTagKey(request);
	}
	
	public static void main(String[] args) throws Exception {
		EnnMonitorLogConfigCacheClient client = new EnnMonitorLogConfigCacheClient("10.19.248.200", 29307);
		EnnMonitorLogConfigCacheTagIdResponse response = client.getTagId(EnnMonitorLogConfigCacheTagIdRequest.newBuilder().setTag("java_error_stack_info").build());
		
		System.out.println(response.getTagId());
	}

}
