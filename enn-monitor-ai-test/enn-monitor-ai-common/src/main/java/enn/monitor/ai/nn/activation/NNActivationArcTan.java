package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationArcTan implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		//return 1 / Math.tan(x);
		return Math.atan(x);
	}

	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
