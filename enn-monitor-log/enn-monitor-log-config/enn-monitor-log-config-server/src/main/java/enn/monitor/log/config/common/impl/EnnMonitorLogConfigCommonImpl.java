package enn.monitor.log.config.common.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.mongo.autoinc.MongoAutoIncTableCtl;
import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigBatchIdGetResponse;
import enn.monitor.log.config.common.parameters.EnnMonitorLogConfigCommonBatchIdGetRequest;
import enn.monitor.log.config.common.server.EnnMonitorLogConfigCommonServerGrpc;

public class EnnMonitorLogConfigCommonImpl extends EnnMonitorLogConfigCommonServerGrpc.EnnMonitorLogConfigCommonServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private MongoAutoIncTableCtl autoIncTableCtl = null;
	
	public EnnMonitorLogConfigCommonImpl(MongoAutoIncTableCtl autoIncTableCtl) {
		this.autoIncTableCtl = autoIncTableCtl;
	}
	
	public void getBatchId(EnnMonitorLogConfigCommonBatchIdGetRequest request,
	        io.grpc.stub.StreamObserver<EnnMonitorLogConfigBatchIdGetResponse> responseObserver) {
		EnnMonitorLogConfigBatchIdGetResponse.Builder responseBuilder = null;
		
		logger.info("getBatchId request: " + request.toString());
		
		responseBuilder = EnnMonitorLogConfigBatchIdGetResponse.newBuilder();
		try {
			responseBuilder.setBatchId(autoIncTableCtl.autoInc(request.getKey() + "_micklongen_batchId"));
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

}
