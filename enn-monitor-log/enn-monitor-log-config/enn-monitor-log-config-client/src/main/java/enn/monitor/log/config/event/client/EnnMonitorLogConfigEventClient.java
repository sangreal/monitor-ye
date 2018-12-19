package enn.monitor.log.config.event.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse;
import enn.monitor.log.config.event.server.EnnMonitorLogConfigEventServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigEventClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigEventServerGrpc.EnnMonitorLogConfigEventServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigEventClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigEventServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigEventGetResponse getEvent(EnnMonitorLogConfigEventGetRequest request) {
		return futureBlockingStub.getEvent(request);
	}
	
	public EnnMonitorLogConfigEventCreateResponse createEvent(EnnMonitorLogConfigEventCreateRequest request) {
		return futureBlockingStub.createEvent(request);
	}
	
	public EnnMonitorLogConfigEventUpdateResponse updateEvent(EnnMonitorLogConfigEventUpdateRequest request) {
		return futureBlockingStub.updateEvent(request);
	}
	
	public EnnMonitorLogConfigEventDeleteResponse deleteEvent(long id) {
		EnnMonitorLogConfigEventDeleteRequest request = EnnMonitorLogConfigEventDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteEvent(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorLogConfigEventGetMaxDeletedRequest request = EnnMonitorLogConfigEventGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorLogConfigEventGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		EnnMonitorLogConfigEventGetValidDataListRequest request = 
				EnnMonitorLogConfigEventGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		return getValidDataListInternal(request);
	}
	
	public List<Object> getValidDataList(EnnMonitorLogConfigEventGetValidDataListRequest request) throws Exception {
		return getValidDataListInternal(request);
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigEventGetDeletedDataListRequest request = EnnMonitorLogConfigEventGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorLogConfigEventGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}
	
	private List<Object> getValidDataListInternal(EnnMonitorLogConfigEventGetValidDataListRequest request) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigEventGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getEventTableList());
		}
		
		return objectList;
	}
}
