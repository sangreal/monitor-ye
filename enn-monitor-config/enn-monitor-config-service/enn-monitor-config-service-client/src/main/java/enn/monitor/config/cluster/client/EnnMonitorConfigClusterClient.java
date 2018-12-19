package enn.monitor.config.cluster.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.config.cluster.cluster.EnnMonitorConfigClusterServerGrpc;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorConfigClusterClient {

	private ManagedChannel channel;
	private EnnMonitorConfigClusterServerGrpc.EnnMonitorConfigClusterServerBlockingStub futureBlockingStub = null;

	public EnnMonitorConfigClusterClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorConfigClusterServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorConfigClusterGetResponse getCluster(EnnMonitorConfigClusterGetRequest request) {
		return futureBlockingStub.getCluster(request);
	}
	
	public EnnMonitorConfigClusterCreateResponse createCluster(EnnMonitorConfigClusterCreateRequest request) {
		return futureBlockingStub.createCluster(request);
	}
	
	public EnnMonitorConfigClusterUpdateResponse updateCluster(EnnMonitorConfigClusterUpdateRequest request) {
		return futureBlockingStub.updateCluster(request);
	}
	
	public EnnMonitorConfigClusterDeleteResponse deleteCluster(long id, String lastUpdateUser) {
		EnnMonitorConfigClusterDeleteRequest request = EnnMonitorConfigClusterDeleteRequest.newBuilder().setId(id).setLastUpdateUser(lastUpdateUser).build();
		return futureBlockingStub.deleteCluster(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorConfigClusterGetMaxDeletedRequest request = EnnMonitorConfigClusterGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorConfigClusterGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigClusterGetValidDataListRequest request = EnnMonitorConfigClusterGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigClusterGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getClusterTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigClusterGetDeletedDataListRequest request = EnnMonitorConfigClusterGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigClusterGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}

	public EnnMonitorConfigClusterExistClusterNameOrNotResponse checkClusterIsExistedOrNot(EnnMonitorConfigClusterExistClusterNameOrNotRequest request) {
		return futureBlockingStub.checkClusterNameIsExistedOrNot(request);
	}

}
