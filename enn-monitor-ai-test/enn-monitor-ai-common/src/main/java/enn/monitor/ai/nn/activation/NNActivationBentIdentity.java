package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationBentIdentity implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		return (Math.sqrt(x * x + 1) - 1) / 2 + x;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
