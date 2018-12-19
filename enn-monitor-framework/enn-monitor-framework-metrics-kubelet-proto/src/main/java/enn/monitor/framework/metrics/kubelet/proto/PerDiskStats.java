package enn.monitor.framework.metrics.kubelet.proto;

import java.util.Map;
import java.util.Map.Entry;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class PerDiskStats implements IContainer {
	
	/*
	 Major uint64            `json:"major"`
	 Minor uint64            `json:"minor"`
	 Stats map[string]uint64 `json:"stats"`
	 */
	
	private long major;
	private long minor;
	private Map<String, Long> stats = null;

	public long getMajor() {
		return major;
	}

	public void setMajor(long major) {
		this.major = major;
	}

	public long getMinor() {
		return minor;
	}

	public void setMinor(long minor) {
		this.minor = minor;
	}

	public Map<String, Long> getStats() {
		return stats;
	}

	public void setStats(Map<String, Long> stats) {
		this.stats = stats;
	}

	@Override
	public void log() {
		System.out.println("			PerDiskStats major:" + major);
		System.out.println("			PerDiskStats minor:" + minor);
		if (stats != null) {
			System.out.println("			PerDiskStats stats:");
			for (Entry<String, Long> entry : stats.entrySet()) {
				System.out.printf("			PerDiskStats stats:  Key: %s    Value: %d\n", entry.getKey(), entry.getValue());
			}
		}
	}

}
