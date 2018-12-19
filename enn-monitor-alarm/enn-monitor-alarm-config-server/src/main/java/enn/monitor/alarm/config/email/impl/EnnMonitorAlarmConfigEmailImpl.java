package enn.monitor.alarm.config.email.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.alarm.config.Server.EnnMonitorAlarmConfigEmailServerGrpc;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeleted;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetRequest;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailTable;
import enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableCtl;
import enn.monitor.alarm.config.email.tablectl.EnnMonitorAlarmConfigEmailTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorAlarmConfigEmailImpl extends EnnMonitorAlarmConfigEmailServerGrpc.EnnMonitorAlarmConfigEmailServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getEmailMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmConfigEmailImpl.class, "getEmail");
	private CounterMetric createEmailMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmConfigEmailImpl.class, "createEmail");
	private CounterMetric updateEmailMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmConfigEmailImpl.class, "updateEmail");
	private CounterMetric deleteEmailMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmConfigEmailImpl.class, "deleteEmail");
	
	private EnnMonitorAlarmConfigEmailTableCtl emailTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorAlarmConfigEmailImpl(
			EnnMonitorAlarmConfigEmailTableCtl emailTable, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl, EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.emailTable = emailTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public void getEnnMonitorAlarmConfigEmail(EnnMonitorAlarmConfigEmailGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorAlarmConfigEmailGetResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getEmail request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorAlarmConfigEmailTableField.IdFieldName, request.getId());
		}
		
		if (request.getGroupName() != null && request.getGroupName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmConfigEmailTableField.GroupNameFieldName, request.getGroupName());
		}
		
		if (request.getName() != null && request.getName().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmConfigEmailTableField.NameFieldName, request.getName());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmConfigEmailTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorAlarmConfigEmailTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorAlarmConfigEmailGetResponse.newBuilder();
		try {
			List<EnnMonitorAlarmConfigEmailTable> tableList = emailTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllEnnMonitorAlarmConfigEmailTableList(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		getEmailMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void createEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailCreateResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailCreateResponse.Builder responseBuilder = EnnMonitorAlarmConfigEmailCreateResponse.newBuilder();
		EnnMonitorAlarmConfigEmailTable.Builder emailBulder = EnnMonitorAlarmConfigEmailTable.newBuilder();
		
		ChannelWriteData channelWriteData = null;
		
		logger.info("createEmail request: " + request.toString());
		
		try {
			if (request.getGroupName() == null || request.getGroupName().equals("") == true) {
				throw new Exception("the groupName is null");
			}
			emailBulder.setGroupName(request.getGroupName());
			
			if (request.getMail() == null || request.getMail().equals("") == true) {
				throw new Exception("the mail is null");
			}
			emailBulder.setMail(request.getMail());
			
			if (request.getName() != null && request.getName().equals("") == false) {
				emailBulder.setName(request.getName());
			}
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("the createUser is null");
			}
			emailBulder.setCreateUser(request.getCreateUser());
			emailBulder.setLastUpdateUser(request.getCreateUser());
			
			emailBulder.setCreateTime(System.currentTimeMillis());
			emailBulder.setLastUpdateTime(emailBulder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(emailBulder.build());
			pipeServer.write(channelWriteData);
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		createEmailMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void updateEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailUpdateResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailTable.Builder emailTableBuilder = null;
    	
    	ChannelWriteData channelWriteData = null;
    	
    	EnnMonitorAlarmConfigEmailUpdateResponse.Builder responseBuilder = EnnMonitorAlarmConfigEmailUpdateResponse.newBuilder();
    	logger.info("updateEmail request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			emailTableBuilder = EnnMonitorAlarmConfigEmailTable.newBuilder();
    			
    			emailTableBuilder.setId(request.getId());
    			
    			if (request.getGroupName() != null) {
    				emailTableBuilder.setGroupName(request.getGroupName());
    			}
    			
    			if (request.getMail() != null) {
    				emailTableBuilder.setMail(request.getMail());
    			}
    			
    			if (request.getName() != null) {
    				emailTableBuilder.setName(request.getName());
    			}
    			
    			if (request.getLastUpdateUser() != null) {
    				emailTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			}
    			emailTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
    			channelWriteData.setObject(emailTableBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
    	
    	updateEmailMetrics.markEvent();

    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
    
	public void deleteEnnMonitorAlarmConfigEmail(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailDeleteResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailDeleteResponse.Builder responseBuilder = EnnMonitorAlarmConfigEmailDeleteResponse.newBuilder();
		EnnMonitorAlarmConfigEmailTable.Builder emailBuilder = EnnMonitorAlarmConfigEmailTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("deleteEmail request: " + request.toString());
		if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		emailBuilder.setId(request.getId());
    		
    		try {
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
    			channelWriteData.setObject(emailBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
    	
    	deleteEmailMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
	}
    
	public void getMaxEnnMonitorAlarmConfigEmailDeletedSeqNo(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailMaxDeletedRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetMaxDeletedResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailGetMaxDeletedResponse.Builder response = EnnMonitorAlarmConfigEmailGetMaxDeletedResponse.newBuilder();
    	
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
    
	public void getEnnMonitorAlarmConfigEmailValid(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetValidResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailGetValidResponse.Builder response = EnnMonitorAlarmConfigEmailGetValidResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorAlarmConfigEmailTable> emailTableList = null;
    	
    	query.put(EnnMonitorAlarmConfigEmailTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorAlarmConfigEmailTableField.SeqNoFieldName, 1);
    	
    	try {
    		emailTableList = emailTable.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllEnnMonitorAlarmConfigEmailTableList(emailTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    
	public void getEnnMonitorAlarmConfigEmailDeleted(enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.alarm.config.email.parameter.EnnMonitorAlarmConfigEmailGetDeletedResponse> responseObserver) {
		EnnMonitorAlarmConfigEmailGetDeletedResponse.Builder response = EnnMonitorAlarmConfigEmailGetDeletedResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorAlarmConfigEmailGetDeleted.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorAlarmConfigEmailGetDeleted> deletedDataList = new ArrayList<EnnMonitorAlarmConfigEmailGetDeleted>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
    		if (deletedTableList != null) {
    			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
    				deletedData = EnnMonitorAlarmConfigEmailGetDeleted.newBuilder();
    				deletedData.setId(deletedTable.getId());
    				deletedData.setSeqNo(deletedTable.getSeqNo());
    				deletedDataList.add(deletedData.build());
        		}
    		}
    		
    		response.setIsSuccess(true);
    		response.addAllEnnMonitorAlarmConfigEmailGetDeletedList(deletedDataList);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		response.setIsSuccess(false);
			response.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

}
