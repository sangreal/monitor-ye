package enn.monitor.log.train.worker.jobmsg;

import java.util.List;

import enn.monitor.log.train.proto.EnnMonitorLogTrainJob;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtl;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;

public class EnnMonitorLogTrainWorkerJobData {

	private long jobId = -1l;
	private long bodyId = -1l;
	private EnnMonitorLogTrainJobStatus jobStatus = null;
	
	private List<EnnMonitorLogTrainJob> jobList = null;
	
	private List<EnnMonitorLogTrainJobCtl> jobCtlList = null;
	
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getBodyId() {
		return bodyId;
	}
	
	public void setBodyId(long bodyId) {
		this.bodyId = bodyId;
	}

	public EnnMonitorLogTrainJobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(EnnMonitorLogTrainJobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<EnnMonitorLogTrainJob> getJobList() {
		return jobList;
	}

	public void setJobList(List<EnnMonitorLogTrainJob> jobList) {
		this.jobList = jobList;
	}

	public List<EnnMonitorLogTrainJobCtl> getJobCtlList() {
		return jobCtlList;
	}

	public void setJobCtlList(List<EnnMonitorLogTrainJobCtl> jobCtlList) {
		this.jobCtlList = jobCtlList;
	}

}
