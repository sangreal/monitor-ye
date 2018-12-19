package enn.monitor.log.config.template.impl;

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
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateCreateResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateDeleteResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedData;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetDeletedDataListResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetMaxDeletedResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateGetValidDataListResponse;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateTable;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateRequest;
import enn.monitor.log.config.template.parameters.EnnMonitorLogConfigTemplateUpdateResponse;
import enn.monitor.log.config.template.server.EnnMonitorLogConfigTemplateServerGrpc;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateTableCtl;
import enn.monitor.log.config.template.tablectl.EnnMonitorLogConfigTemplateTableField;

public class EnnMonitorLogConfigTemplateImpl extends EnnMonitorLogConfigTemplateServerGrpc.EnnMonitorLogConfigTemplateServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogConfigTemplateTableCtl templateTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorLogConfigTemplateImpl(
			EnnMonitorLogConfigTemplateTableCtl templateTable, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl, EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.templateTable = templateTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public void getTemplate(EnnMonitorLogConfigTemplateGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateGetResponse> responseObserver) {
		EnnMonitorLogConfigTemplateGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getTemplate request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.IdFieldName, request.getId());
		}
		
		if (request.getBelongToServiceId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName, request.getBelongToServiceId());
		}
		
		if (request.getTagId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.TagIdFieldName, request.getTagId());
		}
		
		if (request.getIsRoot() == true) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName, new BasicDBObject("$exists", false));
		} else {
			if (request.getBelongToParentTemplateBytes() != null && request.getBelongToParentTemplate().equals("") == false) {
				basicDBObject.put(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName, request.getBelongToParentTemplate());
			}
		}
		
		if (request.getSetType() != null && request.getSetTypeValue() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.SetTypeFieldName, request.getSetType().name());
		}
		
		if (request.getBatchId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.BatchIdFieldName, request.getBatchId());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorLogConfigTemplateGetResponse.newBuilder();
		try {
			List<EnnMonitorLogConfigTemplateTable> tableList = templateTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllTemplateTable(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void createTemplate(EnnMonitorLogConfigTemplateCreateRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateCreateResponse> responseObserver) {
		EnnMonitorLogConfigTemplateCreateResponse.Builder responseBuilder = EnnMonitorLogConfigTemplateCreateResponse.newBuilder();
		EnnMonitorLogConfigTemplateTable.Builder templateBuilder = EnnMonitorLogConfigTemplateTable.newBuilder();
		
		ChannelWriteData channelWriteData = null;
		
		logger.info("createTemplate request: " + request.toString());
		
		try {
			if (request.getTemplateKey() == null || request.getTemplateKey().equals("") == true) {
				throw new Exception("the templateKey is null");
			}
			templateBuilder.setTemplateKey(request.getTemplateKey());
			
			templateBuilder.setBelongToParentTemplate(request.getBelongToParentTemplate());
			
			if (request.getBelongToServiceId() <= 0) {
				throw new Exception("the belongToServiceId is not set");
			}
			templateBuilder.setBelongToServiceId(request.getBelongToServiceId());
			
			if (request.getSetType() != null && request.getSetTypeValue() > 0) {
				templateBuilder.setSetType(request.getSetType());
			}
			
			if (request.getTagId() > 0) {
				templateBuilder.setTagId(request.getTagId());
			}
			
			if (request.getBatchId() > 0) {
				templateBuilder.setBatchId(request.getBatchId());
			}
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("createrUser is null");
			}
			
			templateBuilder.setCreateUser(request.getCreateUser());
			templateBuilder.setLastUpdateUser(request.getCreateUser());
			
			templateBuilder.setCreateTime(System.currentTimeMillis());
			templateBuilder.setLastUpdateTime(templateBuilder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(templateBuilder.build());
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
	
    public void updateTemplate(EnnMonitorLogConfigTemplateUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateUpdateResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateTable.Builder templateTableBuilder = null;
    	
    	ChannelWriteData channelWriteData = null;
    	
    	EnnMonitorLogConfigTemplateUpdateResponse.Builder responseBuilder = EnnMonitorLogConfigTemplateUpdateResponse.newBuilder();
    	logger.info("updateTemplate request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			templateTableBuilder = EnnMonitorLogConfigTemplateTable.newBuilder();
    			
    			templateTableBuilder.setId(request.getId());
    			
    			if (request.getTemplateKey() != null && request.getTemplateKey().equals("") == false) {
    				templateTableBuilder.setTemplateKey(request.getTemplateKey());
    			}
    			
    			templateTableBuilder.setBelongToParentTemplate(request.getBelongToParentTemplate());
    			
    			if (request.getBelongToServiceId() > 0) {
    				templateTableBuilder.setBelongToServiceId(request.getBelongToServiceId());
    			}
    			
    			if (request.getSetType() != null && request.getSetTypeValue() > 0) {
    				templateTableBuilder.setSetType(request.getSetType());
    			}
    			
				templateTableBuilder.setTagId(request.getTagId());
    			
    			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
    				throw new Exception("the lastUpdateUser is null");
    			}
    			templateTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			
    			templateTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
    			channelWriteData.setObject(templateTableBuilder.build());
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
    
    public void deleteTemplate(EnnMonitorLogConfigTemplateDeleteRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateDeleteResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateDeleteResponse.Builder responseBuilder = EnnMonitorLogConfigTemplateDeleteResponse.newBuilder();
    	EnnMonitorLogConfigTemplateTable.Builder templateBuilder = EnnMonitorLogConfigTemplateTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
    	
    	logger.info("deleteTemplate request: " + request.toString());
    	
    	if (request.getId() < 0 && request.getBatchId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		if (request.getId() > 0) {
    			templateBuilder.setId(request.getId());
    		}
    		
    		if (request.getBatchId() > 0) {
    			templateBuilder.setBatchId(request.getBatchId());
    		}
    		
    		try {
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
    			channelWriteData.setObject(templateBuilder.build());
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
    
    public void getMaxDeletedSeqNo(EnnMonitorLogConfigTemplateGetMaxDeletedRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateGetMaxDeletedResponse.Builder response = EnnMonitorLogConfigTemplateGetMaxDeletedResponse.newBuilder();
    	
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
    
    public void getValidDataList(EnnMonitorLogConfigTemplateGetValidDataListRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateGetValidDataListResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateGetValidDataListResponse.Builder response = EnnMonitorLogConfigTemplateGetValidDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorLogConfigTemplateTable> templateTableList = null;
    	
    	query.put(EnnMonitorLogConfigTemplateTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	if (request.getBelongToServiceId() > 0) {
    		query.put(EnnMonitorLogConfigTemplateTableField.BelongToServiceIdFieldName, request.getBelongToServiceId());
    	}
    	if (request.getSetTypeValue() > 0) {
    		query.put(EnnMonitorLogConfigTemplateTableField.SetTypeFieldName, request.getSetType().name());
    	}
    	query.put(EnnMonitorLogConfigTemplateTableField.BelongToParentTemplateFieldName, new BasicDBObject("$exists", false));
    	
    	order.put(EnnMonitorLogConfigTemplateTableField.SeqNoFieldName, 1);
    	
    	try {
    		templateTableList = templateTable.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllTemplateTable(templateTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    
    public void getDeletedDataList(EnnMonitorLogConfigTemplateGetDeletedDataListRequest request,
            io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateGetDeletedDataListResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateGetDeletedDataListResponse.Builder response = EnnMonitorLogConfigTemplateGetDeletedDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorLogConfigTemplateGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorLogConfigTemplateGetDeletedData> deletedDataList = new ArrayList<EnnMonitorLogConfigTemplateGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
    		if (deletedTableList != null) {
    			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
    				deletedData = EnnMonitorLogConfigTemplateGetDeletedData.newBuilder();
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
