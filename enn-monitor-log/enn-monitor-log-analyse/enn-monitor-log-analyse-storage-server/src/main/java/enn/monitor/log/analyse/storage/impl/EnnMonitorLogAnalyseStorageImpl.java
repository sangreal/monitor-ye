package enn.monitor.log.analyse.storage.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerChannelWriteOpType;
import enn.monitor.framework.pipe.server.EnnMonitorFrameworkPipeServerThread;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse;
import enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageTable;
import enn.monitor.log.analyse.storage.server.EnnMonitorLogAnalyseStorageServerGrpc;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableCtl;
import enn.monitor.log.analyse.storage.tablectl.EnnMonitorLogAnalyseStorageTableField;

public class EnnMonitorLogAnalyseStorageImpl extends EnnMonitorLogAnalyseStorageServerGrpc.EnnMonitorLogAnalyseStorageServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private EnnMonitorLogAnalyseStorageTableCtl analyseStorageTableCtl = null;
	private EnnMonitorFrameworkPipeServerThread pipeServer = null;
	
	public EnnMonitorLogAnalyseStorageImpl(EnnMonitorFrameworkPipeServerThread pipeServer, EnnMonitorLogAnalyseStorageTableCtl analyseStorageTableCtl) {
		this.pipeServer = pipeServer;
		this.analyseStorageTableCtl = analyseStorageTableCtl;
	}
	
	public void searchNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageSearchResponse> responseObserver) {
		EnnMonitorLogAnalyseStorageSearchResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getTemplate request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogAnalyseStorageTableField.IdFieldName, request.getId());
		}
		
		if (request.getTokenId() > 0) {
			basicDBObject.put(EnnMonitorLogAnalyseStorageTableField.TokenIdFieldName, request.getTokenId());
		}
		
		responseBuilder = EnnMonitorLogAnalyseStorageSearchResponse.newBuilder();
		try {
			List<EnnMonitorLogAnalyseStorageTable> tableList = analyseStorageTableCtl.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllAnalyseStorageTable(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createNN(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageCreateResponse> responseObserver) {
    	EnnMonitorLogAnalyseStorageCreateResponse.Builder responseBuilder = EnnMonitorLogAnalyseStorageCreateResponse.newBuilder();
    	
		EnnMonitorLogAnalyseStorageTable.Builder tokenBulder = EnnMonitorLogAnalyseStorageTable.newBuilder();
		
		ChannelWriteData channelWriteData = null;
		
		logger.info("create analyse storage request: " + request.toString());
		
		try {
			if (request.getTokenId() < 0) {
				throw new Exception("the token id is not set");
			}
			if (request.getNndata() == null || request.getNndata().equals("") == true) {
				throw new Exception("the nndata is null");
			}
			
			tokenBulder.setTokenId(request.getTokenId());
			tokenBulder.setNndata(request.getNndata());
			
			tokenBulder.setCreateTime(System.currentTimeMillis());
			tokenBulder.setLastUpdateTime(tokenBulder.getCreateTime());
			
			channelWriteData = new ChannelWriteData();
			channelWriteData.setOpEnum(EnnMonitorFrameworkPipeServerChannelWriteOpType.Insert);
			channelWriteData.setObject(tokenBulder.build());
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

    public void getLastestNNData(enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.log.analyse.storage.parameters.EnnMonitorLogAnalyseStorageLastestNNDataResponse> responseObserver) {
    	EnnMonitorLogAnalyseStorageLastestNNDataResponse.Builder response = EnnMonitorLogAnalyseStorageLastestNNDataResponse.newBuilder();
    	
    	BasicDBObject query = new BasicDBObject();
    	BasicDBObject order = new BasicDBObject();
    	
    	List<EnnMonitorLogAnalyseStorageTable> templateTableList = null;
    	
    	query.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, new BasicDBObject("$gt", request.getStartSeqNo()));
    	order.put(EnnMonitorLogAnalyseStorageTableField.SeqNoFieldName, 1);
    	
    	try {
    		templateTableList = analyseStorageTableCtl.getMongoDBCtrl().searchData(query, order, 0, request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllAnalyseStorageTable(templateTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
	
}
