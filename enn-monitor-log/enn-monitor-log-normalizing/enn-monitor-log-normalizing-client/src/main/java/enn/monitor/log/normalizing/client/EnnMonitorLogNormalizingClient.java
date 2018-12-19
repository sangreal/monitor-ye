package enn.monitor.log.normalizing.client;

import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingRequest;
import enn.monitor.log.normalizing.parameters.EnnMonitorLogNormalizingResponse;
import enn.monitor.log.normalizing.server.EnnMonitorLogNormalizingServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogNormalizingClient {

	private ManagedChannel channel;
	private EnnMonitorLogNormalizingServerGrpc.EnnMonitorLogNormalizingServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogNormalizingClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogNormalizingServerGrpc.newBlockingStub(channel);
	}
	
	public EnnMonitorLogNormalizingResponse normalizing(EnnMonitorLogNormalizingRequest request) {
		return futureBlockingStub.normalizing(request);
	}
	
}
