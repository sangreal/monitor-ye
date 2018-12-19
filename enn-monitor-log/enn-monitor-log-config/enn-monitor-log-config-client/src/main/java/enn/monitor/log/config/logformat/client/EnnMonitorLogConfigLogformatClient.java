package enn.monitor.log.config.logformat.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse;
import enn.monitor.log.config.logformat.server.EnnMonitorLogConfigLogformatServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigLogformatClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigLogformatServerGrpc.EnnMonitorLogConfigLogformatServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigLogformatClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigLogformatServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigLogformatGetResponse getLogformat(EnnMonitorLogConfigLogformatGetRequest request) {
		return futureBlockingStub.getLogformat(request);
	}
	
	public EnnMonitorLogConfigLogformatCreateResponse createLogformat(EnnMonitorLogConfigLogformatCreateRequest request) {
		return futureBlockingStub.createLogformat(request);
	}
	
	public EnnMonitorLogConfigLogformatUpdateResponse updateLogformat(EnnMonitorLogConfigLogformatUpdateRequest request) {
		return futureBlockingStub.updateLogformat(request);
	}
	
	public EnnMonitorLogConfigLogformatDeleteResponse deleteLogformat(long id) {
		EnnMonitorLogConfigLogformatDeleteRequest request = EnnMonitorLogConfigLogformatDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteLogformat(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorLogConfigLogformatGetMaxDeletedRequest request = EnnMonitorLogConfigLogformatGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorLogConfigLogformatGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigLogformatGetValidDataListRequest request = EnnMonitorLogConfigLogformatGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorLogConfigLogformatGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getLogformatTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigLogformatGetDeletedDataListRequest request = EnnMonitorLogConfigLogformatGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorLogConfigLogformatGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}

}
