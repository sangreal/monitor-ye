package enn.monitor.config.service.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse;
import enn.monitor.config.service.server.EnnMonitorConfigServiceServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorConfigServiceClient {

	private ManagedChannel channel;
	private EnnMonitorConfigServiceServerGrpc.EnnMonitorConfigServiceServerBlockingStub futureBlockingStub = null;

	public EnnMonitorConfigServiceClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorConfigServiceServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorConfigServiceGetResponse getService(EnnMonitorConfigServiceGetRequest request) {
		return futureBlockingStub.getService(request);
	}
	
	public EnnMonitorConfigServiceCountResponse countService(EnnMonitorConfigServiceGetRequest request) {
		return futureBlockingStub.countService(request);
	}
	
	public EnnMonitorConfigServiceCreateResponse createService(EnnMonitorConfigServiceCreateRequest request) {
		return futureBlockingStub.createService(request);
	}
	
	public EnnMonitorConfigServiceUpdateResponse updateService(EnnMonitorConfigServiceUpdateRequest request) {
		return futureBlockingStub.updateService(request);
	}
	
	public EnnMonitorConfigServiceDeleteResponse deleteService(long id) {
		EnnMonitorConfigServiceDeleteRequest request = EnnMonitorConfigServiceDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteService(request);
	}
	
	public EnnMonitorConfigServiceTokenGeneratorResponse generateServiceToken(String userId) {
		EnnMonitorConfigServiceTokenGeneratorRequest request = EnnMonitorConfigServiceTokenGeneratorRequest.newBuilder().setUserdId(userId).build();
		return futureBlockingStub.generateServiceToken(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorConfigServiceGetMaxDeletedRequest request = EnnMonitorConfigServiceGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorConfigServiceGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigServiceGetValidDataListRequest request = EnnMonitorConfigServiceGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigServiceGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getServiceTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigServiceGetDeletedDataListRequest request = EnnMonitorConfigServiceGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigServiceGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}

	public EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse checkServiceIsExistedOrNot(EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request) {
		return futureBlockingStub.checkServiceIsExistedOrNot(request);
	}

}
