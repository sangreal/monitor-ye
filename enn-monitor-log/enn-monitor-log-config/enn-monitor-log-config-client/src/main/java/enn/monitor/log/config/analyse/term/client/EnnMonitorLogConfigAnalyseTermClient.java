package enn.monitor.log.config.analyse.term.client;

import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse;
import enn.monitor.log.config.analyse.term.server.EnnMonitorLogConfigAnalyseTermServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigAnalyseTermClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigAnalyseTermServerGrpc.EnnMonitorLogConfigAnalyseTermServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigAnalyseTermClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigAnalyseTermServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigAnalyseTermGetResponse getAnalyseTerm(EnnMonitorLogConfigAnalyseTermGetRequest request) {
		return futureBlockingStub.getAnalyseTerm(request);
	}
	
	public EnnMonitorLogConfigAnalyseTermCreateResponse createAnalyseTerm(EnnMonitorLogConfigAnalyseTermCreateRequest request) {
		return futureBlockingStub.createAnalyseTerm(request);
	}
	
	public EnnMonitorLogConfigAnalyseTermUpdateResponse updateAnalyseTerm(EnnMonitorLogConfigAnalyseTermUpdateRequest request) {
		return futureBlockingStub.updateAnalyseTerm(request);
	}
	
	public EnnMonitorLogConfigAnalyseTermDeleteResponse deleteAnalyseTerm(long id) {
		EnnMonitorLogConfigAnalyseTermDeleteRequest request = EnnMonitorLogConfigAnalyseTermDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteAnalyseTerm(request);
	}
	
}
