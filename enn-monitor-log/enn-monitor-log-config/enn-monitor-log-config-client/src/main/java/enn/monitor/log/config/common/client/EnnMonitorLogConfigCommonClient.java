package enn.monitor.log.config.common.client;

import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse;
import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest;
import enn.monitor.log.config.common.server.EnnMonitorLogConfigCommonServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigCommonClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigCommonServerGrpc.EnnMonitorLogConfigCommonServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigCommonClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigCommonServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigBatchIdGetResponse getBatchId(EnnMonitorLogConfigCommonBatchIdGetRequest request) {
		return futureBlockingStub.getBatchId(request);
	}
	
}
