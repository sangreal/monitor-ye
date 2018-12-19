package enn.monitor.ai.nn.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import enn.monitor.framework.ai.nn.activation.NNActivationEnum;

public class NNConfigureParameter {
	// 基因层面
	private static AtomicInteger idCount = new AtomicInteger(0);
	private List<NNItem> nnLayer = new ArrayList<NNItem>();
	
	// 激活函数
	private NNActivationEnum nnActivationEnum = null;
	
	// 调整
	private double bias = 0.1;
	
	// 学习率
	private double learningRate = 0.0;
	
	// 误差值
	private double deviation = 0.0;
	
	// 动量
	private double momentum = 0.0;
	
	// 噪声
	private double maxNoise = 0.0;
	
	public NNConfigureParameter() {
	}
	
	public void updateInputLayer(int number) {
		nnLayer.get(0).numberOfNeuron = number;
	}
	
	public void updateOuputLayer(int number) {
		nnLayer.get(1).numberOfNeuron = number;
	}
	
	public void addHiddenLayer(int number) {
		nnLayer.add(new NNItem(idCount.getAndIncrement(), "隐藏层", number));
	}
	
	public void deleteHiddenLayer(int id) {
		NNItem item = null;
		
		Iterator<NNItem> it = nnLayer.iterator();
		
		while (it.hasNext()) {
			item = it.next();
			
			if (item.id == id) {
				it.remove();
				break;
			}
		}
	}
	
	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}
	
	public double getMomentum() {
		return momentum;
	}

	public void setMomentum(double momentum) {
		this.momentum = momentum;
	}

	public List<NNItem> getNNLayer() {
		return nnLayer;
	}
	
	public NNActivationEnum getNnActivationEnum() {
		return nnActivationEnum;
	}

	public void setNnActivationEnum(NNActivationEnum nnActivationEnum) {
		this.nnActivationEnum = nnActivationEnum;
	}
	
	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}
	
	public double getMaxNoise() {
		return maxNoise;
	}

	public void setMaxNoise(double maxNoise) {
		this.maxNoise = maxNoise;
	}

	public static class NNItem {
		public int id;
		public String type;
		public int numberOfNeuron;
		
		public NNItem(int id, String type, int numberOfNeuron) {
			this.id = id;
			this.type = type;
			this.numberOfNeuron = numberOfNeuron;
		}
	}
}
