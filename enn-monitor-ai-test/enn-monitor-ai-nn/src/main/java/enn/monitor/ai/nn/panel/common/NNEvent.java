package enn.monitor.ai.nn.panel.common;

public class NNEvent {
	
	private NNEventEnum eventEnum = null;
	private Object data = null;
	
	public NNEvent(NNEventEnum eventEnum, Object data) {
		this.eventEnum = eventEnum;
		this.data = data;
	}
	
	public NNEvent(NNEventEnum eventEnum) {
		this.eventEnum = eventEnum;
	}
	
	public NNEventEnum getEventEnum() {
		return eventEnum;
	}
	
	public void setEventEnum(NNEventEnum eventEnum) {
		this.eventEnum = eventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
