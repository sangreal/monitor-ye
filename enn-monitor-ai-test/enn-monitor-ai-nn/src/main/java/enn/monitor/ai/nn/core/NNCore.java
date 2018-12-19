package enn.monitor.ai.nn.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.ai.nn.NNFramework;
import enn.monitor.ai.nn.NNObject;
import enn.monitor.ai.nn.activation.NNActivationEnum;
import enn.monitor.ai.nn.common.NNConfigureParameter;
import enn.monitor.ai.nn.common.NNResult;
import enn.monitor.ai.nn.panel.common.NNEvent;
import enn.monitor.ai.nn.panel.common.NNEventEnum;

public class NNCore implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static Random random = new Random();
	
	private BlockingQueue<NNEvent> eventQueue = new LinkedBlockingQueue<NNEvent>();
	
	private NNObject nnObject = null;
	private List<List<Double>> inputListList = null;
	private List<List<Double>> targetOutputList = null;
	
	private NNConfigureParameter nnConfigureParameter = null;
	
	private boolean start = true;
	
	public NNCore(BlockingQueue<NNEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}

	public void train() {
		start = true;
		new Thread(this).start();
	}
	
	public NNObject getNnObject() {
		return nnObject;
	}

	public void setNnObject(NNObject nnObject) {
		this.nnObject = nnObject;
	}

	public List<List<Double>> getInputListList() {
		return inputListList;
	}

	public void setInputListList(List<List<Double>> inputListList) {
		this.inputListList = inputListList;
	}

	public List<List<Double>> getTargetOutputList() {
		return targetOutputList;
	}

	public void setTargetOutputList(List<List<Double>> targetOutputList) {
		this.targetOutputList = targetOutputList;
	}
	
	public void setNNConfigureParameter(NNConfigureParameter nnConfigureParameter) {
		this.nnConfigureParameter = nnConfigureParameter;
	}
	
	public NNConfigureParameter getNNConfigureParameter() {
		return nnConfigureParameter;
	}
	
	public void stop() {
		start = false;
	}

	@Override
	public void run() {
		training();
		
		if (start == true) {
			eventQueue.add(new NNEvent(NNEventEnum.Active));
			start = false;
		}
	}
	
	private boolean training() {
		int i, j;
		int gereration = 0;
		NNResult nnResult = null;
		List<Double> inputList = null;
		
		if (nnObject != null && inputListList != null && targetOutputList != null) {
			try {
				while (start) {
					nnObject.clearError();
					for (i = 0; i < inputListList.size(); ++i) {
						inputList = new ArrayList<Double>(inputListList.get(i));
						// 添加噪声
						for (j = 0; j < inputList.size(); ++j) {
							inputList.set(j, inputList.get(j) + nnObject.getMaxNoise() * (random.nextDouble() - random.nextDouble()));
						}
						NNFramework.compute(nnObject, inputList, targetOutputList.get(i), NNActivationEnum.Sigmoid, 0, 0);
					}
					
					++gereration;
					nnResult = new NNResult();
					nnResult.setGeneration(gereration);
					nnResult.setError(nnObject.getError());
					eventQueue.add(new NNEvent(NNEventEnum.UpdateResult, nnResult));
					
					if (nnObject.getError() <= nnConfigureParameter.getDeviation()) {
						break;
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	/*public static void main(String[] args) throws Exception {
		NNCore nnCore = new NNCore(new LinkedBlockingQueue<NNEvent>());
		
		List<Double> inputList = null;
		List<List<Double>> inputListList = new ArrayList<List<Double>>();
		
		List<Double> targetOutputList = null;
		List<List<Double>> targetOutputListList = new ArrayList<List<Double>>();
		
		NNObject nnObject = new NNObject();
		nnObject.addNeuronsLayer(2, 1);
		nnObject.addNeuronsLayer(2, 1);
		nnObject.addNeuronsLayer(2, 1);
		nnObject.getNNRelation(0).getWeightListList().get(0).set(0, 0.15);
		nnObject.getNNRelation(0).getWeightListList().get(0).set(1, 0.20);
		nnObject.getNNRelation(0).getWeightListList().get(0).set(2, 0.35);
		nnObject.getNNRelation(0).getWeightListList().get(1).set(0, 0.25);
		nnObject.getNNRelation(0).getWeightListList().get(1).set(1, 0.30);
		nnObject.getNNRelation(0).getWeightListList().get(1).set(2, 0.35);
		nnObject.getNNRelation(1).getWeightListList().get(0).set(0, 0.40);
		nnObject.getNNRelation(1).getWeightListList().get(0).set(1, 0.45);
		nnObject.getNNRelation(1).getWeightListList().get(0).set(2, 0.60);
		nnObject.getNNRelation(1).getWeightListList().get(1).set(0, 0.50);
		nnObject.getNNRelation(1).getWeightListList().get(1).set(1, 0.55);
		nnObject.getNNRelation(1).getWeightListList().get(1).set(2, 0.60);
		nnCore.setNnObject(nnObject);
		
		inputList = new ArrayList<Double>();
		inputList.add(0.05);
		inputList.add(0.10);
		inputListList.add(inputList);
		nnCore.setInputListList(inputListList);
		
		targetOutputList = new ArrayList<Double>();
		targetOutputList.add(0.01);
		targetOutputList.add(0.99);
		targetOutputListList.add(targetOutputList);
		
		nnCore.setTargetOutputList(targetOutputListList);
		
		nnCore.training();
		
		Thread.sleep(10000);
		
		System.out.println("neth1: " + nnObject.getNNData(1).getxList().get(0));
		System.out.println("outh1: " + nnObject.getNNData(1).getyList().get(0));
		System.out.println("neth2: " + nnObject.getNNData(1).getxList().get(1));
		System.out.println("outh2: " + nnObject.getNNData(1).getyList().get(1));
		System.out.println("neto1: " + nnObject.getNNData(2).getxList().get(0));
		System.out.println("outo1: " + nnObject.getNNData(2).getyList().get(0));
		System.out.println("neto2: " + nnObject.getNNData(2).getxList().get(1));
		System.out.println("outo2: " + nnObject.getNNData(2).getyList().get(1));
		
		System.out.println("w5: " + nnObject.getNNRelation(1).getWeightListList().get(0).get(0));
		System.out.println("w6: " + nnObject.getNNRelation(1).getWeightListList().get(0).get(1));
		System.out.println("w7: " + nnObject.getNNRelation(1).getWeightListList().get(1).get(0));
		System.out.println("w8: " + nnObject.getNNRelation(1).getWeightListList().get(1).get(1));
		
		System.out.println("w1: " + nnObject.getNNRelation(0).getWeightListList().get(0).get(0));
		System.out.println("w2: " + nnObject.getNNRelation(0).getWeightListList().get(0).get(1));
		System.out.println("w3: " + nnObject.getNNRelation(0).getWeightListList().get(1).get(0));
		System.out.println("w4: " + nnObject.getNNRelation(0).getWeightListList().get(1).get(1));
	}*/
	
}
