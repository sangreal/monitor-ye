package enn.monitor.ai.ga.nn.panel.common;

public class NNEvent {
	
	private NNEventEnum tspEventEnum = null;
	private Object data = null;
	
	public NNEventEnum getTspEventEnum() {
		return tspEventEnum;
	}
	
	public void setTspEventEnum(NNEventEnum tspEventEnum) {
		this.tspEventEnum = tspEventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
