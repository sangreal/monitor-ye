package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;
import enn.monitor.ai.nn.NNObject.NNRelation;

public class NNActivationSigmoid implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		return 1 / (1 + Math.exp(-x));
	}

	public void backPropagate(NNObject nnObject, 
			List<Double> targetOutputList, List<Double> outputList) throws Exception {
		int i, j, k;
		double sum;
		
		NNRelation preNNRelation = null;
		NNRelation nnRelation = null;
		List<List<Double>> weightListList = null;
		
		if (targetOutputList.size() != outputList.size()) {
			throw new Exception("in backPropagate, the output and the targetOutput size is not equal");
		}
		
		for (i = 0; i < outputList.size(); ++i) {
			nnObject.addError((targetOutputList.get(i) - outputList.get(i)) * (targetOutputList.get(i) - outputList.get(i)));
		}
		
		for (k = nnObject.nnRelationSize() - 1; k >= 0; --k) {
			preNNRelation = nnRelation;
			nnRelation = nnObject.getNNRelation(k);
			
			if (k == nnObject.nnRelationSize() - 1) {   // 输出层
				weightListList = nnRelation.getWeightListList();
				for (i = 0; i < weightListList.size(); ++i) {   // last
					nnRelation.getErrorList().set(i, -(targetOutputList.get(i) - outputList.get(i)) * outputList.get(i) * (1 - outputList.get(i)));
					for (j = 0; j < weightListList.get(0).size() - 1; ++j) {   // front
						nnRelation.getOffWeightListList().get(i).set(
								j, nnObject.getLearningRate() * nnRelation.getErrorList().get(i) * nnObject.getNNData(k).getyList().get(j));
					}
					nnRelation.getOffWeightListList().get(i).set(
							j, nnObject.getLearningRate() * nnRelation.getErrorList().get(i) * nnObject.getNNData(k).getBias());
				}
			} else {    // 隐藏层
				weightListList = nnRelation.getWeightListList();
				for (i = 0; i < weightListList.size(); ++i) {   // last
					sum = 0.0;
					for (j = 0; j < preNNRelation.getOutput(); ++j) {
						sum = sum + preNNRelation.getErrorList().get(j) * preNNRelation.getWeightListList().get(j).get(i);
					}
					nnRelation.getErrorList().set(i, sum * nnObject.getNNData(k + 1).getyList().get(i) * (1 - nnObject.getNNData(k + 1).getyList().get(i)));
					for (j = 0; j < weightListList.get(0).size() - 1; ++j) {   // front
						nnRelation.getOffWeightListList().get(i).set(
								j, nnObject.getLearningRate() * nnRelation.getErrorList().get(i) * nnObject.getNNData(k).getyList().get(j));
					}
					nnRelation.getOffWeightListList().get(i).set(
							j, nnObject.getLearningRate() * nnRelation.getErrorList().get(i) * nnObject.getNNData(k).getBias());
				}
			}
		}
		
		nnObject.flushWeight();
	}
}
