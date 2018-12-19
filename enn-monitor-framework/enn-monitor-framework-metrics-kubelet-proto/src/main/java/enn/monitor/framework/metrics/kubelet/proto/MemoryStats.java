package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class MemoryStats implements IContainer {
	/*
	Usage uint64 `json:"usage"`
	Cache uint64 `json:"cache"`
	RSS uint64 `json:"rss"`
	Swap uint64 `json:"swap"`
	WorkingSet uint64 `json:"working_set"`
	Failcnt uint64 `json:"failcnt"`
	ContainerData    MemoryStatsMemoryData `json:"container_data,omitempty"`
	HierarchicalData MemoryStatsMemoryData `json:"hierarchical_data,omitempty"`
	 */
	
	private long usage;
	private long cache;
	private long rss;
	private long swap;
	private long working_set;
	private long failcnt;

	private MemoryStatsMemoryData container_data = null;
	private MemoryStatsMemoryData hierarchical_data = null;
	
	public long getUsage() {
		return usage;
	}

	public void setUsage(long usage) {
		this.usage = usage;
	}

	public long getCache() {
		return cache;
	}

	public void setCache(long cache) {
		this.cache = cache;
	}

	public long getRss() {
		return rss;
	}

	public void setRss(long rss) {
		this.rss = rss;
	}

	public long getSwap() {
		return swap;
	}

	public void setSwap(long swap) {
		this.swap = swap;
	}

	public long getWorking_set() {
		return working_set;
	}

	public void setWorking_set(long working_set) {
		this.working_set = working_set;
	}

	public long getFailcnt() {
		return failcnt;
	}

	public void setFailcnt(long failcnt) {
		this.failcnt = failcnt;
	}

	public MemoryStatsMemoryData getContainer_data() {
		return container_data;
	}

	public void setContainer_data(MemoryStatsMemoryData container_data) {
		this.container_data = container_data;
	}

	public MemoryStatsMemoryData getHierarchical_data() {
		return hierarchical_data;
	}

	public void setHierarchical_data(MemoryStatsMemoryData hierarchical_data) {
		this.hierarchical_data = hierarchical_data;
	}

	@Override
	public void log() {
		System.out.println("		MemoryStats usage: " + usage);
		System.out.println("		MemoryStats cache: " + cache);
		System.out.println("		MemoryStats rss: " + rss);
		System.out.println("		MemoryStats swap: " + swap);
		System.out.println("		MemoryStats working_set: " + working_set);
		System.out.println("		MemoryStats failcnt: " + failcnt);
		if (container_data != null) {
			container_data.log();
		}
		if (hierarchical_data != null) {
			hierarchical_data.log();
		}
	}

}
