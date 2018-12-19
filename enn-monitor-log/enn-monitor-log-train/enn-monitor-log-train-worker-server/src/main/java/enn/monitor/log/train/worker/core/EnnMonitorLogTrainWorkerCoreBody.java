package enn.monitor.log.train.worker.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.util.NNParameterAdjustUtil;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.framework.log.channel.ChannelWriteData;
import enn.monitor.log.train.proto.EnnMonitorLogTrainJobStatus;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobData;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobEnum;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;
import enn.monitor.log.train.worker.util.EnnMonitorLogTrainWorkerUtil;

public class EnnMonitorLogTrainWorkerCoreBody implements Runnable {
	
	protected static final Logger logger = LogManager.getLogger();
	
	protected long jobId = -1l;
	
	protected long bodyVersion = 0l;
	
	protected long changeParameter = 200;
	
	protected List<List<Double>> inputListList = null;
	protected List<List<Double>> targetOutputList = null;
	protected NNObject bestNNObject = null;
	
	protected Random random = new Random();
	
	protected Gson gson = new Gson();
	
	protected long bodyId = -1l;
	
	protected int count = 0;
	protected int gereration = 0;
	protected double minTotalError = Double.MAX_VALUE;
	
	protected EnnMonitorLogTrainWorkerJobmsg jobmsg = null;
	
	public EnnMonitorLogTrainWorkerCoreBody(EnnMonitorLogTrainWorkerJobmsg jobmsg, long bodyId, long jobId, NNObject nnObject, 
			List<List<Double>> inputListList, List<List<Double>> targetOutputList, long changeParameter) {
		this.bodyId = bodyId;
		this.jobId = jobId;
		this.inputListList = inputListList;
		this.targetOutputList = targetOutputList;
		this.bestNNObject = nnObject.cloneNNObject();
		this.changeParameter = changeParameter;
		
		this.jobmsg = jobmsg;
	}

	@Override
	public void run() {
		long originVersion = bodyVersion;
		
		NNObject nnObject = bestNNObject.cloneNNObject();
		
		logger.info("core thread");
		
		if (nnObject != null && inputListList != null && targetOutputList != null) {
			try {
				reportJobStatus(nnObject);
				
				while (originVersion == bodyVersion) {
					trainNN(nnObject);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				logger.info("train completed, BodyId is " + bodyId);
			}
		}
	}
	
	protected void trainNN(NNObject nnObject) throws Exception {
		int i, j;
		List<Double> inputList = null;
		
		double minError = Double.MAX_VALUE;
		
		nnObject.clearError();
		for (i = 0; i < inputListList.size(); ++i) {
			inputList = new ArrayList<Double>(inputListList.get(i));
			// 添加噪声
			for (j = 0; j < inputList.size(); ++j) {
				inputList.set(j, inputList.get(j) + nnObject.getMaxNoise() * (random.nextDouble() - random.nextDouble()));
			}
			NNFramework.compute(nnObject, inputList, targetOutputList.get(i), NNActivationEnum.Sigmoid, NNWeightEnum.Adagrad, 0, 0);
		}
		
		++gereration;
		
		if (minTotalError > nnObject.getError()) {
			count = 0;
			minTotalError = nnObject.getError();
			bestNNObject = nnObject.cloneNNObject();
			
			logger.debug("minTotalError -------------------------------------------------------------------- : " + minTotalError);
			
			reportJobStatus(nnObject);
		} else if (bestNNObject != null && nnObject.getError() / 10 > minTotalError) {
			logger.info("reset bestNNObject");
			nnObject = bestNNObject.cloneNNObject();
		}
		
		if (minError > nnObject.getError()) {
			minError = nnObject.getError();
			count = 0;
		} else {
			++count;
			if (count >= changeParameter) {
				minError = nnObject.getError();
				count = 0;
				logger.debug("readjust -------------------------------------------------------------------- minError: " + minError);
				
				nnObject.setLearningRate(NNParameterAdjustUtil.adjustParameter(nnObject.getLearningRate(), 0.8, 1.2));
				nnObject.setMomentum(NNParameterAdjustUtil.adjustParameter(nnObject.getMomentum(), 0.8, 1.2));
				nnObject.setMaxNoise(NNParameterAdjustUtil.adjustParameter(nnObject.getMaxNoise(), 0.8, 1.2));
				nnObject.adjustBias(0.8, 1.2);
			}
		}
	}
	
	protected void reportJobStatus(NNObject nnObject) throws Exception {
		EnnMonitorLogTrainWorkerJobData jobData = null;
		ChannelWriteData channelWriteData = null;
		
		if (gereration <= 0) {
			return;
		}
		
		jobData = new EnnMonitorLogTrainWorkerJobData();
		jobData.setBodyId(bodyId);
		jobData.setJobStatus(EnnMonitorLogTrainJobStatus.newBuilder()
					.setJobId(jobId)
					.setIdentityId(EnnMonitorLogTrainWorkerUtil.generateIdentityId())
					.setBestJob(gson.toJson(nnObject))
					.setBestGeneration(gereration)
					.setBestError(nnObject.getError())
					.build());
		channelWriteData = new ChannelWriteData(EnnMonitorLogTrainWorkerJobEnum.ReportJobStatus, jobData);
		jobmsg.write(channelWriteData);
	}
	
	protected void replaceBestJob() throws Exception {
		EnnMonitorLogTrainWorkerJobData jobData = null;
		ChannelWriteData channelWriteData = null;
		
		jobData = new EnnMonitorLogTrainWorkerJobData();
		jobData.setBodyId(bodyId);
		jobData.setJobId(jobId);
		channelWriteData = new ChannelWriteData(EnnMonitorLogTrainWorkerJobEnum.ReplaceBestJob, jobData);
		jobmsg.write(channelWriteData);
	}
	
	public void stop() {
		logger.info("task stop");
		++bodyVersion;
	}
	
	public void setNNObject(NNObject nnObject) {
		if (minTotalError > nnObject.getError()) {
			count = 0;
			minTotalError = nnObject.getError();
			bestNNObject = nnObject.cloneNNObject();
		}
	}

}
