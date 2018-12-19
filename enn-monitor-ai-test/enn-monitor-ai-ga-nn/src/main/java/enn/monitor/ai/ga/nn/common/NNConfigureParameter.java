package enn.monitor.ai.ga.nn.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import enn.monitor.ai.nn.activation.NNActivationEnum;

public class NNConfigureParameter {
	// 基因层面
	private static AtomicInteger idCount = new AtomicInteger(2);
	private List<NNItem> nnLayer = new ArrayList<NNItem>();
	
	// 激活函数
	private NNActivationEnum nnActivationEnum = null;
	private double a = 1.0;
	private double multiple = 1.0;
	
	// 调整
	private double wOff = 0.1;
	
	public NNConfigureParameter() {
		nnLayer.add(new NNItem(0, "输入层", 1));
		nnLayer.add(new NNItem(1, "输出层", 2));
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
			
			if (item.id == 0 || item.id == 1) {
				continue;
			}
			
			if (item.id == id) {
				it.remove();
				break;
			}
		}
	}
	
	public double getwOff() {
		return wOff;
	}

	public void setwOff(double wOff) {
		this.wOff = wOff;
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

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getMultiple() {
		return multiple;
	}

	public void setMultiple(double multiple) {
		this.multiple = multiple;
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
