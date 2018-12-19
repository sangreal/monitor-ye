package enn.monitor.alarm.ticket.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avaje.metric.CounterMetric;
import org.avaje.metric.MetricManager;

import enn.monitor.alarm.ticket.Server.EnnMonitorAlarmTicketServerGrpc;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeleted;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTable;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse;
import enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse;

public class EnnMonitorAlarmTicketImpl extends EnnMonitorAlarmTicketServerGrpc.EnnMonitorAlarmTicketServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private CounterMetric getTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketImpl.class, "getTicket");
	private CounterMetric createTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketImpl.class, "createTicket");
	private CounterMetric updateTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketImpl.class, "updateTicket");
	private CounterMetric deleteTicketMetrics = MetricManager.getCounterMetric(EnnMonitorAlarmTicketImpl.class, "deleteTicket");
	
	private EnnMonitorAlarmTicketHandler ticketHandler = null;
	
	public EnnMonitorAlarmTicketImpl(EnnMonitorAlarmTicketHandler ticketHandler) {
		this.ticketHandler = ticketHandler;
	}
	
	public void getEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetResponse> responseObserver) {
		EnnMonitorAlarmTicketGetResponse.Builder responseBuilder = null;
		
		logger.info("getTicket request: " + request.toString());
		
		responseBuilder = EnnMonitorAlarmTicketGetResponse.newBuilder();
		try {
			List<EnnMonitorAlarmTicketTable> ticketTableList = ticketHandler.getEnnMonitorAlarmTicket(request);
			responseBuilder.setIsSuccess(true);
			responseBuilder.addAllEnnMonitorAlarmTicketTableList(ticketTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		getTicketMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void createEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketCreateResponse> responseObserver) {
    	EnnMonitorAlarmTicketCreateResponse.Builder responseBuilder = EnnMonitorAlarmTicketCreateResponse.newBuilder();
    	
		logger.info("createTicket request: " + request.toString());
		
		try {
			ticketHandler.createEnnMonitorAlarmTicket(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		createTicketMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void updateEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketUpdateResponse> responseObserver) {
    	EnnMonitorAlarmTicketUpdateResponse.Builder responseBuilder = EnnMonitorAlarmTicketUpdateResponse.newBuilder();
    	
		logger.info("updateTicket request: " + request.toString());
		try {
			ticketHandler.updateEnnMonitorAlarmTicket(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
		
		updateTicketMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void deleteEnnMonitorAlarmTicket(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketDeleteResponse> responseObserver) {
    	EnnMonitorAlarmTicketDeleteResponse.Builder responseBuilder = EnnMonitorAlarmTicketDeleteResponse.newBuilder();
		
		logger.info("deleteTicket request: " + request.toString());
		try {
			ticketHandler.deleteEnnMonitorAlarmTicket(request);
			responseBuilder.setIsSuccess(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseBuilder.setIsSuccess(false);
			responseBuilder.setError(e.getMessage());
		}
    	
    	deleteTicketMetrics.markEvent();
		
		responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    public void getEnnMonitorAlarmTicketMaxDeletedSeqNo(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetMaxDeletedResponse> responseObserver) {
    	EnnMonitorAlarmTicketGetMaxDeletedResponse.Builder response = EnnMonitorAlarmTicketGetMaxDeletedResponse.newBuilder();
    	
    	long seqNo = -1;
    	
    	try {
    		seqNo = ticketHandler.getEnnMonitorAlarmTicketMaxDeletedSeqNo();
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

    public void getEnnMonitorAlarmTicketValid(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetValidResponse> responseObserver) {
    	EnnMonitorAlarmTicketGetValidResponse.Builder response = EnnMonitorAlarmTicketGetValidResponse.newBuilder();
    	
    	List<EnnMonitorAlarmTicketTable> ticketTableList = null;
    	
    	try {
    		ticketTableList = ticketHandler.getEnnMonitorAlarmTicketValid(request.getStartSeqNo(), request.getBatchNum());
			response.setIsSuccess(true);
			response.addAllEnnMonitorAlarmTicketTableList(ticketTableList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public void getEnnMonitorAlarmTicketDeleted(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedRequest request,
        io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketGetDeletedResponse> responseObserver) {
    	EnnMonitorAlarmTicketGetDeletedResponse.Builder response = EnnMonitorAlarmTicketGetDeletedResponse.newBuilder();
    	
    	List<EnnMonitorAlarmTicketGetDeleted> deletedDataList = null;
    	
    	try {
    		deletedDataList = ticketHandler.getEnnMonitorAlarmTicketDeleted(request.getStartSeqNo(), request.getBatchNum());
    		
    		response.setIsSuccess(true);
    		response.addAllEnnMonitorAlarmTicketGetDeletedList(deletedDataList);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		response.setIsSuccess(false);
			response.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    
    public void updateEnnMonitorAlarmTicketTransformState(enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformState request,
            io.grpc.stub.StreamObserver<enn.monitor.alarm.ticket.parameter.EnnMonitorAlarmTicketTransformStateResponse> responseObserver) {
    	EnnMonitorAlarmTicketTransformStateResponse.Builder response = EnnMonitorAlarmTicketTransformStateResponse.newBuilder();
    	
    	try {
			ticketHandler.updateEnnMonitorAlarmTicketTransformState(request);
			
			response.setIsSuccess(true);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
    		response.setIsSuccess(false);
			response.setError(e.getMessage());
		}
    	
    	responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    
}
