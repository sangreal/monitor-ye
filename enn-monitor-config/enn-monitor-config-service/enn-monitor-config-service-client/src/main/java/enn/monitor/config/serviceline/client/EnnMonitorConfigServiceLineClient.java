package enn.monitor.config.serviceline.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.config.serviceLine.parameters.*;
import enn.monitor.config.serviceLine.server.EnnMonitorConfigServiceLineServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorConfigServiceLineClient {
	
	private ManagedChannel channel;
	private EnnMonitorConfigServiceLineServerGrpc.EnnMonitorConfigServiceLineServerBlockingStub futureBlockingStub = null;

	public EnnMonitorConfigServiceLineClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorConfigServiceLineServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorConfigServiceLineGetResponse getServiceLine(EnnMonitorConfigServiceLineGetRequest request) {
		return futureBlockingStub.getServiceLine(request);
	}
	
	public EnnMonitorConfigServiceLineCreateResponse createServiceLine(EnnMonitorConfigServiceLineCreateRequest request) {
		return futureBlockingStub.createServiceLine(request);
	}
	
	public EnnMonitorConfigServiceLineUpdateResponse updateServiceLine(EnnMonitorConfigServiceLineUpdateRequest request) {
		return futureBlockingStub.updateServiceLine(request);
	}
	
	public EnnMonitorConfigServiceLineDeleteResponse deleteServiceLine(long id, String lastUpdateUser) {
		EnnMonitorConfigServiceLineDeleteRequest request = EnnMonitorConfigServiceLineDeleteRequest.newBuilder().setId(id).setLastUpdateUser(lastUpdateUser).build();
		return futureBlockingStub.deleteServiceLine(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorConfigServiceLineGetMaxDeletedRequest request = EnnMonitorConfigServiceLineGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorConfigServiceLineGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigServiceLineGetValidDataListRequest request = EnnMonitorConfigServiceLineGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigServiceLineGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getServiceLineTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigServiceLineGetDeletedDataListRequest request = EnnMonitorConfigServiceLineGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigServiceLineGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}

	public EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse checkServiceLineIsExistedOrNot(EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request) {
		return futureBlockingStub.checkServiceLineIsExistedOrNot(request);
	}

}
