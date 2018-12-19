package enn.monitor.framework.ai.nn;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.ai.nn.NNObject.NNLayer;
import enn.monitor.framework.ai.nn.NNObject.NNRelation;
import enn.monitor.framework.ai.nn.activation.NNActivationEnum;
import enn.monitor.framework.ai.nn.activation.NNActivationFactory;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNFramework {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static List<Double> compute(NNObject nnObject, List<Double> inputList, List<Double> targetOutputList, 
			NNActivationEnum nnAcitvationEnum, NNWeightEnum nnWeightEnum, double a, double multiple) throws Exception {
		int i, j, k;
		double sum = 0.0;
		
		NNLayer preNNData = null;
		NNLayer curNNData = null;
		
		NNRelation nnRelation = null;
		
		if (nnObject == null) {
			logger.error("the nnObject is null");
			return null;
		}

		nnObject.initInput(inputList);
		for (i = 1; i <= nnObject.getNumLayers(); ++i) {
			preNNData = nnObject.getNNData(i - 1);
			curNNData = nnObject.getNNData(i);
			
			curNNData.getxList().clear();
			curNNData.getyList().clear();
			
			nnRelation = nnObject.getNNRelation(i - 1);
			
			for (j = 0; j < nnRelation.getOutput(); ++j) {
				sum = 0.0;
				for (k = 0; k < nnRelation.getInput() - 1; ++k) {
					sum += preNNData.getyList().get(k) * nnRelation.getWeightListList().get(j).get(k);
				}
				sum += preNNData.getBias() * nnRelation.getWeightListList().get(j).get(k);
				curNNData.addX(sum);
				curNNData.addY(NNActivationFactory.compute(nnAcitvationEnum, sum, a, multiple));
			}
			
		}
		
		if (targetOutputList != null) {
			NNActivationFactory.backPropagate(nnAcitvationEnum, nnWeightEnum, nnObject, targetOutputList, nnObject.getOutputList());
		}
		
		return nnObject.getOutputList();
	}
	
}
