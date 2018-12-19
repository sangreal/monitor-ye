package enn.monitor.log.analyse.storge.client;

import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse;
import enn.monitor.log.analyse.storage.server.EnnMonitorLogAnalyseStorageServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogAnalyseStorageClient {

	private ManagedChannel channel;
	private EnnMonitorLogAnalyseStorageServerGrpc.EnnMonitorLogAnalyseStorageServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogAnalyseStorageClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogAnalyseStorageServerGrpc.newBlockingStub(channel);
	}
	
	public EnnMonitorLogAnalyseStorageSearchResponse searchNN(EnnMonitorLogAnalyseStorageSearchRequest request) {
		return futureBlockingStub.searchNN(request);
	}

	public EnnMonitorLogAnalyseStorageCreateResponse createNN(EnnMonitorLogAnalyseStorageCreateRequest request) {
		return futureBlockingStub.createNN(request);
	}
	
	public EnnMonitorLogAnalyseStorageLastestNNDataResponse getLastestNNData(EnnMonitorLogAnalyseStorageLastestNNDataRequest request) {
		return futureBlockingStub.getLastestNNData(request);
	}
	
}
