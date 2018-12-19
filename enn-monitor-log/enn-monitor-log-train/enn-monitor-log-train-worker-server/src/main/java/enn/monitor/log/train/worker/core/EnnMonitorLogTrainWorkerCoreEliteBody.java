package enn.monitor.log.train.worker.core;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.framework.ai.nn.NNFramework;
import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.util.NNParameterAdjustUtil;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;
import enn.monitor.log.train.worker.jobmsg.EnnMonitorLogTrainWorkerJobmsg;

public class EnnMonitorLogTrainWorkerCoreEliteBody extends EnnMonitorLogTrainWorkerCoreBody {
	
	private long eliteGenerator = 500l;
	private long generatorNow = 0l;
	
	public EnnMonitorLogTrainWorkerCoreEliteBody(
			EnnMonitorLogTrainWorkerJobmsg jobmsg, long eliteGenerator, long bodyId, long jobId, NNObject nnObject, 
			List<List<Double>> inputListList, List<List<Double>> targetOutputList, long changeParameter) {
		super(jobmsg, bodyId, jobId, nnObject, inputListList, targetOutputList, changeParameter);
		this.eliteGenerator = eliteGenerator;
	}

	protected void trainNN(NNObject nnObject) throws Exception {
		int i, j;
		List<Double> inputList = null;
		
		double minError = Double.MAX_VALUE;
		
		if (generatorNow >= eliteGenerator) {
			generatorNow = 0;
			replaceBestJob();
		}
		
		nnObject.clearError();
		for (i = 0; i < inputListList.size(); ++i) {
			inputList = new ArrayList<Double>(inputListList.get(i));
			// 添加噪声
			for (j = 0; j < inputList.size(); ++j) {
				inputList.set(j, inputList.get(j) + nnObject.getMaxNoise() * (random.nextDouble() - random.nextDouble()));
			}
			NNFramework.compute(nnObject, inputList, targetOutputList.get(i), NNActivationEnum.Sigmoid, NNWeightEnum.Adagrad, 0, 0);
		}
		
		++generatorNow;
		++gereration;
		
		if (minTotalError > nnObject.getError()) {
			minTotalError = nnObject.getError();
			bestNNObject = nnObject.cloneNNObject();
			
			logger.debug("elite minTotalError -------------------------------------------------------------------- : " + minTotalError);
			
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
				logger.debug("elite readjust -------------------------------------------------------------------- minError: " + minError);
				
				nnObject.setLearningRate(NNParameterAdjustUtil.adjustParameter(nnObject.getLearningRate(), 0.8, 1.2));
				nnObject.setMomentum(NNParameterAdjustUtil.adjustParameter(nnObject.getMomentum(), 0.8, 1.2));
				nnObject.setMaxNoise(NNParameterAdjustUtil.adjustParameter(nnObject.getMaxNoise(), 0.8, 1.2));
				nnObject.adjustBias(0.8, 1.2);
			}
		}
	}
	
	public void setNNObject(NNObject nnObject) {
		super.setNNObject(nnObject);
		generatorNow = 0;
		
		logger.info("elite getBestJobStatus -------------------------------------------------------------------- : " + nnObject.getError());
	}
	
}
