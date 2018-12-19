package enn.monitor.log.ai.train.nn.core;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.log.ai.train.nn.parameter.TrainNNParameter;
import enn.monitor.log.ai.train.nn.parameter.TrainNNResultParameter;
import enn.monitor.log.train.common.nn.data.TrainNNData;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJob;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlEnum;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobCtlReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobDeployReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobGeStatusRsp;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobInput;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobOutput;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobReq;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJogGetBestJobRsp;
import enn.monitor.log.train.util.CoreContextUtil;
import enn.monitor.log.train.util.EnnMonitorLogTrainMasterClientUtil;

public class TrainNNCore implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private TrainNNFSM trainNNFSM = null;
	
	private NNObject nnObject = null;
	private List<List<Double>> inputListList = null;
	private List<List<Double>> targetOutputList = null;
	
	private TrainNNParameter nnConfigureParameter = null;
	
	private boolean start = true;
	
	private TrainNNData trainNNData = null;
	
	private Gson gson = new Gson();
	
	public TrainNNCore(TrainNNFSM trainNNFSM) {
		this.trainNNFSM = trainNNFSM;
	}

	public void train() {
		start = true;
		new Thread(this).start();
	}
	
	public NNObject getNnObject() {
		return nnObject;
	}

	public void setNnObject(NNObject nnObject) {
		this.nnObject = nnObject;
	}
	
	public List<List<Double>> getInputListList() {
		return inputListList;
	}

	public void setInputListList(List<List<Double>> inputListList) {
		this.inputListList = inputListList;
	}

	public List<List<Double>> getTargetOutputList() {
		return targetOutputList;
	}

	public void setTargetOutputList(List<List<Double>> targetOutputList) {
		this.targetOutputList = targetOutputList;
	}
	
	public TrainNNData getTrainNNData() {
		return trainNNData;
	}

	public void setTrainNNData(TrainNNData trainNNData) {
		this.trainNNData = trainNNData;
	}

	public void setTrainNNParameter(TrainNNParameter nnConfigureParameter) {
		this.nnConfigureParameter = nnConfigureParameter;
	}
	
	public TrainNNParameter getNNConfigureParameter() {
		return nnConfigureParameter;
	}
	
	public void stop() {
		start = false;
	}

	@Override
	public void run() {
		training();
	}
	
	private boolean training() {
		int i;
		
		EnnMonitorLogTrainJob.Builder trainJob = null;
		
		TrainNNResultParameter trainNNResultParameter = null;
		
		List<EnnMonitorLogTrainJobStatus> jobStatusList = null;
		EnnMonitorLogTrainJobGeStatusRsp jobStatusRsp = null;
		
		EnnMonitorLogTrainJogGetBestJobRsp getBestJobRsp = null;
		
		logger.info("start Train");
		
		if (nnObject != null && inputListList != null && targetOutputList != null) {
			try {
				// deploy job
				trainJob = EnnMonitorLogTrainJob.newBuilder();
				trainJob.setJobId(1000l);
				trainJob.setJob(gson.toJson(nnObject));
				trainJob.setGenomNum(320);
				trainJob.setChangeParameter(50);
				trainJob.setEliteGenerator(50);
				
				for (i = 0; i < inputListList.size(); ++i) {
					trainJob.addInput(EnnMonitorLogTrainJobInput.newBuilder().addAllInput(inputListList.get(i)).build());
				}
				for (i = 0; i < targetOutputList.size(); ++i) {
					trainJob.addOutput(EnnMonitorLogTrainJobOutput.newBuilder().addAllOutput(targetOutputList.get(i)).build());
				}
				
				logger.info("deployJob");
				EnnMonitorLogTrainMasterClientUtil.getEnnMonitorLogTrainMasterClient().deployJob(
						EnnMonitorLogTrainJobDeployReq.newBuilder().setJob(trainJob.build()).build());
				
				// 获取状态
				while (start) {
					try {
						logger.debug("get jobStatus");
						jobStatusRsp = EnnMonitorLogTrainMasterClientUtil.getEnnMonitorLogTrainMasterClient().getJobStatus(EnnMonitorLogTrainJobGeStatusReq.newBuilder().setJobId(1000l).build());
						if (jobStatusRsp.getIsSuccess() == true) {
							jobStatusList = jobStatusRsp.getJobStatusList();
							
							trainNNResultParameter = new TrainNNResultParameter();
							trainNNResultParameter.setJobStatusList(jobStatusList);
							trainNNFSM.addEvent(new TrainNNEvent(TrainNNEventEnum.UpdateResult, trainNNResultParameter));
							
							for (i = 0; i < jobStatusList.size(); ++i) {
								if (jobStatusList.get(i).getBestGeneration() <= 0) {
									continue;
								}
								if (jobStatusList.get(i).getBestError() <= nnConfigureParameter.getDeviation()) {
									logger.info("training completed");
									break;
								}
							}
							if (i != jobStatusList.size()) {
								break;
							}
						} else {
							logger.error(jobStatusRsp.getError());
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					} finally {
						try {
							Thread.sleep(3000);
						} catch (Exception e1) {
							logger.error(e1.getMessage(), e1);
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		} else {
			return false;
		}
		
		if (jobStatusList != null) {
			getBestJobRsp = EnnMonitorLogTrainMasterClientUtil.getEnnMonitorLogTrainMasterClient().getBestJob(EnnMonitorLogTrainJogGetBestJobReq.newBuilder().setJobId(1000l).build());
			if (getBestJobRsp.getIsSuccess() == true) {
				CoreContextUtil.setNnObject(gson.fromJson(getBestJobRsp.getBestJob(), NNObject.class));
				CoreContextUtil.setTrainNNData(trainNNData);
			} else {
				CoreContextUtil.setNnObject(null);
				CoreContextUtil.setTrainNNData(null);
			}
		} else {
			CoreContextUtil.setNnObject(null);
			CoreContextUtil.setTrainNNData(null);
		}
		
		EnnMonitorLogTrainMasterClientUtil.getEnnMonitorLogTrainMasterClient().ctlJob(
				EnnMonitorLogTrainJobCtlReq.newBuilder().setCtl(EnnMonitorLogTrainJobCtlEnum.Stop).setJobId(1000l).build());
		
		return true;
	}
	
	public void replace(TrainNNResultParameter parameter) {
		EnnMonitorLogTrainJobCtlReq.Builder reqBuilder = EnnMonitorLogTrainJobCtlReq.newBuilder();
		
		if (parameter.getIdentityId() != null && parameter.getIdentityId().equals("") == false) {
			reqBuilder.setIdentityId(parameter.getIdentityId());
		}
		reqBuilder.setJobId(parameter.getJobId());
		reqBuilder.setCtl(EnnMonitorLogTrainJobCtlEnum.Replace);
		EnnMonitorLogTrainMasterClientUtil.getEnnMonitorLogTrainMasterClient().ctlJob(reqBuilder.build());
	}
	
}
