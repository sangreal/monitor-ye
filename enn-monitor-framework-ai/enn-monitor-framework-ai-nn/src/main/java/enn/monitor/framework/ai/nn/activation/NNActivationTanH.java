package enn.monitor.framework.ai.nn.activation;

import java.util.List;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNActivationTanH implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		return 2 / (1 + Math.exp(-2 * x)) - 1;
	}

	@Override
	public void backPropagate(NNObject nnObject, NNWeightEnum nnWeightEnum, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
