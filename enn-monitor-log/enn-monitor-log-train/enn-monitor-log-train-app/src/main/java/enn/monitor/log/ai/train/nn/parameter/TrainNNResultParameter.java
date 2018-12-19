package enn.monitor.log.ai.train.nn.parameter;

import java.util.List;

import enn.monitor.log.ai.train.nn.core.TrainNNModeEnum;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;

public class TrainNNResultParameter {
	
	private TrainNNModeEnum nnModeEnum = null;
	private List<EnnMonitorLogTrainJobStatus> jobStatusList = null;
	
	private String identityId = null;
	private long jobId = -1l;

	public TrainNNModeEnum getNnModeEnum() {
		return nnModeEnum;
	}

	public void setNnModeEnum(TrainNNModeEnum nnModeEnum) {
		this.nnModeEnum = nnModeEnum;
	}

	public List<EnnMonitorLogTrainJobStatus> getJobStatusList() {
		return jobStatusList;
	}

	public void setJobStatusList(List<EnnMonitorLogTrainJobStatus> jobStatusList) {
		this.jobStatusList = jobStatusList;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
}
