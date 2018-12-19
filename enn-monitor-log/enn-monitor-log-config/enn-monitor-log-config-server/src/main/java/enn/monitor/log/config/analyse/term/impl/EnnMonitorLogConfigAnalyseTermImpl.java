package enn.monitor.log.config.analyse.term.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermCreateResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermDeleteResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermGetResponse;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermTable;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateRequest;
import enn.monitor.log.config.analyse.term.parameters.EnnMonitorLogConfigAnalyseTermUpdateResponse;
import enn.monitor.log.config.analyse.term.server.EnnMonitorLogConfigAnalyseTermServerGrpc;
import enn.monitor.log.config.analyse.term.tablectl.EnnMonitorLogConfigAnalyseTermTableCtl;
import enn.monitor.log.config.analyse.term.tablectl.EnnMonitorLogConfigAnalyseTermTableField;

public class EnnMonitorLogConfigAnalyseTermImpl extends EnnMonitorLogConfigAnalyseTermServerGrpc.EnnMonitorLogConfigAnalyseTermServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncTableCtl = null;
	private EnnMonitorLogConfigAnalyseTermTableCtl ennMonitorLogConfigAnalyseTermTableCtl = null;
	
	public EnnMonitorLogConfigAnalyseTermImpl(MongoAutoIncTableCtl autoIncTableCtl, EnnMonitorLogConfigAnalyseTermTableCtl ennMonitorLogConfigAnalyseTermTableCtl) {
		this.autoIncTableCtl = autoIncTableCtl;
		this.ennMonitorLogConfigAnalyseTermTableCtl = ennMonitorLogConfigAnalyseTermTableCtl;
	}

	public void getAnalyseTerm(EnnMonitorLogConfigAnalyseTermGetRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigAnalyseTermGetResponse> responseObserver) {
		EnnMonitorLogConfigAnalyseTermGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getAnalyseTerm request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName, request.getId());
		}
		
		basicDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.BelongToServiceIdFieldName, request.getBelongToServiceId());
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorLogConfigAnalyseTermGetResponse.newBuilder();
		try {
			List<EnnMonitorLogConfigAnalyseTermTable> tableList = ennMonitorLogConfigAnalyseTermTableCtl.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllAnalyseTermTable(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createAnalyseTerm(EnnMonitorLogConfigAnalyseTermCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigAnalyseTermCreateResponse> responseObserver) {
    	EnnMonitorLogConfigAnalyseTermCreateResponse.Builder responseBuilder = EnnMonitorLogConfigAnalyseTermCreateResponse.newBuilder();
    	EnnMonitorLogConfigAnalyseTermTable.Builder analyseTermBuilder = EnnMonitorLogConfigAnalyseTermTable.newBuilder();
    	
    	StringBuilder termKey = new StringBuilder();
		
		logger.info("createAnalyseTerm request: " + request.toString());
		
		try {
			if (request.getAddTerm() == null) {
				throw new Exception("the addTerm is null");
			}
			analyseTermBuilder.setAddTerm(request.getAddTerm().toLowerCase());
			
			if (request.getFilterTerm() == null) {
				throw new Exception("the filterTerm is null");
			}
			analyseTermBuilder.setFilterTerm(request.getFilterTerm().toLowerCase());
			analyseTermBuilder.setId(autoIncTableCtl.autoInc(ennMonitorLogConfigAnalyseTermTableCtl.getTableName() + "_ID"));
			analyseTermBuilder.setBelongToServiceId(request.getBelongToServiceId());
			
			termKey.append(analyseTermBuilder.getBelongToServiceId());
			if (analyseTermBuilder.getAddTerm() != null && analyseTermBuilder.getAddTerm().equals("") == false) {
				termKey.append("-");
				termKey.append(analyseTermBuilder.getAddTerm());
			}
			if (analyseTermBuilder.getFilterTerm() != null && analyseTermBuilder.getFilterTerm().equals("") == false) {
				termKey.append("-");
				termKey.append(analyseTermBuilder.getFilterTerm());
			}
			analyseTermBuilder.setAnalyseTermKey(termKey.toString());
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("createrUser is null");
			}
			
			analyseTermBuilder.setCreateUser(request.getCreateUser());
			analyseTermBuilder.setLastUpdateUser(request.getCreateUser());
			
			analyseTermBuilder.setCreateTime(System.currentTimeMillis());
			analyseTermBuilder.setLastUpdateTime(analyseTermBuilder.getCreateTime());

			ennMonitorLogConfigAnalyseTermTableCtl.getMongoDBCtrl().insert(analyseTermBuilder.build(), false);
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void updateAnalyseTerm(EnnMonitorLogConfigAnalyseTermUpdateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigAnalyseTermUpdateResponse> responseObserver) {
    	EnnMonitorLogConfigAnalyseTermTable.Builder analyseTermTableBuilder = null;
    	
    	BasicDBObject queryDBObject = new BasicDBObject();
    	
    	StringBuilder termKey = new StringBuilder();
    	
    	EnnMonitorLogConfigAnalyseTermUpdateResponse.Builder responseBuilder = EnnMonitorLogConfigAnalyseTermUpdateResponse.newBuilder();
    	logger.info("updateAnalyse request: " + request.toString());
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		try {
    			analyseTermTableBuilder = EnnMonitorLogConfigAnalyseTermTable.newBuilder();
    			
    			analyseTermTableBuilder.setId(request.getId());
    			
    			if (request.getAddTerm() != null) {
    				analyseTermTableBuilder.setAddTerm(request.getAddTerm());
    			}
    			
    			if (request.getFilterTerm() != null) {
    				analyseTermTableBuilder.setFilterTerm(request.getFilterTerm());
    			}
    			
				analyseTermTableBuilder.setBelongToServiceId(request.getBelongToServiceId());
				
				termKey.append(analyseTermTableBuilder.getBelongToServiceId());
    			if (analyseTermTableBuilder.getAddTerm() != null && analyseTermTableBuilder.getAddTerm().equals("") == false) {
    				termKey.append("-");
    				termKey.append(analyseTermTableBuilder.getAddTerm());
    			}
    			if (analyseTermTableBuilder.getFilterTerm() != null && analyseTermTableBuilder.getFilterTerm().equals("") == false) {
    				termKey.append("-");
    				termKey.append(analyseTermTableBuilder.getFilterTerm());
    			}
    			analyseTermTableBuilder.setAnalyseTermKey(termKey.toString());
    			
    			if (request.getLastUpdateUser() == null || request.getLastUpdateUser().equals("") == true) {
    				throw new Exception("the lastUpdateUser is null");
    			}
    			analyseTermTableBuilder.setLastUpdateUser(request.getLastUpdateUser());
    			
    			analyseTermTableBuilder.setLastUpdateTime(System.currentTimeMillis());
    			
    			queryDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName, analyseTermTableBuilder.getId());
    			ennMonitorLogConfigAnalyseTermTableCtl.getMongoDBCtrl().update(queryDBObject, analyseTermTableBuilder.build(), false, false, false);
    			
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

    public void deleteAnalyseTerm(EnnMonitorLogConfigAnalyseTermDeleteRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigAnalyseTermDeleteResponse> responseObserver) {
    	EnnMonitorLogConfigAnalyseTermDeleteResponse.Builder responseBuilder = EnnMonitorLogConfigAnalyseTermDeleteResponse.newBuilder();
    	
    	BasicDBObject basicDBObject = new BasicDBObject();
    	
    	logger.info("deleteAnalyseTerm request: " + request.toString());
    	
    	if (request.getId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the id is null");
    	} else {
    		basicDBObject.put(EnnMonitorLogConfigAnalyseTermTableField.IdFieldName, request.getId());
    		
    		try {
    			ennMonitorLogConfigAnalyseTermTableCtl.getMongoDBCtrl().remove(basicDBObject, false);
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
}
