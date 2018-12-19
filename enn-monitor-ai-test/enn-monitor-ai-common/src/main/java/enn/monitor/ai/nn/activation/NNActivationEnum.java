package enn.monitor.ai.nn.activation;

public enum NNActivationEnum {
	ArcTan("ArcTan"),  BentIdentity("BentIdentity"), BinaryStep("BinaryStep"), ExponentialLinearUnit("ExponentialLinearUnit"), 
	Gaussian("Gaussian"), Identity("Identity"), 
	ParametericRectifiedLinearUnit("ParametericRectifiedLinearUnit"), RectifiedLinearUnit("RectifiedLinearUnit"), 
	Sigmoid("Sigmoid"), Sinc("Sinc"), Sinusoid("Sinusoid"), SoftExponential("SoftExponential"), 
	SoftPlus("SoftPlus"), Softsign("Softsign"), TanH("TanH");

	private String value = null;
	
	NNActivationEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
