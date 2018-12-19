package enn.monitor.log.config.template.client;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse;
import enn.monitor.log.config.template.server.EnnMonitorLogConfigTemplateServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogConfigTemplateClient {

	private ManagedChannel channel;
	private EnnMonitorLogConfigTemplateServerGrpc.EnnMonitorLogConfigTemplateServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogConfigTemplateClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogConfigTemplateServerGrpc.newBlockingStub(channel);
	}

	public EnnMonitorLogConfigTemplateGetResponse getTemplate(EnnMonitorLogConfigTemplateGetRequest request) {
		return futureBlockingStub.getTemplate(request);
	}
	
	public EnnMonitorLogConfigTemplateCreateResponse createTemplate(EnnMonitorLogConfigTemplateCreateRequest request) {
		return futureBlockingStub.createTemplate(request);
	}
	
	public EnnMonitorLogConfigTemplateUpdateResponse updateTemplate(EnnMonitorLogConfigTemplateUpdateRequest request) {
		return futureBlockingStub.updateTemplate(request);
	}
	
	public EnnMonitorLogConfigTemplateDeleteResponse deleteTemplate(long id) {
		EnnMonitorLogConfigTemplateDeleteRequest request = EnnMonitorLogConfigTemplateDeleteRequest.newBuilder().setId(id).build();
		return futureBlockingStub.deleteTemplate(request);
	}
	
	public long getMaxDeletedSeqNo() throws Exception {
		EnnMonitorLogConfigTemplateGetMaxDeletedRequest request = EnnMonitorLogConfigTemplateGetMaxDeletedRequest.newBuilder().build();
		EnnMonitorLogConfigTemplateGetMaxDeletedResponse response = null;
		
		response = futureBlockingStub.getMaxDeletedSeqNo(request);
		if (response.getIsSuccess() == false) {
			return -1;
		}
		
		return response.getSeqNo();
	}
	
	public List<Object> getValidDataList(long startSeqNo, int batch) throws Exception {
		EnnMonitorLogConfigTemplateGetValidDataListRequest request = 
				EnnMonitorLogConfigTemplateGetValidDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		return getValidDataListInternal(request);
	}
	
	public List<Object> getValidDataList(EnnMonitorLogConfigTemplateGetValidDataListRequest request) throws Exception {
		return getValidDataListInternal(request);
	}
	
	public List<Object> getDeletedDataList(long startSeqNo, int batch) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigTemplateGetDeletedDataListRequest request = EnnMonitorLogConfigTemplateGetDeletedDataListRequest.newBuilder().setStartSeqNo(startSeqNo).setBatchNum(batch).build();
		EnnMonitorLogConfigTemplateGetDeletedDataListResponse response = futureBlockingStub.getDeletedDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getDeletedDataListList());
		}
		
		return objectList;
	}
	
	private List<Object> getValidDataListInternal(EnnMonitorLogConfigTemplateGetValidDataListRequest request) throws Exception {
		List<Object> objectList = null;
		
		EnnMonitorLogConfigTemplateGetValidDataListResponse response = futureBlockingStub.getValidDataList(request);
		
		objectList = new ArrayList<Object>();
		if (response.getIsSuccess() == true) {
			objectList.addAll(response.getTemplateTableList());
		}
		
		return objectList;
	}

}
