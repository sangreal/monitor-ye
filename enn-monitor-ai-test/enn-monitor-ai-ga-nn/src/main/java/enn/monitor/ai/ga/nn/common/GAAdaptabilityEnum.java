package enn.monitor.ai.ga.nn.common;

public enum GAAdaptabilityEnum {
	LongestRoute("最长路"), Total("总长");
	
	private String value = null;
	
	GAAdaptabilityEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}

}
