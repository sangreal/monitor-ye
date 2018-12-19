package enn.monitor.log.ai.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import enn.monitor.log.ai.common.CommonEventInterface;
import enn.monitor.log.ai.common.CommonFSMInterface;

public class EnnMonitorLogAiCoreFSM extends CommonFSMInterface implements Runnable {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, CommonFSMInterface> moduleFSMMap = new HashMap<String, CommonFSMInterface>();

	public EnnMonitorLogAiCoreFSM(BlockingQueue<CommonEventInterface> eventQueue) {
		super("TrainDataCoreFSM");
		this.eventQueue = eventQueue;
	}

	@Override
	public void run() {
		EnnMonitorLogAiCoreEvent coreEvent = null;
		
		while (true) {
			try {
				coreEvent = (EnnMonitorLogAiCoreEvent) eventQueue.poll(500, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
			
			if (coreEvent == null) {
				continue;
			}
			
			try {
				moduleFSMMap.get(coreEvent.getModuleName()).runEvent(coreEvent.getData());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public void addCoreModuleInfo(String name, CommonFSMInterface module) {
		moduleFSMMap.put(name, module);
	}

	@Override
	public void runEvent(Object data) throws Exception {
		logger.error("call the function is failed");
	}

}
