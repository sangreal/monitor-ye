package enn.monitor.config.business.topic.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse;
import enn.monitor.config.business.topic.server.EnnMonitorConfigBusinessTopicServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorConfigBusinessTopicClient {

	private ManagedChannel channel;
	private EnnMonitorConfigBusinessTopicServerGrpc.EnnMonitorConfigBusinessTopicServerBlockingStub futureBlockingStub = null;

	public EnnMonitorConfigBusinessTopicClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorConfigBusinessTopicServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorConfigBusinessTopicGetResponse getTopic(EnnMonitorConfigBusinessTopicGetRequest request) {
		return futureBlockingStub.getTopic(request);
	}
	
	public EnnMonitorConfigBusinessTopicCreateResponse createTopic(EnnMonitorConfigBusinessTopicCreateRequest request) {
		return futureBlockingStub.createTopic(request);
	}
	
	public EnnMonitorConfigBusinessTopicUpdateResponse updateTopic(EnnMonitorConfigBusinessTopicUpdateRequest request) {
		return futureBlockingStub.updateTopic(request);
	}
	
	public EnnMonitorConfigBusinessTopicDeleteResponse deleteTopic(Long id) {
		EnnMonitorConfigBusinessTopicDeleteRequest request = EnnMonitorConfigBusinessTopicDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteTopic(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request = EnnMonitorConfigBusinessTopicGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorConfigBusinessTopicGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigBusinessTopicGetValidDataListRequest request = EnnMonitorConfigBusinessTopicGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigBusinessTopicGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getTopicTableListList());
		}
		
		return objectList;
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request = EnnMonitorConfigBusinessTopicGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorConfigBusinessTopicGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}

}
