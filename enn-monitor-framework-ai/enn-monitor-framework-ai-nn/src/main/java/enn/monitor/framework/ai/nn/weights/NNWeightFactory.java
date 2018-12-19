package enn.monitor.framework.ai.nn.weights;

import java.util.HashMap;
import java.util.Map;

import enn.monitor.framework.ai.nn.NNObject;

public class NNWeightFactory {
	
	public static Map<NNWeightEnum, NNWeightInterface> nnWeightMap = new HashMap<NNWeightEnum, NNWeightInterface>();
	static {
		nnWeightMap.put(NNWeightEnum.SGD, new NNWeightSGD());
		nnWeightMap.put(NNWeightEnum.Momentum, new NNWeightMomentum());
		nnWeightMap.put(NNWeightEnum.Adagrad, new NNWeightAdagrad());
	}

	public static void flushWeight(NNObject nnObject, NNWeightEnum nnWeightEnum) throws Exception {
		NNWeightInterface nnWeightInterface = null;
		
		nnWeightInterface = nnWeightMap.get(nnWeightEnum);
		if (nnWeightInterface == null) {
			throw new Exception("nnWeightInterface is null");
		}
		
		nnWeightInterface.flushWeight(nnObject);
	}
	
}
