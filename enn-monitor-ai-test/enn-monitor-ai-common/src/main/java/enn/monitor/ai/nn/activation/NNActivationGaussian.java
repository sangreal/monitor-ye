package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationGaussian implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		return Math.exp(-x*x);
	}
	
	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
