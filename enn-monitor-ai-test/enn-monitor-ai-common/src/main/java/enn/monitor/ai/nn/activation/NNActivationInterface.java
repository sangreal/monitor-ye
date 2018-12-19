package enn.monitor.ai.nn.activation;

import java.util.List;

import enn.monitor.ai.nn.NNObject;

public interface NNActivationInterface {
	
	public double activation(double x, double a, double multiple);
	public void backPropagate(NNObject nnObject, List<Double> targetOutputList, List<Double> outputList) throws Exception;

}
