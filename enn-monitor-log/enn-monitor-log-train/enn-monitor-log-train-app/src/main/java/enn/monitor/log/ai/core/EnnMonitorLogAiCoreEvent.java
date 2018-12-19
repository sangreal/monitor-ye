package enn.monitor.log.ai.core;

import enn.monitor.log.ai.common.CommonEventInterface;

public class EnnMonitorLogAiCoreEvent extends CommonEventInterface {
	
	private EnnMonitorLogAiCoreEnum eventEnum = null;
	private Object data = null;
	
	public EnnMonitorLogAiCoreEvent(String moduleName, EnnMonitorLogAiCoreEnum eventEnum, Object data) {
		this.eventEnum = eventEnum;
		this.data = data;
		setModuleName(moduleName);
	}
	
	public EnnMonitorLogAiCoreEvent(String moduleName, EnnMonitorLogAiCoreEnum eventEnum) {
		this.eventEnum = eventEnum;
		setModuleName(moduleName);
	}
	
	public EnnMonitorLogAiCoreEnum getEventEnum() {
		return eventEnum;
	}
	
	public void setEventEnum(EnnMonitorLogAiCoreEnum eventEnum) {
		this.eventEnum = eventEnum;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
