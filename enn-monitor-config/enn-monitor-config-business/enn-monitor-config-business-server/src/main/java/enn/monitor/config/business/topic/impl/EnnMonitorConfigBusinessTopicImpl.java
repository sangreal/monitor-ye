package enn.monitor.config.business.topic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import com.mongodb.BasicDBObject;

import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicCreateResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicDeleteResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedData;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetDeletedDataListResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetMaxDeletedResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicGetValidDataListResponse;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicTable;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateRequest;
import enn.monitor.config.business.topic.parameters.EnnMonitorConfigBusinessTopicUpdateResponse;
import enn.monitor.config.business.topic.server.EnnMonitorConfigBusinessTopicServerGrpc;
import enn.monitor.config.business.topic.tablectl.EnnMonitorConfigBusinessTopicTableCtl;
import enn.monitor.config.business.topic.tablectl.EnnMonitorConfigBusinessTopicTableField;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTable;
import enn.monitor.framework.pipe.common.EnnMonitorFrameworkPipeDeletedTableCtl;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;

public class EnnMonitorConfigBusinessTopicImpl extends EnnMonitorConfigBusinessTopicServerGrpc.EnnMonitorConfigBusinessTopicServerImplBase {
	
private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getTopicMetrics = MetricManager.getCounterMetric(EnnMonitorConfigBusinessTopicImpl.class, "getTopic");
	private CounterMetric createTopicMetrics = MetricManager.getCounterMetric(EnnMonitorConfigBusinessTopicImpl.class, "createTopic");
	private CounterMetric updateTopicMetrics = MetricManager.getCounterMetric(EnnMonitorConfigBusinessTopicImpl.class, "updateTopic");
	private CounterMetric deleteTopicMetrics = MetricManager.getCounterMetric(EnnMonitorConfigBusinessTopicImpl.class, "deleteTopic");
	
	private EnnMonitorConfigBusinessTopicTableCtl topicTable = null;
	private EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorConfigBusinessTopicImpl(
			EnnMonitorConfigBusinessTopicTableCtl topicTable, EnnMonitorFrameworkPipeDeletedTableCtl deletedTableCtl, EnnMonitorFrameworkPipeServerThread pipeServer) {
		this.topicTable = topicTable;
		this.deletedTableCtl = deletedTableCtl;
		this.pipeServer = pipeServer;
	}
	
