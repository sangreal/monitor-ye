package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class MemoryStatsMemoryData implements IContainer {
	/*
	 Pgfault    uint64 `json:"pgfault"`
	Pgmajfault uint64 `json:"pgmajfault"`
	 */
	
	private long pgfault;
	private long pgmajfault;

	@Override
	public void log() {
		System.out.println("			MemoryStatsMemoryData pgfault: " + pgfault);
		System.out.println("			MemoryStatsMemoryData pgmajfault: " + pgmajfault);
	}

}
