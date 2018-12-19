package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class LoadStats implements IContainer {
	/*
	NrSleeping uint64 `json:"nr_sleeping"`
	NrRunning uint64 `json:"nr_running"`
	NrStopped uint64 `json:"nr_stopped"`
	NrUninterruptible uint64 `json:"nr_uninterruptible"`
	NrIoWait uint64 `json:"nr_io_wait"`
	 */
	
	private long nr_sleeping;
	private long nr_running;
	private long nr_stopped;
	private long nr_uninterruptible;
	private long nr_io_wait;

	public long getNr_sleeping() {
		return nr_sleeping;
	}

	public long getNr_running() {
		return nr_running;
	}

	public long getNr_stopped() {
		return nr_stopped;
	}

	public long getNr_uninterruptible() {
		return nr_uninterruptible;
	}

	public long getNr_io_wait() {
		return nr_io_wait;
	}

	@Override
	public void log() {
		System.out.println("	LoadStats nr_sleeping: " + nr_sleeping);
		System.out.println("	LoadStats nr_running: " + nr_running);
		System.out.println("	LoadStats nr_stopped: " + nr_stopped);
		System.out.println("	LoadStats nr_uninterruptible: " + nr_uninterruptible);
		System.out.println("	LoadStats nr_io_wait: " + nr_io_wait);
	}

}
