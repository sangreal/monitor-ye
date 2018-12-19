package enn.monitor.ai.nn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NNObject {
	private static Random random = new Random();

	private List<NNLayer> numNeuronsList = new ArrayList<NNLayer>();
	private List<NNRelation> nnRelationList = new ArrayList<NNRelation>();
	
	private double momentum = 0.9;
	private double learningRate = 0.5;
	private double error = 0.0;
	private double maxNoise = 0.0;
	
	public NNObject cloneNNObject() {
		int i;
		NNObject nnOjbect = new NNObject();

		for (i = 0; i < numNeuronsList.size(); ++i) {
			nnOjbect.addNNData(numNeuronsList.get(i).cloneNNData());
		}
		for (i = 0; i < nnRelationList.size(); ++i) {
			nnOjbect.addNNRelation(nnRelationList.get(i).cloneNNRelation());
		}
		
		return nnOjbect;
	}
	
	public void clear() {
		numNeuronsList.clear();
		nnRelationList.clear();
	}
	
	public void initInput(List<Double> inputList) {
		int i;
		
		for (i = 0; i < inputList.size(); ++i) {
			numNeuronsList.get(0).setyList(new ArrayList<Double>(inputList));
		}
	}
	
	public List<Double> getOutputList() {
		List<Double> outputList = new ArrayList<Double>();
		
		outputList.addAll(numNeuronsList.get(numNeuronsList.size() - 1).getyList());
		
		return outputList;
	}
	
	public void addNeuronsLayer(int num, double bias) {
		NNRelation nnRelation = null;
		
		numNeuronsList.add(new NNLayer(num, bias));
		
		if (numNeuronsList.size() < 2) {
			return;
		}
		
		nnRelation = new NNRelation(
				numNeuronsList.get(numNeuronsList.size() - 2).getNumNeurons(), 
				numNeuronsList.get(numNeuronsList.size() - 1).getNumNeurons());
		
		nnRelationList.add(nnRelation);
	}
	
	public int getNumNerons(int index) {
		if (numNeuronsList.size() < index) {
			return 0;
		}
		
		return numNeuronsList.get(index).getNumNeurons();
	}
	
	public void setNNRelation(int index, NNRelation nnRelation) {
		nnRelationList.set(index, nnRelation);
	}

	public void addNNRelation(NNRelation nnRelation) {
		nnRelationList.add(nnRelation);
	}
	
	public NNRelation getNNRelation(int index) {
		if (nnRelationList.size() < index) {
			return null;
		}
		
		return nnRelationList.get(index);
	}
	
	public int nnRelationSize() {
		return nnRelationList.size();
	}
	
	public void addNNData(NNLayer nnData) {
		numNeuronsList.add(nnData);
	}
	
	public NNLayer getNNData(int index) {
		if (numNeuronsList.size() < index) {
			return null;
		}
		
		return numNeuronsList.get(index);
	}
	
	public int getNumLayers() {
		return nnRelationList.size();
	}
	
	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	
	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}
	
	public void clearError() {
		error = 0.0;
	}
	
	public void addError(double var) {
		error += var;
	}
	
	public void setMomentum(double momentum) {
		this.momentum = momentum;
	}
	
	public double getMaxNoise() {
		return maxNoise;
	}

	public void setMaxNoise(double maxNoise) {
		this.maxNoise = maxNoise;
	}

	public void flushWeight() {
		for (NNRelation nnRelation : nnRelationList) {
			nnRelation.flushWeight(momentum);
		}
	}

	public static class NNLayer {
		private int numNeurons;
		
		private double bias = 0.0;
		
		private List<Double> xList = new ArrayList<Double>();
		private List<Double> yList = new ArrayList<Double>();
		
		private NNLayer() {}
		
		public NNLayer(int numNeurons, double bias) {
			this.numNeurons = numNeurons;
			this.bias = bias;
		}
		
		public int getNumNeurons() {
			return numNeurons;
		}
		
		public void setNumNeurons(int numNeurons) {
			this.numNeurons = numNeurons;
		}
		
		public List<Double> getxList() {
			return xList;
		}

		public void setxList(List<Double> xList) {
			this.xList = xList;
		}
		
		public void addX(double x) {
			xList.add(x);
		}

		public List<Double> getyList() {
			return yList;
		}

		public void setyList(List<Double> yList) {
			this.yList = yList;
		}
		
		public void addY(double y) {
			yList.add(y);
		}
		
		public double getBias() {
			return bias;
		}

		public void setBias(double bias) {
			this.bias = bias;
		}

		public NNLayer cloneNNData() {
			NNLayer nnData = new NNLayer();
			
			nnData.setNumNeurons(numNeurons);
			nnData.setxList(new ArrayList<Double>(xList));
			nnData.setyList(new ArrayList<Double>(yList));
			
			return nnData;
		}
		
	}
	
	public static class NNRelation {

		private List<List<Double>> weightListList = new ArrayList<List<Double>>();
		private List<List<Double>> offWeightListList = new ArrayList<List<Double>>();
		private List<List<Double>> preOffWeightListList = new ArrayList<List<Double>>();
		private List<Double> errorList = new ArrayList<Double>();
		
		private NNRelation() {
			
		}
		
		public NNRelation(int input, int output) {
			int i, j;
			
			List<Double> weightList = null;
			List<Double> offErrorList = null;
			List<Double> preOffWeightList = null;

			for (i = 0; i < output; ++i) {
				weightList = new ArrayList<Double>();
				offErrorList = new ArrayList<Double>();
				preOffWeightList = new ArrayList<Double>();
				for (j = 0; j <= input; ++j) {
					weightList.add(random.nextDouble() * 2 - 1);
					offErrorList.add(0.0);
					preOffWeightList.add(0.0);
				}
				weightListList.add(weightList);
				errorList.add(0.0);
				offWeightListList.add(offErrorList);
				preOffWeightListList.add(preOffWeightList);
			}
		}
		
		public int getInput() {
			return weightListList.get(0).size();
		}

		public int getOutput() {
			return weightListList.size();
		}
		
		public void addWeightList(List<Double> weightList) {
			weightListList.add(weightList);
		}
		
		public List<List<Double>> getWeightListList() {
			return weightListList;
		}
		
		public List<Double> getErrorList() {
			return errorList;
		}
		
		public List<List<Double>> getOffWeightListList() {
			return offWeightListList;
		}
		
		public List<List<Double>> getPreOffWeightListList() {
			return preOffWeightListList;
		}
		
		public void flushWeight(double momentum) {
			int i, j;
			
			
			for (i = 0; i < weightListList.size(); ++i) {
				for (j = 0; j < weightListList.get(0).size(); ++j) {
					weightListList.get(i).set(j, weightListList.get(i).get(j) - offWeightListList.get(i).get(j) - momentum * preOffWeightListList.get(i).get(j));
					preOffWeightListList.get(i).set(j, offWeightListList.get(i).get(j));
				}
			}
		}
		
		public NNRelation cloneNNRelation() {
			int i;
			
			NNRelation nnRelation = new NNRelation();

			for (i = 0; i < weightListList.size(); ++i) {
				nnRelation.addWeightList(new ArrayList<Double>(weightListList.get(i)));
			}
			
			return nnRelation;
		}
		
	}

}
