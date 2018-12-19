package enn.monitor.log.train.master.client;

import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobCtlRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterGetJobRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterReportJobStatusRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainMasterServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class EnnMonitorLogTrainMasterClient {
	
	private ManagedChannel channel;
	private EnnMonitorLogTrainMasterServerGrpc.EnnMonitorLogTrainMasterServerBlockingStub futureBlockingStub = null;

	public EnnMonitorLogTrainMasterClient(String host, int port) {
		channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext(true)
				.build();
		futureBlockingStub = EnnMonitorLogTrainMasterServerGrpc.newBlockingStub(channel);
	}
	
	public EnnMonitorLogTrainMasterGetJobRsp getJob(EnnMonitorLogTrainMasterGetJobReq request) {
		return futureBlockingStub.getJob(request);
	}
	
	public EnnMonitorLogTrainMasterGetJobCtlRsp getJobCtl(EnnMonitorLogTrainMasterGetJobCtlReq request) {
		return futureBlockingStub.getJobCtl(request);
	}
	
	public EnnMonitorLogTrainMasterReportJobStatusRsp reportJobStatus(EnnMonitorLogTrainMasterReportJobStatusReq request) {
		return futureBlockingStub.reportJobStatus(request);
	}
	
	public EnnMonitorLogTrainJobDeployRsp deployJob(EnnMonitorLogTrainJobDeployReq request) {
		return futureBlockingStub.deloyJob(request);
	}
	
	public EnnMonitorLogTrainJobCtlRsp ctlJob(EnnMonitorLogTrainJobCtlReq request) {
		return futureBlockingStub.ctlJob(request);
	}
	
	public EnnMonitorLogTrainJobGeStatusRsp getJobStatus(EnnMonitorLogTrainJobGeStatusReq request) {
		return futureBlockingStub.getJobStatus(request);
	}
	
	public EnnMonitorLogTrainJogGetBestJobRsp getBestJob(EnnMonitorLogTrainJogGetBestJobReq request) {
		return futureBlockingStub.getBestJob(request);
	}

}
