package enn.monitor.ai.ga.scaleratio;

public enum GAScaleRatioEnum {
	None("无"), Rank("排名变比"), Sigma("西格玛变比"), Boltzmann("波兹曼变比");
	
	private String value = null;
	
	GAScaleRatioEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
