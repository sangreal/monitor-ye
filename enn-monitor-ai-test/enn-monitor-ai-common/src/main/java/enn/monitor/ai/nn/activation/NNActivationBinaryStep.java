package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public class NNActivationBinaryStep implements NNActivationInterface {

	@Override
	public double activation(double x, double a, double multiple) {
		if (x < 0) {
			return 0;
		}
		
		return 1;
	}
	
	@Override
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
