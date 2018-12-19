package enn.monitor.log.config.event.impl;

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
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventCreateResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventDeleteResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedData;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventTable;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateRequest;
import enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventUpdateResponse;
import enn.monitor.log.config.event.server.EnnMonitorLogConfigEventServerGrpc;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventTableCtl;
import enn.monitor.log.config.event.tablectl.EnnMonitorLogConfigEventTableField;

public class EnnMonitorLogConfigEventImpl extends EnnMonitorLogConfigEventServerGrpc.EnnMonitorLogConfigEventServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogConfigEventTableCtl eventTableCtl = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorLogConfigEventImpl(EnnMonitorLogConfigEventTableCtl ennMonitorLogConfigEventTableCtl, 
			EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl, EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.eventTableCtl = ennMonitorLogConfigEventTableCtl;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}

	public void getEvent(EnnMonitorLogConfigEventGetRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigEventGetResponse> responseObserver) {
		EnnMonitorLogConfigEventGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		BasicDBObject orderDBObject = new BasicDBObject();
		
		logger.info("getEvent request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigEventTableField.IdFieldName, request.getId());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigEventTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigEventTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		orderDBObject.put(EnnMonitorLogConfigEventTableField.EventNameFieldName, 1);
		
		responseBuilder = EnnMonitorLogConfigEventGetResponse.newBuilder();
		try {
			List<EnnMonitorLogConfigEventTable> tableList = eventTableCtl.getMongoDBCtrl().searchAllData(basicDBObject, orderDBObject);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllEventTable(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createEvent(EnnMonitorLogConfigEventCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigEventCreateResponse> responseObserver) {
    	EnnMonitorLogConfigEventCreateResponse.Builder responseBuilder = EnnMonitorLogConfigEventCreateResponse.newBuilder();
    	EnnMonitorLogConfigEventTable.Builder eventBuilder = EnnMonitorLogConfigEventTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("createEvent request: " + request.toString());
		
		try {
			if (request.getEventName() == null || request.equals("") == true) {
				throw new Exception("the eventNum is null");
			}
			
			eventBuilder.setEventKey(request.getBelongToServiceId() + "-" + request.getEventName());
			
			eventBuilder.setEventName(request.getEventName());
			eventBuilder.setBelongToServiceId(request.getBelongToServiceId());
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("createrUser is null");
			}
			
			eventBuilder.setCreateUser(request.getCreateUser());
			eventBuilder.setLastUpdateUser(request.getCreateUser());
			
			eventBuilder.setCreateTime(System.currentTimeMillis());
			eventBuilder.setLastUpdateTime(eventBuilder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(eventBuilder.build());
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

    public void updateEvent(EnnMonitorLogConfigEventUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigEventUpdateResponse> responseObserver) {
    	EnnMonitorLogConfigEventTable.Builder eventTableBuilder = null;
    	
    	ChannelWriteData channelWriteData = null;
    	
    	EnnMonitorLogConfigEventUpdateResponse.Builder responseBuilder = EnnMonitorLogConfigEventUpdateResponse.newBuilder();
    	logger.info("updateEvent request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			eventTableBuilder = EnnMonitorLogConfigEventTable.newBuilder();
    			
    			eventTableBuilder.setId(request.getId());
    			
    			eventTableBuilder.setBelongToServiceId(request.getBelongToServiceId());
    			
    			if (request.getEventName() == null || request.equals("") == true) {
    				throw new Exception("the eventName is null");
    			}
    			eventTableBuilder.setEventName(request.getEventName());
    			
    			eventTableBuilder.setEventKey(request.getBelongToServiceId() + "-" + request.getEventName());
    			
    			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
    				throw new Exception("the lastUpdateUser is null");
    			}
    			eventTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			eventTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
    			channelWriteData.setObject(eventTableBuilder.build());
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

    public void deleteEvent(EnnMonitorLogConfigEventDeleteRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigEventDeleteResponse> responseObserver) {
    	EnnMonitorLogConfigEventDeleteResponse.Builder responseBuilder = EnnMonitorLogConfigEventDeleteResponse.newBuilder();
    	EnnMonitorLogConfigEventTable.Builder eventBuilder = EnnMonitorLogConfigEventTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("deleteEvent request: " + request.toString());
    	
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		eventBuilder.setId(request.getId());
    		
    		try {
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
    			channelWriteData.setObject(eventBuilder.build());
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
    
    public void getMaxDeletedSeqNo(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorLogConfigEventGetMaxDeletedResponse.Builder response = EnnMonitorLogConfigEventGetMaxDeletedResponse.newBuilder();
    	
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

    public void getValidDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetValidDataListResponse> responseObserver) {
    	EnnMonitorLogConfigEventGetValidDataListResponse.Builder response = EnnMonitorLogConfigEventGetValidDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorLogConfigEventTable> eventTableList = null;
    	
    	query.put(EnnMonitorLogConfigEventTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorLogConfigEventTableField.SeqNoFieldName, 1);
    	
    	try {
    		eventTableList = eventTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllEventTable(eventTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getDeletedDataList(enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.config.event.parameters.EnnMonitorLogConfigEventGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorLogConfigEventGetDeletedDataListResponse.Builder response = EnnMonitorLogConfigEventGetDeletedDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorLogConfigEventGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorLogConfigEventGetDeletedData> deletedDataList = new ArrayList<EnnMonitorLogConfigEventGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
    		if (deletedTableList != null) {
    			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
    				deletedData = EnnMonitorLogConfigEventGetDeletedData.newBuilder();
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
