package enn.monitor.ai.ga.nn.common;

import java.util.ArrayList;
import java.util.List;

import enn.monitor.ai.ga.common.SGenome;
import enn.monitor.ai.nn.NNObject;

public class CircleSGenome extends SGenome {
	
	private NNObject nnObject = new NNObject();
	private List<List<Double>> inputListList = new ArrayList<List<Double>>();
	
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
	
	public SGenome cloneSGenome() {
		int i;
		List<Double> inputList = null;
		List<List<Double>> inputListList = new ArrayList<List<Double>>();
		CircleSGenome entry = new CircleSGenome();
		
		entry.setdFitness(this.getdFitness());
		entry.setGenome(new ArrayList<Integer>(this.getGenome()));
		entry.setNnObject(nnObject.cloneNNObject());
		
		for (i = 0; i < this.inputListList.size(); ++i) {
			inputList = new ArrayList<Double>(this.inputListList.get(i));
			inputListList.add(inputList);
		}
		entry.setInputListList(inputListList);
		
		return entry;
	}

}
