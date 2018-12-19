package enn.monitor.config.cluster.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.cluster.cluster.EnnMonitorConfigClusterServerGrpc;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterCreateResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterDeleteResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterExistClusterNameOrNotResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedData;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetDeletedDataListResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetMaxDeletedResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterGetValidDataListResponse;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterTable;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateRequest;
import enn.monitor.config.cluster.parameters.EnnMonitorConfigClusterUpdateResponse;

public class EnnMonitorConfigClusterImpl extends EnnMonitorConfigClusterServerGrpc.EnnMonitorConfigClusterServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorConfigClusterHandler clusterHandler = null;
	
	public EnnMonitorConfigClusterImpl(EnnMonitorConfigClusterHandler clusterHandler) {
		this.clusterHandler = clusterHandler;
	}

	public void getCluster(EnnMonitorConfigClusterGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterGetResponse> responseObserver) {
		EnnMonitorConfigClusterGetResponse.Builder responseBuilder = null;
		
		responseBuilder = EnnMonitorConfigClusterGetResponse.newBuilder();
		try {
			List<EnnMonitorConfigClusterTable> clusterTableList = clusterHandler.getEnnMonitorConfigCluster(request);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllClusterTable(clusterTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createCluster(EnnMonitorConfigClusterCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterCreateResponse> responseObserver) {
    	EnnMonitorConfigClusterCreateResponse.Builder responseBuilder = EnnMonitorConfigClusterCreateResponse.newBuilder();
    	
		try {
			clusterHandler.createEnnMonitorConfigCluster(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void updateCluster(EnnMonitorConfigClusterUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterUpdateResponse> responseObserver) {
    	EnnMonitorConfigClusterUpdateResponse.Builder responseBuilder = EnnMonitorConfigClusterUpdateResponse.newBuilder();
    	
		try {
			clusterHandler.updateEnnMonitorConfigCluster(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    public void deleteCluster(EnnMonitorConfigClusterDeleteRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorConfigClusterDeleteResponse> responseObserver) {
    	EnnMonitorConfigClusterDeleteResponse.Builder responseBuilder = EnnMonitorConfigClusterDeleteResponse.newBuilder();
    	
    	try {
    		clusterHandler.deleteEnnMonitorConfigCluster(request);
    		responseBuilder.setIsSuccess(true);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void getMaxDeletedSeqNo(EnnMonitorConfigClusterGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorConfigClusterGetMaxDeletedResponse.Builder response = EnnMonitorConfigClusterGetMaxDeletedResponse.newBuilder();
    	
    	long seqNo = -1;
    	
    	try {
    		seqNo = clusterHandler.getEnnMonitorConfigClusterMaxDeletedSeqNo();
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

    public void getValidDataList(EnnMonitorConfigClusterGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterGetValidDataListResponse> responseObserver) {
    	EnnMonitorConfigClusterGetValidDataListResponse.Builder response = EnnMonitorConfigClusterGetValidDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigClusterTable> clusterTableList = null;
    	
    	try {
    		clusterTableList = clusterHandler.getEnnMonitorConfigClusterValid(request.getStartSeqNo(), request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllClusterTableList(clusterTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getDeletedDataList(EnnMonitorConfigClusterGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigClusterGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorConfigClusterGetDeletedDataListResponse.Builder response = EnnMonitorConfigClusterGetDeletedDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigClusterGetDeletedData> deletedDataList = null;
    	
    	try {
    		deletedDataList = clusterHandler.getEnnMonitorConfigClusterDeleted(request.getStartSeqNo(), request.getBatchNum());
    		
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

	public void checkClusterNameIsExistedOrNot(EnnMonitorConfigClusterExistClusterNameOrNotRequest request,
										   io.grpc.stub.StreamObserver<EnnMonitorConfigClusterExistClusterNameOrNotResponse> responseObserver) {
		EnnMonitorConfigClusterExistClusterNameOrNotResponse.Builder responseBuilder = EnnMonitorConfigClusterExistClusterNameOrNotResponse.newBuilder();

		boolean existedOrNot = false;

		try {
			existedOrNot = clusterHandler.checkClusterExistedOrNot(request.getClusterName());
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
