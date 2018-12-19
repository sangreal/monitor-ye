package enn.monitor.alarm.ticket.grpc.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.alarm.ticket.Server.EnnMonitorAlarmTicketServerGrpc;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse;
import enn.monitor.framework.log.grpc.EnnMonitorGrpcClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorAlarmTicketGrpcClient {

	/*
	rpc getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest) returns (EnnMonitorAlarmTicketGetResponse) {}
	rpc createEnnMonitorAlarmTicket(EnnMonitorAlarmTicketCreateRequest) returns (EnnMonitorAlarmTicketCreateResponse) {}
	rpc updateEnnMonitorAlarmTicket(EnnMonitorAlarmTicketUpdateRequest) returns (EnnMonitorAlarmTicketUpdateResponse) {}
	rpc deleteEnnMonitorAlarmTicket(EnnMonitorAlarmTicketDeleteRequest) returns (EnnMonitorAlarmTicketDeleteResponse) {}
	rpc getEnnMonitorAlarmTicketMaxDeletedSeqNo(EnnMonitorAlarmTicketGetMaxDeletedRequest) returns (EnnMonitorAlarmTicketGetMaxDeletedResponse) {}
	rpc getEnnMonitorAlarmTicketValid(EnnMonitorAlarmTicketGetValidRequest) returns (EnnMonitorAlarmTicketGetValidResponse) {}
	rpc getEnnMonitorAlarmTicketDeleted(EnnMonitorAlarmTicketGetDeletedRequest) returns (EnnMonitorAlarmTicketGetDeletedResponse) {}
	
	rpc updateEnnMonitorAlarmTicketTransformState(EnnMonitorAlarmTicketTransformState) returns (EnnMonitorAlarmTicketTransformStateResponse) {}
	 */
	
	private ManagedChannel channel;
	private EnnMonitorAlarmTicketServerGrpc.EnnMonitorAlarmTicketServerBlockingStub futureBlockingStub = null;

	public EnnMonitorAlarmTicketGrpcClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.intercept(EnnMonitorGrpcClientInterceptor.create())
				.build();
		futureBlockingStub = EnnMonitorAlarmTicketServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorAlarmTicketGetResponse getEnnMonitorAlarmTicket(EnnMonitorAlarmTicketGetRequest request) {
		return futureBlockingStub.getEnnMonitorAlarmTicket(request);
	}
	
	public EnnMonitorAlarmTicketCreateResponse createEnnMonitorAlarmTicket(EnnMonitorAlarmTicketCreateRequest request) {
		return futureBlockingStub.createEnnMonitorAlarmTicket(request);
	}
	
	public EnnMonitorAlarmTicketUpdateResponse updateEnnMonitorAlarmTicket(EnnMonitorAlarmTicketUpdateRequest request) {
		return futureBlockingStub.updateEnnMonitorAlarmTicket(request);
	}
	
	public EnnMonitorAlarmTicketDeleteResponse deleteEnnMonitorAlarmTicket(EnnMonitorAlarmTicketDeleteRequest request) {
		return futureBlockingStub.deleteEnnMonitorAlarmTicket(request);
	}
	
	public long getEnnMonitorAlarmTicketMaxDeletedSeqNo() throws Exception {
		EnnMonitorAlarmTicketGetMaxDeletedRequest request = EnnMonitorAlarmTicketGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorAlarmTicketGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getEnnMonitorAlarmTicketMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getEnnMonitorAlarmTicketTableList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorAlarmTicketGetValidRequest request = EnnMonitorAlarmTicketGetValidRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorAlarmTicketGetValidResponse response = futureBlockingStub.getEnnMonitorAlarmTicketValid(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getEnnMonitorAlarmTicketTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getEnnMonitorAlarmTicketGetDeletedList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorAlarmTicketGetDeletedRequest request = EnnMonitorAlarmTicketGetDeletedRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorAlarmTicketGetDeletedResponse response = futureBlockingStub.getEnnMonitorAlarmTicketDeleted(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getEnnMonitorAlarmTicketGetDeletedListList());
		}
		
		return objectList;
	}
	
	public EnnMonitorAlarmTicketTransformStateResponse updateEnnMonitorAlarmTicketTransformState(
			EnnMonitorAlarmTicketTransformState transformState) throws Exception {
		return futureBlockingStub.updateEnnMonitorAlarmTicketTransformState(transformState);
	}

}
