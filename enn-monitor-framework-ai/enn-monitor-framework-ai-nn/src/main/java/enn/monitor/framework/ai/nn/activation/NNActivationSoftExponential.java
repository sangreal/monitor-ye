package enn.monitor.framework.ai.nn.activation;

import java.util.List;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.weights.NNWeightEnum;

public class NNActivationSoftExponential implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		if (a < 0) {
			return -(Math.log(1 - a * (x + a))) / a;
		}
		
		if (a == 0) {
			return x;
		}
		
		return (Math.exp(a * x) - 1) / a + a;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, NNWeightEnum nnWeightEnum, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
