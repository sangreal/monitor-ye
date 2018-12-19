package enn.monitor.framework.ai.nn.weights;

public enum NNWeightEnum {
	
	SGD("SGD"),  Momentum("Momentum"), Nesterov("Nesterov"), Adagrad("Adagrad"), 
	Adadelta("Adadelta"), RMSprop("RMSprop"), Adam("Adam"), Adamax("Adamax"), 
	Nadam("Nadam");

	private String value = null;
	
	NNWeightEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}

}
