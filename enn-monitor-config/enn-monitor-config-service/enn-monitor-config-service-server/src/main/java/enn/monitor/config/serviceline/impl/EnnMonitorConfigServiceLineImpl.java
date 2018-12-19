package enn.monitor.config.serviceline.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineCreateResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineDeleteResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedData;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetDeletedDataListResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetMaxDeletedResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineGetValidDataListResponse;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineTable;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateRequest;
import enn.monitor.config.serviceLine.parameters.EnnMonitorConfigServiceLineUpdateResponse;
import enn.monitor.config.serviceLine.server.EnnMonitorConfigServiceLineServerGrpc;

public class EnnMonitorConfigServiceLineImpl extends EnnMonitorConfigServiceLineServerGrpc.EnnMonitorConfigServiceLineServerImplBase {
	
private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorConfigServiceLineHandler serviceLineHandler = null;
	
	public EnnMonitorConfigServiceLineImpl(EnnMonitorConfigServiceLineHandler serviceLineHandler) {
		this.serviceLineHandler = serviceLineHandler;
	}

	public void getServiceLine(EnnMonitorConfigServiceLineGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineGetResponse> responseObserver) {
		EnnMonitorConfigServiceLineGetResponse.Builder responseBuilder = null;
		
		responseBuilder = EnnMonitorConfigServiceLineGetResponse.newBuilder();
		try {
			List<EnnMonitorConfigServiceLineTable> serviceLineTableList = serviceLineHandler.getEnnMonitorConfigServiceLine(request);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllServiceLineTable(serviceLineTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createServiceLine(EnnMonitorConfigServiceLineCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineCreateResponse> responseObserver) {
    	EnnMonitorConfigServiceLineCreateResponse.Builder responseBuilder = EnnMonitorConfigServiceLineCreateResponse.newBuilder();
    	
		try {
			serviceLineHandler.createEnnMonitorConfigServiceLine(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void updateServiceLine(EnnMonitorConfigServiceLineUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineUpdateResponse> responseObserver) {
    	EnnMonitorConfigServiceLineUpdateResponse.Builder responseBuilder = EnnMonitorConfigServiceLineUpdateResponse.newBuilder();
    	
		try {
			serviceLineHandler.updateEnnMonitorConfigServiceLine(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    public void deleteServiceLine(EnnMonitorConfigServiceLineDeleteRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineDeleteResponse> responseObserver) {
    	EnnMonitorConfigServiceLineDeleteResponse.Builder responseBuilder = EnnMonitorConfigServiceLineDeleteResponse.newBuilder();
    	
    	try {
    		serviceLineHandler.deleteEnnMonitorConfigServiceLine(request);
    		responseBuilder.setIsSuccess(true);
    	} catch (Exception e) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void getMaxDeletedSeqNo(EnnMonitorConfigServiceLineGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorConfigServiceLineGetMaxDeletedResponse.Builder response = EnnMonitorConfigServiceLineGetMaxDeletedResponse.newBuilder();
    	
    	long seqNo = -1;
    	
    	try {
    		seqNo = serviceLineHandler.getEnnMonitorConfigServiceLineMaxDeletedSeqNo();
    		response.setIsSuccess(true);
    		response.setSeqNo(seqNo);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		response.setIsSuccess(false);
			response.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getValidDataList(EnnMonitorConfigServiceLineGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineGetValidDataListResponse> responseObserver) {
    	EnnMonitorConfigServiceLineGetValidDataListResponse.Builder response = EnnMonitorConfigServiceLineGetValidDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigServiceLineTable> serviceLineTableList = null;
    	
    	try {
    		serviceLineTableList = serviceLineHandler.getEnnMonitorConfigServiceLineValid(request.getStartSeqNo(), request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllServiceLineTableList(serviceLineTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getDeletedDataList(EnnMonitorConfigServiceLineGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorConfigServiceLineGetDeletedDataListResponse.Builder response = EnnMonitorConfigServiceLineGetDeletedDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigServiceLineGetDeletedData> deletedDataList = null;
    	
    	try {
    		deletedDataList = serviceLineHandler.getEnnMonitorConfigServiceLineDeleted(request.getStartSeqNo(), request.getBatchNum());
    		
    		response.setIsSuccess(true);
    		response.addAllDeletedDataList(deletedDataList);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		response.setIsSuccess(false);
			response.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

	public void checkServiceLineIsExistedOrNot(EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotRequest request,
										   io.grpc.stub.StreamObserver<EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse> responseObserver) {
		EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse.Builder responseBuilder = EnnMonitorConfigServiceLineCheckServiceLineIsExistedOrNotResponse.newBuilder();

		boolean existedOrNot = false;

		try {
			existedOrNot = serviceLineHandler.checkServiceLineExistedOrNot(request.getServiceLineName());
			responseBuilder.setIsSuccess(existedOrNot);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}

		responseObserver.onNext(responseBuilder.build());
		responseObserver.onCompleted();
	}
}
