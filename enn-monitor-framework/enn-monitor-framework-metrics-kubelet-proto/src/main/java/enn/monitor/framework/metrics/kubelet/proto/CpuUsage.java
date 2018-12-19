package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class CpuUsage implements IContainer {
	
	/*
	Total uint64 `json:"total"`
	PerCpu []uint64 `json:"per_cpu_usage,omitempty"`
	User uint64 `json:"user"`
	System uint64 `json:"system"`
	 */
	
	private long total;
	private long[] per_cpu_usage;
	private long user;
	private long system;
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long[] getPer_cpu_usage() {
		return per_cpu_usage;
	}

	public void setPer_cpu_usage(long[] per_cpu_usage) {
		this.per_cpu_usage = per_cpu_usage;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getSystem() {
		return system;
	}

	public void setSystem(long system) {
		this.system = system;
	}

	@Override
	public void log() {
		System.out.println("			CpuUsage");
		System.out.println("			CpuUsage total: " + total);
		if (per_cpu_usage != null) {
			System.out.println("			CpuUsage per_cpu_usage: ");
			for (long cpu : per_cpu_usage) {
				System.out.println("			CpuUsage per_cpu_usage: " + cpu);
			}
		}
		System.out.println("			CpuUsage user: " + user);
		System.out.println("			CpuUsage system: " + system);
	}

}