	public void getTopic(EnnMonitorConfigBusinessTopicGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicGetResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getTopic request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorConfigBusinessTopicTableField.IdFieldName, request.getId());
		}
		
		if (request.getSource() != null && request.getSource().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigBusinessTopicTableField.SourceFieldName, request.getSource());
		}
		
		if (request.getTopic() != null && request.getTopic().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigBusinessTopicTableField.TopicFieldName, request.getTopic());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigBusinessTopicTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorConfigBusinessTopicTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorConfigBusinessTopicGetResponse.newBuilder();
		try {
			List<EnnMonitorConfigBusinessTopicTable> tableList = topicTable.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllTopic(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		getTopicMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void createTopic(EnnMonitorConfigBusinessTopicCreateRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicCreateResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicCreateResponse.Builder responseBuilder = EnnMonitorConfigBusinessTopicCreateResponse.newBuilder();
		EnnMonitorConfigBusinessTopicTable.Builder topicBulder = EnnMonitorConfigBusinessTopicTable.newBuilder();
		
		ChannelWriteData channelWriteData = null;
		
		logger.info("createTopic request: " + request.toString());
		
		try {
			if (request.getSource() == null || request.getSource().equals("") == true) {
				throw new Exception("the source is null");
			}
			if (request.getTopic() == null || request.getTopic().equals("") == true) {
				throw new Exception("the topic is null");
			}
			
			topicBulder.setSource(request.getSource());
			topicBulder.setTopic(request.getTopic());
			
			topicBulder.setCreateUser(request.getCreateUser());
			topicBulder.setLastUpdateUser(request.getCreateUser());
			
			topicBulder.setCreateTime(System.currentTimeMillis());
			topicBulder.setLastUpdateTime(topicBulder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(topicBulder.build());
			pipeServer.write(channelWriteData);
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		createTopicMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void updateTopic(EnnMonitorConfigBusinessTopicUpdateRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicUpdateResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicTable.Builder topicTableBuilder = null;
    	
    	ChannelWriteData channelWriteData = null;
    	
    	EnnMonitorConfigBusinessTopicUpdateResponse.Builder responseBuilder = EnnMonitorConfigBusinessTopicUpdateResponse.newBuilder();
    	logger.info("updateTopic request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			topicTableBuilder = EnnMonitorConfigBusinessTopicTable.newBuilder();
    			
    			topicTableBuilder.setId(request.getId());
    			
    			if (request.getSource() != null) {
    				topicTableBuilder.setSource(request.getSource());
    			}
    			
    			if (request.getTopic() != null) {
    				topicTableBuilder.setTopic(request.getTopic());
    			}
    			
    			if (request.getLastUpdateUser() != null && request.getLastUpdateUser().trim().equals("") == false) {
    				topicTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			}
    			
    			topicTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Update);
    			channelWriteData.setObject(topicTableBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
    	
    	updateTopicMetrics.markEvent();

    	responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void deleteTopic(EnnMonitorConfigBusinessTopicDeleteRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicDeleteResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicDeleteResponse.Builder responseBuilder = EnnMonitorConfigBusinessTopicDeleteResponse.newBuilder();
		EnnMonitorConfigBusinessTopicTable.Builder topicBuilder = EnnMonitorConfigBusinessTopicTable.newBuilder();
    	
    	ChannelWriteData channelWriteData = null;
		
		logger.info("deleteTopic request: " + request.toString());
		if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		topicBuilder.setId(request.getId());
    		
    		try {
    			channelWriteData = new ChannelWriteData();
    			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Delete);
    			channelWriteData.setObject(topicBuilder.build());
    			pipeServer.write(channelWriteData);
    			
    			responseBuilder.setIsSuccess(true);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    			responseBuilder.setIsSuccess(false);
    			responseBuilder.setError(e.getMessage());
    		}
    	}
    	
    	deleteTopicMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void getMaxDeletedSeqNo(EnnMonitorConfigBusinessTopicGetMaxDeletedRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicGetMaxDeletedResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.Builder response = EnnMonitorConfigBusinessTopicGetMaxDeletedResponse.newBuilder();
    	
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
	
	public void getValidDataList(EnnMonitorConfigBusinessTopicGetValidDataListRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicGetValidDataListResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicGetValidDataListResponse.Builder response = EnnMonitorConfigBusinessTopicGetValidDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorConfigBusinessTopicTable> topicTableList = null;
    	
    	query.put(EnnMonitorConfigBusinessTopicTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorConfigBusinessTopicTableField.SeqNoFieldName, 1);
    	
    	try {
    		topicTableList = topicTable.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllTopicTableList(topicTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
	
	public void getDeletedDataList(EnnMonitorConfigBusinessTopicGetDeletedDataListRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorConfigBusinessTopicGetDeletedDataListResponse> responseObserver) {
		EnnMonitorConfigBusinessTopicGetDeletedDataListResponse.Builder response = EnnMonitorConfigBusinessTopicGetDeletedDataListResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	EnnMonitorConfigBusinessTopicGetDeletedData.Builder deletedData = null;
    	
    	List<EnnMonitorFrameworkPipeDeletedTable> deletedTableList = null;
    	List<EnnMonitorConfigBusinessTopicGetDeletedData> deletedDataList = new ArrayList<EnnMonitorConfigBusinessTopicGetDeletedData>();
    	
    	query.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorFrameworkPipeDeletedTable.SeqNoFieldName, 1);
    	
    	try {
    		deletedTableList = deletedTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
    		if (deletedTableList != null) {
    			for (EnnMonitorFrameworkPipeDeletedTable deletedTable : deletedTableList) {
    				deletedData = EnnMonitorConfigBusinessTopicGetDeletedData.newBuilder();
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
