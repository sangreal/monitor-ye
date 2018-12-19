package enn.monitor.ai.nn.activation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enn.monitor.ai.nn.NNObject;

public class NNActivationFactory {

	public static Map<NNActivationEnum, NNActivationInterface> nnActivationMap = new HashMap<NNActivationEnum, NNActivationInterface>();
	static {
		nnActivationMap.put(NNActivationEnum.ArcTan, new NNActivationArcTan());
		nnActivationMap.put(NNActivationEnum.BentIdentity, new NNActivationBentIdentity());
		nnActivationMap.put(NNActivationEnum.BinaryStep, new NNActivationBinaryStep());
		nnActivationMap.put(NNActivationEnum.ExponentialLinearUnit, new NNActivationExponentialLinearUnit());
		nnActivationMap.put(NNActivationEnum.Gaussian, new NNActivationGaussian());
		nnActivationMap.put(NNActivationEnum.Identity, new NNActivationIdentity());
		nnActivationMap.put(NNActivationEnum.Sigmoid, new NNActivationSigmoid());
		nnActivationMap.put(NNActivationEnum.ParametericRectifiedLinearUnit, new NNActivationParametericRectifiedLinearUnit());
		nnActivationMap.put(NNActivationEnum.RectifiedLinearUnit, new NNActivationRectifiedLinearUnit());
		nnActivationMap.put(NNActivationEnum.Sinc, new NNActivationSinc());
		nnActivationMap.put(NNActivationEnum.Sinusoid, new NNActivationSinusoid());
		nnActivationMap.put(NNActivationEnum.SoftExponential, new NNActivationSoftExponential());
		nnActivationMap.put(NNActivationEnum.SoftPlus, new NNActivationSoftPlus());
		nnActivationMap.put(NNActivationEnum.Softsign, new NNActivationSoftsign());
		nnActivationMap.put(NNActivationEnum.TanH, new NNActivationTanH());
	}
	
	public static double compute(NNActivationEnum nnActivationEnum, double x, double a, double multiple) throws Exception {
		NNActivationInterface nnActivationInterface = null;
		
		nnActivationInterface = nnActivationMap.get(nnActivationEnum);
		if (nnActivationInterface == null) {
			throw new Exception("nnActivationInterface is null");
		}
		
		return nnActivationInterface.activation(x, a, multiple);
	}
	
	public static void backPropagate(NNActivationEnum nnActivationEnum, NNObject nnObject, 
			List<Double> targetOutputList, List<Double> outputList) throws Exception {
		NNActivationInterface nnActivationInterface = null;
		
		nnActivationInterface = nnActivationMap.get(nnActivationEnum);
		nnActivationInterface.backPropagate(nnObject, targetOutputList, outputList);
	}
}
