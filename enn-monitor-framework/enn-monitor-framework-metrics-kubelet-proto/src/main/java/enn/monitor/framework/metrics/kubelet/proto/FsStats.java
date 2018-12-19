package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class FsStats implements IContainer {
	/*
	Device string `json:"device,omitempty"`
	Type string `json:"type"`
	Limit uint64 `json:"capacity"`
	Usage uint64 `json:"usage"`
	BaseUsage uint64 `json:"base_usage"`
	Available uint64 `json:"available"`
	HasInodes bool `json:"has_inodes"`
	Inodes uint64 `json:"inodes"`
	InodesFree uint64 `json:"inodes_free"`
	ReadsCompleted uint64 `json:"reads_completed"`
	ReadsMerged uint64 `json:"reads_merged"`
	SectorsRead uint64 `json:"sectors_read"`
	ReadTime uint64 `json:"read_time"`
	WritesCompleted uint64 `json:"writes_completed"`
	WritesMerged uint64 `json:"writes_merged"`
	SectorsWritten uint64 `json:"sectors_written"`
	WriteTime uint64 `json:"write_time"`
	IoInProgress uint64 `json:"io_in_progress"`
	IoTime uint64 `json:"io_time"`
	WeightedIoTime uint64 `json:"weighted_io_time"`
	 */
	
	private String device;
	private String type;
	private long capacity;
	private long usage;
	private long base_usage;
	private long available;
	private boolean has_inodes;
	private long inodes;
	private long inodes_free;
	private long reads_completed;
	private long reads_merged;
	private long sectors_read;
	private long read_time;
	private long writes_completed;
	private long writes_merged;
	private long sectors_written;
	private long write_time;
	private long io_in_progress;
	private long io_time;
	private long weighted_io_time;
	
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getUsage() {
		return usage;
	}

	public void setUsage(long usage) {
		this.usage = usage;
	}

	public long getBase_usage() {
		return base_usage;
	}

	public void setBase_usage(long base_usage) {
		this.base_usage = base_usage;
	}

	public long getAvailable() {
		return available;
	}

	public void setAvailable(long available) {
		this.available = available;
	}

	public boolean isHas_inodes() {
		return has_inodes;
	}

	public void setHas_inodes(boolean has_inodes) {
		this.has_inodes = has_inodes;
	}

	public long getInodes() {
		return inodes;
	}

	public void setInodes(long inodes) {
		this.inodes = inodes;
	}

	public long getInodes_free() {
		return inodes_free;
	}

	public void setInodes_free(long inodes_free) {
		this.inodes_free = inodes_free;
	}

	public long getReads_completed() {
		return reads_completed;
	}

	public void setReads_completed(long reads_completed) {
		this.reads_completed = reads_completed;
	}

	public long getReads_merged() {
		return reads_merged;
	}

	public void setReads_merged(long reads_merged) {
		this.reads_merged = reads_merged;
	}

	public long getSectors_read() {
		return sectors_read;
	}

	public void setSectors_read(long sectors_read) {
		this.sectors_read = sectors_read;
	}

	public long getRead_time() {
		return read_time;
	}

	public void setRead_time(long read_time) {
		this.read_time = read_time;
	}

	public long getWrites_completed() {
		return writes_completed;
	}

	public void setWrites_completed(long writes_completed) {
		this.writes_completed = writes_completed;
	}

	public long getWrites_merged() {
		return writes_merged;
	}

	public void setWrites_merged(long writes_merged) {
		this.writes_merged = writes_merged;
	}

	public long getSectors_written() {
		return sectors_written;
	}

	public void setSectors_written(long sectors_written) {
		this.sectors_written = sectors_written;
	}

	public long getWrite_time() {
		return write_time;
	}

	public void setWrite_time(long write_time) {
		this.write_time = write_time;
	}

	public long getIo_in_progress() {
		return io_in_progress;
	}

	public void setIo_in_progress(long io_in_progress) {
		this.io_in_progress = io_in_progress;
	}

	public long getIo_time() {
		return io_time;
	}

	public void setIo_time(long io_time) {
		this.io_time = io_time;
	}

	public long getWeighted_io_time() {
		return weighted_io_time;
	}

	public void setWeighted_io_time(long weighted_io_time) {
		this.weighted_io_time = weighted_io_time;
	}

	@Override
	public void log() {
		if (device != null) {
			System.out.println("		FsStats device: " + device);
		}
		if (type != null) {
			System.out.println("		FsStats type: " + type);
		}
		System.out.println("		FsStats capacity: " + capacity);
		System.out.println("		FsStats usage: " + usage);
		System.out.println("		FsStats base_usage: " + base_usage);
		System.out.println("		FsStats available: " + available);
		System.out.println("		FsStats has_inodes: " + has_inodes);
		System.out.println("		FsStats inodes: " + inodes);
		System.out.println("		FsStats inodes_free: " + inodes_free);
		System.out.println("		FsStats reads_completed: " + reads_completed);
		System.out.println("		FsStats reads_merged: " + reads_merged);
		System.out.println("		FsStats sectors_read: " + sectors_read);
		System.out.println("		FsStats read_time: " + read_time);
		System.out.println("		FsStats writes_completed: " + writes_completed);
		System.out.println("		FsStats writes_merged: " + writes_merged);
		System.out.println("		FsStats sectors_written: " + sectors_written);
		System.out.println("		FsStats write_time: " + write_time);
		System.out.println("		FsStats io_in_progress: " + io_in_progress);
		System.out.println("		FsStats io_time: " + io_time);
		System.out.println("		FsStats weighted_io_time: " + weighted_io_time);
	}

}
