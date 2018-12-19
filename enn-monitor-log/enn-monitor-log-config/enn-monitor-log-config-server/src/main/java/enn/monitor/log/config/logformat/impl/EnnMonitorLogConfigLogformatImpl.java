package enn.monitor.log.config.logformat.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatCreateResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatDeleteResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedData;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetDeletedDataListResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetMaxDeletedResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatGetValidDataListResponse;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatTable;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateRequest;
import enn.monitor.log.config.logformat.parameters.EnnMonitorLogConfigLogformatUpdateResponse;
import enn.monitor.log.config.logformat.server.EnnMonitorLogConfigLogformatServerGrpc;
import enn.monitor.log.config.logformat.tablectl.EnnMonitorLogConfigLogformatTableCtl;
import enn.monitor.log.config.logformat.tablectl.EnnMonitorLogConfigLogformatTableField;

public class EnnMonitorLogConfigLogformatImpl extends EnnMonitorLogConfigLogformatServerGrpc.EnnMonitorLogConfigLogformatServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogConfigLogformatTableCtl logformatTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorLogConfigLogformatImpl(
			EnnMonitorLogConfigLogformatTableCtl logformatTable, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl, EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.logformatTable = logformatTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public void getLogformat(EnnMonitorLogConfigLogformatGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatGetResponse> responseObserver) {
		EnnMonitorLogConfigLogformatGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getLogformat request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigLogformatTableField.IdFieldName, request.getId());
		}
		
		if (request.getBelongToServiceId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigLogformatTableField.BelongToServiceIdFieldName, request.getBelongToServiceId());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigLogformatTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigLogformatTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorLogConfigLogformatGetResponse.newBuilder();
		try {
			List<EnnMonitorLogConfigLogformatTable> tableList = logformatTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllLogformat(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void createLogformat(EnnMonitorLogConfigLogformatCreateRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatCreateResponse> responseObserver) {
		EnnMonitorLogConfigLogformatCreateResponse.Builder responseBuilder = EnnMonitorLogConfigLogformatCreateResponse.newBuilder();
		EnnMonitorLogConfigLogformatTable.Builder logformatBuilder = EnnMonitorLogConfigLogformatTable.newBuilder();
		
		ChannelWriteData channelWriteData = null;
		
		logger.info("createLogformat request: " + request.toString());
		
		try {
			if (request.getRegex() == null || request.getRegex().equals("") == true) {
				throw new Exception("the regex is null");
			}
			logformatBuilder.setRegex(request.getRegex());
			
			if (request.getLogformat() == null || request.getLogformat().equals("") == true) {
				throw new Exception("the logformat is null");
			}
			logformatBuilder.setLogformat(request.getLogformat());
			
			if (request.getBelongToServiceId() <= 0) {
				throw new Exception("the belongToServiceId is not set");
			}
			logformatBuilder.setBelongToServiceId(request.getBelongToServiceId());
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("createrUser is null");
			}
			
			logformatBuilder.setCreateUser(request.getCreateUser());
			logformatBuilder.setLastUpdateUser(request.getCreateUser());
			
			logformatBuilder.setCreateTime(System.currentTimeMillis());
			logformatBuilder.setLastUpdateTime(logformatBuilder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(logformatBuilder.build());
			pipeServer.write(channelWriteData);
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
    public void updateLogformat(EnnMonitorLogConfigLogformatUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatUpdateResponse> responseObserver) {
    	EnnMonitorLogConfigLogformatTable.Builder logformatTableBuilder = null;
    	
    	ChannelWriteData channelWriteData = null;
    	
    	EnnMonitorLogConfigLogformatUpdateResponse.Builder responseBuilder = EnnMonitorLogConfigLogformatUpdateResponse.newBuilder();
    	logger.info("updateLogformat request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			logformatTableBuilder = EnnMonitorLogConfigLogformatTable.newBuilder();
    			
    			logformatTableBuilder.setId(request.getId());
    			
    			if (request.getRegex() != null && request.getRegex().equals("") == false) {
    				logformatTableBuilder.setRegex(request.getRegex());
    			}
    			
    			if (request.getLogformat() != null && request.getLogformat().equals("") == false) {
    				logformatTableBuilder.setLogformat(request.getLogformat());
    			}
    			
    			if (request.getBelongToServiceId() > 0) {
    				logformatTableBuilder.setBelongToServiceId(request.getBelongToServiceId());
    			}
    			
    			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
    				throw new Exception("the lastUpdateUser is null");
    			}
    			logformatTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			
    			logformatTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
    			channelWriteData.setObject(logformatTableBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
    	
    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    public void deleteLogformat(EnnMonitorLogConfigLogformatDeleteRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatDeleteResponse> responseObserver) {
    	EnnMonitorLogConfigLogformatDeleteResponse.Builder responseBuilder = EnnMonitorLogConfigLogformatDeleteResponse.newBuilder();
    	EnnMonitorLogConfigLogformatTable.Builder logformatBuilder = EnnMonitorLogConfigLogformatTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("deleteLogformat request: " + request.toString());
    	
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		logformatBuilder.setId(request.getId());
    		
    		try {
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
    			channelWriteData.setObject(logformatBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
		
    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
    public void getMaxDeletedSeqNo(EnnMonitorLogConfigLogformatGetMaxDeletedRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorLogConfigLogformatGetMaxDeletedResponse.Builder response = EnnMonitorLogConfigLogformatGetMaxDeletedResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	long seqNo = -1;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null; 
    	
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, -1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, 1);
    		if (deletedTableList == null || deletedTableList.size() <= 0) {
    			seqNo = -1;
    		} else {
    			seqNo = deletedTableList.get(0).getSeqNo();
    		}
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
    
    public void getValidDataList(EnnMonitorLogConfigLogformatGetValidDataListRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatGetValidDataListResponse> responseObserver) {
    	EnnMonitorLogConfigLogformatGetValidDataListResponse.Builder response = EnnMonitorLogConfigLogformatGetValidDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorLogConfigLogformatTable> logformatTableList = null;
    	
    	query.put(EnnMonitorLogConfigLogformatTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorLogConfigLogformatTableField.SeqNoFieldName, 1);
    	
    	try {
    		logformatTableList = logformatTable.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllLogformatTableList(logformatTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    
    public void getDeletedDataList(EnnMonitorLogConfigLogformatGetDeletedDataListRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigLogformatGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorLogConfigLogformatGetDeletedDataListResponse.Builder response = EnnMonitorLogConfigLogformatGetDeletedDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorLogConfigLogformatGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorLogConfigLogformatGetDeletedData> deletedDataList = new ArrayList<EnnMonitorLogConfigLogformatGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
    		if (deletedTableList != null) {
    			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
    				deletedData = EnnMonitorLogConfigLogformatGetDeletedData.newBuilder();
    				deletedData.setId(deletedTable.getId());
    				deletedData.setSeqNo(deletedTable.getSeqNo());
    				deletedDataList.add(deletedData.build());
        		}
    		}
    		
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
    
}
