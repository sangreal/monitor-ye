package enn.monitor.log.train.worker.jobmsg;

import enn.monitor.framework.log.channel.ChannelWriteOpType;

public enum EnnMonitorLogTrainWorkerJobEnum implements ChannelWriteOpType {
	
	ReportJobStatus, ReplaceBestJob, DeploymentJob, CtlJob, ReportToMaster

}
