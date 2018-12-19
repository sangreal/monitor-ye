package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class CpuStats implements IContainer {
	/*
	 Usage CpuUsage `json:"usage"`
	 CFS   CpuCFS   `json:"cfs"`
	 LoadAverage int32 `json:"load_average"`
	 */
	
	private CpuUsage usage = null;
	private int load_average;

	public CpuUsage getUsage() {
		return usage;
	}

	public void setUsage(CpuUsage usage) {
		this.usage = usage;
	}

	public int getLoad_average() {
		return load_average;
	}

	public void setLoad_average(int load_average) {
		this.load_average = load_average;
	}

	@Override
	public void log() {
		if (usage != null) {
			usage.log();
		}
		System.out.println("		CpuStats load_average:" + load_average);
	}

}
