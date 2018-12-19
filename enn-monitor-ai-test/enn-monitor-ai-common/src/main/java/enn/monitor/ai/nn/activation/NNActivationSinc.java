package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationSinc implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		if (Math.abs(x - 0) <= 0.0000001) {
			return 1;
		}
		
		return Math.sin(x) / x;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
