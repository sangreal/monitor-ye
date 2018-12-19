package enn.monitor.log.train.master.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.train.master.jobmsg.EnnMonitorLogTrainMasterJobmsg;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJob;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterServerGrpc;

public class EnnMonitorLogTrainMasterImpl extends EnnMonitorLogTrainMasterServerGrpc.EnnMonitorLogTrainMasterServerImplBase {
	
	private static final Logger logger = LogManager.getLogger();
	
    public void getJob(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp> responseObserver) {
    	List<EnnMonitorLogTrainJob> jobList = null;
    	
    	EnnMonitorLogTrainMasterGetJobRsp.Builder jobRsp = EnnMonitorLogTrainMasterGetJobRsp.newBuilder();
    	
    	if (request.getIdentityId() == null || request.getIdentityId().equals("") == true) {
    		jobRsp.setError("the identityId is null");
    		jobRsp.setIsSuccess(false);
    	} else {
    		jobList = EnnMonitorLogTrainMasterJobmsg.getJob(request.getIdentityId());
    		if (jobList == null) {
    			jobRsp.setError("the worker is not exists");
    			jobRsp.setIsSuccess(false);
    		} else {
    			jobRsp.setIsSuccess(true);
    			jobRsp.addAllTrainJob(jobList);
    		}
    	}
    	
    	responseObserver.onNext(jobRsp.build());
        responseObserver.onCompleted();
    }

    public void getJobCtl(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp> responseObserver) {
    	EnnMonitorLogTrainMasterGetJobCtlRsp.Builder jobCtlRsp = EnnMonitorLogTrainMasterGetJobCtlRsp.newBuilder();
    	
    	jobCtlRsp.addAllJobCtl(EnnMonitorLogTrainMasterJobmsg.getJobCtl(request.getIdentityId()));
    	jobCtlRsp.setIsSuccess(true);
    	
    	responseObserver.onNext(jobCtlRsp.build());
        responseObserver.onCompleted();
    }

    public void reportJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp> responseObserver) {
    	EnnMonitorLogTrainMasterReportJobStatusRsp.Builder jobStatusRsp = EnnMonitorLogTrainMasterReportJobStatusRsp.newBuilder();
    	
    	try {
    		if (request.getJobStatusList() == null) {
    			throw new Exception("the jobStatus is null");
    		}
    		
    		for (EnnMonitorLogTrainJobStatus jobStatus : request.getJobStatusList()) {
    			EnnMonitorLogTrainMasterJobmsg.reportJobStatus(jobStatus);
    		}
    		
    		jobStatusRsp.setIsSuccess(true);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		jobStatusRsp.setIsSuccess(false);
    		jobStatusRsp.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(jobStatusRsp.build());
        responseObserver.onCompleted();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void deloyJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp> responseObserver) {
    	EnnMonitorLogTrainJobDeployRsp.Builder deployRsp = EnnMonitorLogTrainJobDeployRsp.newBuilder();
    	
    	try {
    		if (request.getJob() == null) {
    			throw new Exception("the job is null");
    		}
    		
    		EnnMonitorLogTrainMasterJobmsg.deployJob(request.getJob());
    		deployRsp.setIsSuccess(true);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		deployRsp.setIsSuccess(false);
    		deployRsp.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(deployRsp.build());
        responseObserver.onCompleted();
    }

    public void ctlJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp> responseObserver) {
    	EnnMonitorLogTrainJobCtlRsp.Builder jobCtlResp = EnnMonitorLogTrainJobCtlRsp.newBuilder();
    	
    	try {
    		if (request.getCtl() == null) {
    			throw new Exception("the ctl is null");
    		}
    		
    		if (request.getJobId() <= 0) {
    			throw new Exception("jobId is null");
    		}
    		
    		switch (request.getCtl()) {
    		case Replace:
    			if (request.getJobId() <= 0) {
    				throw new Exception("the jobId is null");
    			}
    			EnnMonitorLogTrainMasterJobmsg.replaceJob(request.getIdentityId(), request.getJobId());
    			break;
    		case Stop:
    			EnnMonitorLogTrainMasterJobmsg.stopJob(request.getJobId());
    			break;
    		default:
    			throw new Exception("unexpected ctl, it is " + request.getCtl().name());
    		}
    		
    		jobCtlResp.setIsSuccess(true);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		jobCtlResp.setIsSuccess(false);
    		jobCtlResp.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(jobCtlResp.build());
        responseObserver.onCompleted();
    }

    public void getJobStatus(enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq request,
        io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp> responseObserver) {
    	EnnMonitorLogTrainJobGeStatusRsp.Builder jobGetStatusResp = EnnMonitorLogTrainJobGeStatusRsp.newBuilder();
    	
    	try {
    		if (request.getJobId() < 0) {
    			throw new Exception("the jobid invalid");
    		}
    		
    		jobGetStatusResp.addAllJobStatus(EnnMonitorLogTrainMasterJobmsg.getJobStatus(request.getJobId()));
    		jobGetStatusResp.setIsSuccess(true);
    		
    		logger.debug("getJobStatus: " + jobGetStatusResp.toString());
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		jobGetStatusResp.setIsSuccess(false);
    		jobGetStatusResp.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(jobGetStatusResp.build());
        responseObserver.onCompleted();
    }
    
    public void getBestJob(enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq request,
            io.grpc.stub.StreamObserver<enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp> responseObserver) {
    	EnnMonitorLogTrainJogGetBestJobRsp.Builder getBestJobRsp = EnnMonitorLogTrainJogGetBestJobRsp.newBuilder();
    	
    	String bestJob = null;
    	
    	try {
    		if (request.getJobId() < 0) {
    			throw new Exception("the jobid invalid");
    		}
    		
    		bestJob = EnnMonitorLogTrainMasterJobmsg.getBestJob(request.getJobId());
    		if (bestJob != null && bestJob.equals("") == false) {
    			getBestJobRsp.setBestJob(bestJob);
    		}
    		getBestJobRsp.setIsSuccess(true);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		getBestJobRsp.setIsSuccess(false);
    		getBestJobRsp.setError(e.getMessage());
    	}
    	
    	responseObserver.onNext(getBestJobRsp.build());
        responseObserver.onCompleted();
    }

}
