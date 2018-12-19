package enn.monitor.log.ai.common;

import java.util.concurrent.BlockingQueue;

import enn.monitor.log.ai.core.EnnMonitorLogAiCoreEvent;

public abstract class CommonFSMInterface {
	
	private String moduleName = null;
	
	protected BlockingQueue<CommonEventInterface> eventQueue = null;
	
	public CommonFSMInterface(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public void addEvent(Object data) throws Exception {
		eventQueue.add(new EnnMonitorLogAiCoreEvent(this.getModuleName(), null, data));
	}
	
	abstract public void runEvent(Object data) throws Exception;
}
