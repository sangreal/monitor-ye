package enn.monitor.ai.ga.mutation;

public enum GAMutationEnum {
	EM("两点变异"), SM("散播变异"), DM("移位变异"), IM("插入变异"), RM1("逆序变异1"), RM2("逆序变异2");
	
	private String value = null;
	
	GAMutationEnum(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
