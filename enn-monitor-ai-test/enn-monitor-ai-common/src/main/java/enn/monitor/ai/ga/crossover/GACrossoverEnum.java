package enn.monitor.ai.ga.crossover;

public enum GACrossoverEnum {
	PMX("部分映射杂交"), OBX("基于顺序的杂交"), PBX("基于位置的杂交");
	
	private String value = null;
	
	GACrossoverEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
