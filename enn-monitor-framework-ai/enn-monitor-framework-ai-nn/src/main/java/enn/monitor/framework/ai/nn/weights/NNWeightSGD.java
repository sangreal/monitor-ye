package enn.monitor.framework.ai.nn.weights;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.framework.ai.nn.NNObject;
import enn.monitor.framework.ai.nn.NNObject.NNRelation;

public class NNWeightSGD implements NNWeightInterface {
	
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void flushWeight(NNObject nnObject) throws Exception {
		int i;
		
		NNRelation nnRelation = null;
		
		for (i = 0; i < nnObject.nnRelationSize(); ++i) {
			nnRelation = nnObject.getNNRelation(i);
			if (nnRelation == null) {
				logger.info("the nnObject is bad, the nnRelation is null");
				return;
			}
			
			flushWeight(nnObject, nnRelation);
		}
	}
	
	private void flushWeight(NNObject nnObject, NNRelation nnRelation) throws Exception {
		int i, j;
		
		for (i = 0; i < nnRelation.getWeightListList().size(); ++i) {
			for (j = 0; j < nnRelation.getWeightListList().get(0).size(); ++j) {
				nnRelation.getWeightListList().get(i).set(j, nnRelation.getWeightListList().get(i).get(j) - nnObject.getLearningRate() * nnRelation.getOffWeightListList().get(i).get(j));
				nnRelation.getPreOffWeightListList().get(i).set(j, nnRelation.getOffWeightListList().get(i).get(j));
			}
		}
	}

}
