package enn.monitor.log.config.cache.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.config.cache.event.EnnMonitorLogConfigCacheEventUtil;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse;
import enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse;
import enn.monitor.log.config.cache.server.EnnMonitorLogConfigCacheServerGrpc;
import enn.monitor.log.config.cache.template.EnnMonitorLogConfigCacheTemplateUtil;

public class EnnMonitorLogConfigCacheImpl extends EnnMonitorLogConfigCacheServerGrpc.EnnMonitorLogConfigCacheServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	public void getTagByTemplateKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheResponse> responseObserver) {
		Long eventId = null;
		String eventName = null;
		
		EnnMonitorLogConfigCacheResponse.Builder responseBuilder = null;
		
		responseBuilder = EnnMonitorLogConfigCacheResponse.newBuilder();
		
		try {
			if (request.getTemplateKey() == null || request.getTemplateKey().equals("") == true) {
				throw new Exception("the templateKey is null");
			}
			
			eventId = EnnMonitorLogConfigCacheTemplateUtil.getEventId(request.getTemplateKey());
			if (eventId != null) {
				eventName = EnnMonitorLogConfigCacheEventUtil.getEvent(eventId);
				if (eventName != null && eventName.equals("") == false) {
					responseBuilder.setTag(eventName);
				}
			}
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
	
	public void getTagIdByTagKey(enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdRequest request,
	        io.grpc.stub.StreamObserver<enn.monitor.log.config.cache.parameters.EnnMonitorLogConfigCacheTagIdResponse> responseObserver) {
		Long eventId = null;
		
		EnnMonitorLogConfigCacheTagIdResponse.Builder responseBuilder = null;
		responseBuilder = EnnMonitorLogConfigCacheTagIdResponse.newBuilder();
		
		try {
			if (request.getTag() == null || request.getTag().equals("") == true) {
				throw new Exception("the tag is null");
			}
			
			eventId = EnnMonitorLogConfigCacheEventUtil.getEventId(request.getTag());
			if (eventId != null) {
				responseBuilder.setTagId(eventId);
			}
			
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

}
