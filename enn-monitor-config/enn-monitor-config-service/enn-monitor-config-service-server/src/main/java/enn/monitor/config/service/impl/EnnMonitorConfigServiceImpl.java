package enn.monitor.config.service.impl;

import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCountResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceCreateResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceDeleteResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedData;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetDeletedDataListResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetMaxDeletedResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceGetValidDataListResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTable;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceTokenGeneratorResponse;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateRequest;
import enn.monitor.config.service.parameters.EnnMonitorConfigServiceUpdateResponse;
import enn.monitor.config.service.server.EnnMonitorConfigServiceServerGrpc;
import enn.monitor.framework.common.env.EnnMonitorEnvAppUtil;

public class EnnMonitorConfigServiceImpl extends EnnMonitorConfigServiceServerGrpc.EnnMonitorConfigServiceServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorConfigServiceHandler serviceHandler = null;
	
	private static AtomicLong count = new AtomicLong(0);
	
	public EnnMonitorConfigServiceImpl(EnnMonitorConfigServiceHandler serviceHandler) {
		this.serviceHandler = serviceHandler;
	}
	
	public void getService(EnnMonitorConfigServiceGetRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceGetResponse> responseObserver) {
		EnnMonitorConfigServiceGetResponse.Builder responseBuilder = null;
		
		responseBuilder = EnnMonitorConfigServiceGetResponse.newBuilder();
		try {
			List<EnnMonitorConfigServiceTable> serviceTableList = serviceHandler.getEnnMonitorConfigService(request);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllServiceTable(serviceTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void countService(EnnMonitorConfigServiceGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceCountResponse> responseObserver) {
		long count;
		EnnMonitorConfigServiceCountResponse.Builder responseBuilder = null;
		
		responseBuilder = EnnMonitorConfigServiceCountResponse.newBuilder();
		try {
			count = serviceHandler.countEnnMonitorConfigService(request);
			responseBuilder.setIsSuccess(true);
			responseBuilder.setCount(count);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createService(EnnMonitorConfigServiceCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceCreateResponse> responseObserver) {
    	EnnMonitorConfigServiceCreateResponse.Builder responseBuilder = EnnMonitorConfigServiceCreateResponse.newBuilder();
    	
		try {
			serviceHandler.createEnnMonitorConfigService(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void updateService(EnnMonitorConfigServiceUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceUpdateResponse> responseObserver) {
    	EnnMonitorConfigServiceUpdateResponse.Builder responseBuilder = EnnMonitorConfigServiceUpdateResponse.newBuilder();
    	
		try {
			serviceHandler.updateEnnMonitorConfigService(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void deleteService(EnnMonitorConfigServiceDeleteRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceDeleteResponse> responseObserver) {
    	EnnMonitorConfigServiceDeleteResponse.Builder responseBuilder = EnnMonitorConfigServiceDeleteResponse.newBuilder();
		
		try {
			serviceHandler.deleteEnnMonitorConfigService(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
    	
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    public void generateServiceToken(EnnMonitorConfigServiceTokenGeneratorRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorConfigServiceTokenGeneratorResponse> responseObserver) {
    	String token = null;
    	String md5 = null;
    	EnnMonitorConfigServiceTokenGeneratorResponse.Builder response = EnnMonitorConfigServiceTokenGeneratorResponse.newBuilder();
    	
    	if (request.getUserdId() == null || request.getUserdId().equals("") == true) {
    		response.setIsSuccess(false);
    		response.setError("the userid is null, it is " + request.getUserdId());
    	} else {
    		try {
    			token = request.getUserdId() + System.currentTimeMillis() + EnnMonitorEnvAppUtil.getNameSpace() + 
    					EnnMonitorEnvAppUtil.getPodName() + Thread.currentThread().getId() + count.getAndIncrement();
        		MessageDigest md = MessageDigest.getInstance("MD5");  
                byte[] input = token.getBytes();  
                byte[] buff = md.digest(input);
                md5 = bytesToHex(buff);
                
                response.setIsSuccess(true);
                response.setToken(md5);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			response.setIsSuccess(false);
        		response.setError(e.getMessage());
    		}
    	}
		
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getMaxDeletedSeqNo(EnnMonitorConfigServiceGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorConfigServiceGetMaxDeletedResponse.Builder response = EnnMonitorConfigServiceGetMaxDeletedResponse.newBuilder();
    	
    	long seqNo = -1;
    	
    	try {
    		seqNo = serviceHandler.getEnnMonitorConfigServiceMaxDeletedSeqNo();
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

    public void getValidDataList(EnnMonitorConfigServiceGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceGetValidDataListResponse> responseObserver) {
    	EnnMonitorConfigServiceGetValidDataListResponse.Builder response = EnnMonitorConfigServiceGetValidDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigServiceTable> serviceTableList = null;
    	
    	try {
    		serviceTableList = serviceHandler.getEnnMonitorConfigServiceValid(request.getStartSeqNo(), request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllServiceTableList(serviceTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getDeletedDataList(EnnMonitorConfigServiceGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorConfigServiceGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorConfigServiceGetDeletedDataListResponse.Builder response = EnnMonitorConfigServiceGetDeletedDataListResponse.newBuilder();
    	
    	List<EnnMonitorConfigServiceGetDeletedData> deletedDataList = null;
    	
    	try {
    		deletedDataList = serviceHandler.getEnnMonitorConfigServiceDeleted(request.getStartSeqNo(), request.getBatchNum());
    		
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

	public void checkServiceIsExistedOrNot(EnnMonitorConfigServiceCheckServiceIsExistedOrNotRequest request,
										   io.grpc.stub.StreamObserver<EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse> responseObserver) {
		EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse.Builder responseBuilder = EnnMonitorConfigServiceCheckServiceIsExistedOrNotResponse.newBuilder();

		boolean existedOrNot = false;

		try {
			existedOrNot = serviceHandler.checkServiceExistedOrNot(request.getId(), request.getServiceName());
			responseBuilder.setIsSuccess(existedOrNot);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}

		responseObserver.onNext(responseBuilder.build());
		responseObserver.onCompleted();
	}
	
	private String bytesToHex(byte[] bytes) {  
	    StringBuffer md5str = new StringBuffer();  
	    
	    int digital;  
	    for (int i = 0; i < bytes.length; i++) {  
	        digital = bytes[i];  
	  
	        if (digital < 0) {  
	        	digital += 256;  
	        }  
	        if (digital < 16) {  
	        	md5str.append("0");  
	        }  
	        md5str.append(Integer.toHexString(digital));  
	    }  
	    
	    return md5str.toString().toUpperCase();  
    }
}
