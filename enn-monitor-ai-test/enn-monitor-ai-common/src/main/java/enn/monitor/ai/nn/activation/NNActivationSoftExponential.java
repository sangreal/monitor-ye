package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

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
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
