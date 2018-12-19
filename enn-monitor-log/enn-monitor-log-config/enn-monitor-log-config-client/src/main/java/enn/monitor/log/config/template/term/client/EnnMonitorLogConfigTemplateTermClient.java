package enn.monitor.log.config.template.term.client;

import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse;
import enn.monitor.log.config.template.term.server.EnnMonitorLogConfigTemplateTermServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigTemplateTermClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigTemplateTermServerGrpc.EnnMonitorLogConfigTemplateTermServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigTemplateTermClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigTemplateTermServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigTemplateTermGetResponse getTemplateTerm(EnnMonitorLogConfigTemplateTermGetRequest request) {
		return futureBlockingStub.getTemplateTerm(request);
	}
	
	public EnnMonitorLogConfigTemplateTermCreateResponse createTemplateTerm(EnnMonitorLogConfigTemplateTermCreateRequest request) {
		return futureBlockingStub.createTemplateTerm(request);
	}
	
	public EnnMonitorLogConfigTemplateTermDeleteResponse deleteTemplateTerm(long batchId) {
		EnnMonitorLogConfigTemplateTermDeleteRequest request = EnnMonitorLogConfigTemplateTermDeleteRequest.newBuilder().setBatchId(batchId).build();
		return futureBlockingStub.deleteTemplateTerm(request);
	}
	
}
