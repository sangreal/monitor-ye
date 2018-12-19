package enn.monitor.alarm.config.email.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.alarm.config.Server.EnnMonitorAlarmConfigEmailServerGrpc;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse;
import enn.monitor.framework.log.grpc.EnnMonitorGrpcClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorAlarmConfigEmailClient {

	private ManagedChannel channel;
	private EnnMonitorAlarmConfigEmailServerGrpc.EnnMonitorAlarmConfigEmailServerBlockingStub futureBlockingStub = null;

	public EnnMonitorAlarmConfigEmailClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.intercept(EnnMonitorGrpcClientInterceptor.create())
				.build();
		futureBlockingStub = EnnMonitorAlarmConfigEmailServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorAlarmConfigEmailGetResponse getEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailGetRequest request) {
		return futureBlockingStub.getEnnMonitorAlarmConfigEmail(request);
	}
	
	public EnnMonitorAlarmConfigEmailCreateResponse createEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailCreateRequest request) {
		return futureBlockingStub.createEnnMonitorAlarmConfigEmail(request);
	}
	
	public EnnMonitorAlarmConfigEmailUpdateResponse updateEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailUpdateRequest request) {
		return futureBlockingStub.updateEnnMonitorAlarmConfigEmail(request);
	}
	
	public EnnMonitorAlarmConfigEmailDeleteResponse deleteEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailDeleteRequest request) {
		return futureBlockingStub.deleteEnnMonitorAlarmConfigEmail(request);
	}
	
	public long getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo() throws Exception {
		EnnMonitorAlarmConfigEmailMaxDeletedRequest request = EnnMonitorAlarmConfigEmailMaxDeletedRequest.newBuilder().build();
		EnnMonitorAlarmConfigEmailGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getEnnMonitorAlarmConfigEmailTableList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorAlarmConfigEmailGetValidRequest request = EnnMonitorAlarmConfigEmailGetValidRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorAlarmConfigEmailGetValidResponse response = futureBlockingStub.getEnnMonitorAlarmConfigEmailValid(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getEnnMonitorAlarmConfigEmailTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getEnnMonitorAlarmConfigEmailGetDeletedList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorAlarmConfigEmailGetDeletedRequest request = EnnMonitorAlarmConfigEmailGetDeletedRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorAlarmConfigEmailGetDeletedResponse response = futureBlockingStub.getEnnMonitorAlarmConfigEmailDeleted(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getEnnMonitorAlarmConfigEmailGetDeletedListList());
		}
		
		return objectList;
	}

}
