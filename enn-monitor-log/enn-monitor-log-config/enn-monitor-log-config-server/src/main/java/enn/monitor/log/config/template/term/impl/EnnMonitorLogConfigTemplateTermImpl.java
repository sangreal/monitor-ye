package enn.monitor.log.config.template.term.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermCreateResponse;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermDeleteResponse;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetRequest;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermGetResponse;
import enn.monitor.log.config.template.term.parameters.EnnMonitorLogConfigTemplateTermTable;
import enn.monitor.log.config.template.term.server.EnnMonitorLogConfigTemplateTermServerGrpc;
import enn.monitor.log.config.template.term.tablectl.EnnMonitorLogConfigTemplateTermTableCtl;
import enn.monitor.log.config.template.term.tablectl.EnnMonitorLogConfigTemplateTermTableField;

public class EnnMonitorLogConfigTemplateTermImpl extends EnnMonitorLogConfigTemplateTermServerGrpc.EnnMonitorLogConfigTemplateTermServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncTableCtl = null;
	private EnnMonitorLogConfigTemplateTermTableCtl ennMonitorLogConfigTemplateTermTableCtl = null;
	
	public EnnMonitorLogConfigTemplateTermImpl(MongoAutoIncTableCtl autoIncTableCtl, EnnMonitorLogConfigTemplateTermTableCtl ennMonitorLogConfigTemplateTermTableCtl) {
		this.autoIncTableCtl = autoIncTableCtl;
		this.ennMonitorLogConfigTemplateTermTableCtl = ennMonitorLogConfigTemplateTermTableCtl;
	}

	public void getTemplateTerm(EnnMonitorLogConfigTemplateTermGetRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateTermGetResponse> responseObserver) {
		EnnMonitorLogConfigTemplateTermGetResponse.Builder responseBuilder = null;
		
		BasicDBObject basicDBObject = new BasicDBObject();
		
		logger.info("getTemplateTerm request: " + request.toString());
		
		if (request.getId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.IdFieldName, request.getId());
		}
		
		if (request.getBelongToServiceId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.BelongToServiceIdFieldName, request.getBelongToServiceId());
		}
		
		if (request.getBatchId() > 0) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName, request.getBatchId());
		}
		
		if (request.getCreateUser() != null && request.getCreateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.CreateUserFieldName, request.getCreateUser());
		}
		
		if (request.getLastUpdateUser() != null && request.getLastUpdateUser().equals("") == false) {
			basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.LastUpdateUserFieldName, request.getLastUpdateUser());
		}
		
		responseBuilder = EnnMonitorLogConfigTemplateTermGetResponse.newBuilder();
		try {
			List<EnnMonitorLogConfigTemplateTermTable> tableList = ennMonitorLogConfigTemplateTermTableCtl.getMongoDBCtrl().searchAllData(basicDBObject, null);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllTemplateTermTable(tableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createTemplateTerm(EnnMonitorLogConfigTemplateTermCreateRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateTermCreateResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateTermCreateResponse.Builder responseBuilder = EnnMonitorLogConfigTemplateTermCreateResponse.newBuilder();
    	EnnMonitorLogConfigTemplateTermTable.Builder templateTermBuilder = EnnMonitorLogConfigTemplateTermTable.newBuilder();
		
		logger.info("createTemplateTerm request: " + request.toString());
		
		try {
			if (request.getTemplateTerm() == null) {
				throw new Exception("the templateTerm is null");
			}
			templateTermBuilder.setTemplateTerm(request.getTemplateTerm());
			
			if (request.getBelongToServiceId() < 0) {
				throw new Exception("the belongToServiceId is null");
			}
			templateTermBuilder.setBelongToServiceId(request.getBelongToServiceId());
			
			if (request.getTemplateTermValue() >= 0) {
				templateTermBuilder.setTemplateTermValue(request.getTemplateTermValue());
			}
			
			templateTermBuilder.setIsSelected(request.getIsSelected());
			
			templateTermBuilder.setTemplateTermKey("" + request.getBatchId() + "-" + request.getBelongToServiceId() + "-" + request.getTemplateTerm());
			
			if (request.getBatchId() > 0) {
				templateTermBuilder.setBatchId(request.getBatchId());
			}
			
			templateTermBuilder.setId(autoIncTableCtl.autoInc(ennMonitorLogConfigTemplateTermTableCtl.getTableName() + "_ID"));
			
			if (request.getCreateUser() == null || request.getCreateUser().equals("") == true) {
				throw new Exception("createrUser is null");
			}
			
			templateTermBuilder.setCreateUser(request.getCreateUser());
			templateTermBuilder.setLastUpdateUser(request.getCreateUser());
			
			templateTermBuilder.setCreateTime(System.currentTimeMillis());
			templateTermBuilder.setLastUpdateTime(templateTermBuilder.getCreateTime());

			ennMonitorLogConfigTemplateTermTableCtl.getMongoDBCtrl().insert(templateTermBuilder.build(), false);
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void deleteTemplateTerm(EnnMonitorLogConfigTemplateTermDeleteRequest request,
        io.grpc.stub.StreamObserver<EnnMonitorLogConfigTemplateTermDeleteResponse> responseObserver) {
    	EnnMonitorLogConfigTemplateTermDeleteResponse.Builder responseBuilder = EnnMonitorLogConfigTemplateTermDeleteResponse.newBuilder();
    	
    	BasicDBObject basicDBObject = new BasicDBObject();
    	
    	logger.info("deleteTemplateTerm request: " + request.toString());
    	
    	if (request.getBatchId() < 0) {
    		responseBuilder.setIsSuccess(false);
    		responseBuilder.setError("the batchId is null");
    	} else {
    		basicDBObject.put(EnnMonitorLogConfigTemplateTermTableField.BatchIdFieldName, request.getBatchId());
    		
    		try {
    			ennMonitorLogConfigTemplateTermTableCtl.getMongoDBCtrl().remove(basicDBObject, false);
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
