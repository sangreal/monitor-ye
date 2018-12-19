package enn.monitor.log.ai.train.nn.core;

import enn.monitor.log.ai.common.CommonEventInterface;

public class TrainNNEvent extends CommonEventInterface {
	
	private TrainNNEventEnum eventEnum = null;
	private Object data = null;
	
	public TrainNNEvent(TrainNNEventEnum eventEnum, Object data) {
		this.eventEnum = eventEnum;
		this.data = data;
	}
	
	public TrainNNEvent(TrainNNEventEnum eventEnum) {
		this.eventEnum = eventEnum;
	}
	
	public TrainNNEventEnum getEventEnum() {
		return eventEnum;
	}
	
	public void setEventEnum(TrainNNEventEnum eventEnum) {
		this.eventEnum = eventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
