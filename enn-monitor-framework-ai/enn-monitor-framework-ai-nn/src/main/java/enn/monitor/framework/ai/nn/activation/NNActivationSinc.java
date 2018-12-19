package enn.monitor.framework.ai.nn.activation;

import java.util.List;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNActivationSinc implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		if (Math.abs(x - 0) <= 0.0000001) {
			return 1;
		}
		
		return Math.sin(x) / x;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, NNWeightEnum nnWeightEnum, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
