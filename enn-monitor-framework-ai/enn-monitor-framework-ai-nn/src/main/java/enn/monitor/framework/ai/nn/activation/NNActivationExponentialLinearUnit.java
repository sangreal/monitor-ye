package enn.monitor.framework.ai.nn.activation;

import java.util.List;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNActivationExponentialLinearUnit implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		if (x < 0) {
			return a * (Math.exp(x) - 1);
		}
		
		return x;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, NNWeightEnum nnWeightEnum, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
