package enn.monitor.ai.ga.panel.common;

public class TSPEvent {
	
	private TSPEventEnum tspEventEnum = null;
	private Object data = null;
	
	public TSPEventEnum getTspEventEnum() {
		return tspEventEnum;
	}
	
	public void setTspEventEnum(TSPEventEnum tspEventEnum) {
		this.tspEventEnum = tspEventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
