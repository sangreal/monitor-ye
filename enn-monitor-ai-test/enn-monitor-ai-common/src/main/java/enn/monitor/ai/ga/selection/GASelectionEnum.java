package enn.monitor.ai.ga.selection;

public enum GASelectionEnum {
	RouletteWheel("赌轮选择"), SUS("随机遍及取样"), Tournament("锦标赛选择");
	
	private String value = null;
	
	GASelectionEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
