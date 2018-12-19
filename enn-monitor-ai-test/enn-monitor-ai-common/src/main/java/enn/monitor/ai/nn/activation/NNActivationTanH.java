package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationTanH implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		return 2 / (1 + Math.exp(-2 * x)) - 1;
	}

	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
