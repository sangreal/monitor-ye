package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class ContainerStats implements IContainer {
	
	/*
	Timestamp time.Time    `json:"timestamp"`
	Cpu       CpuStats     `json:"cpu,omitempty"`
	DiskIo    DiskIoStats  `json:"diskio,omitempty"`
	Memory    MemoryStats  `json:"memory,omitempty"`
	Network   NetworkStats `json:"network,omitempty"`

	// Filesystem statistics
	Filesystem []FsStats `json:"filesystem,omitempty"`

	// Task load stats
	TaskStats LoadStats `json:"task_stats,omitempty"`

	// Custom metrics from all collectors
	CustomMetrics map[string][]MetricVal `json:"custom_metrics,omitempty"`
	 */
	
	private String timestamp = null;
	private String clustername = null;
	private CpuStats cpu = null;
//	private GpuStats gpu = null;
	private DiskIoStats diskio = null;
	private DiskQuota diskquota = null;
	private MemoryStats memory = null;
	private NetworkStats network = null;
	
	private FsStats[] filesystem = null;
	
	private LoadStats task_stats = null;
	private GpuStats[] accelerators = null;

	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getClustername() {
		return clustername;
	}

	public void setClustername(String clustername) {
		this.clustername = clustername;
	}

	public CpuStats getCpu() {
		return cpu;
	}

	public void setCpu(CpuStats cpu) {
		this.cpu = cpu;
	}

//	public GpuStats getGpu() {
//		return gpu;
//	}

	public DiskIoStats getDiskio() {
		return diskio;
	}

	public void setDiskio(DiskIoStats diskio) {
		this.diskio = diskio;
	}
	
	public DiskQuota getDiskquota() {
		return diskquota;
	}

	public void setDiskquota(DiskQuota diskquota) {
		this.diskquota = diskquota;
	}

	public MemoryStats getMemory() {
		return memory;
	}

	public void setMemory(MemoryStats memory) {
		this.memory = memory;
	}

	public NetworkStats getNetwork() {
		return network;
	}

	public void setNetwork(NetworkStats network) {
		this.network = network;
	}

	public FsStats[] getFilesystem() {
		return filesystem;
	}

	public void setFilesystem(FsStats[] filesystem) {
		this.filesystem = filesystem;
	}

	public LoadStats getTask_stats() {
		return task_stats;
	}

	public void setTask_stats(LoadStats task_stats) {
		this.task_stats = task_stats;
	}

	public GpuStats[] getAccelerators() {
		return accelerators;
	}

	public void setAccelerators(GpuStats[] accelerators) {
		this.accelerators = accelerators;
	}

	@Override
	public void log() {
		System.out.printf("ContainerStats timestamp: %s\n", timestamp);
		if (cpu != null) {
			cpu.log();
		}
		if (diskio != null) {
			diskio.log();
		}
		if (memory != null) {
			memory.log();
		}
		if (network != null) {
			network.log();
		}
		if (filesystem != null) {
			for (FsStats item : filesystem) {
				item.log();
			}
		}
		if (task_stats != null) {
			task_stats.log();
		}
	}

}
