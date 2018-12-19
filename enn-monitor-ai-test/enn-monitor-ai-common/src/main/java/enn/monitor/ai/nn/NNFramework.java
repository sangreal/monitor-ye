package enn.monitor.ai.nn;

import java.util.List;

import enn.monitor.ai.nn.NNObject.NNLayer;
import enn.monitor.ai.nn.NNObject.NNRelation;
import enn.monitor.ai.nn.activation.NNActivationEnum;
import enn.monitor.ai.nn.activation.NNActivationFactory;

public class NNFramework {
	
	public static List<Double> compute(NNObject nnObject, List<Double> inputList, List<Double> targetOutputList, 
			NNActivationEnum nnAcitvationEnum, double a, double multiple) throws Exception {
		int i, j, k;
		double sum = 0.0;
		
		NNLayer preNNData = null;
		NNLayer curNNData = null;
		
		NNRelation nnRelation = null;
		
		if (nnObject == null) {
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
			NNActivationFactory.backPropagate(nnAcitvationEnum, nnObject, targetOutputList, nnObject.getOutputList());
		}
		
		return nnObject.getOutputList();
	}
	
}
