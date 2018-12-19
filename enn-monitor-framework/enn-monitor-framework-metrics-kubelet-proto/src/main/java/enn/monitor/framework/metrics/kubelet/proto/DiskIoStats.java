package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class DiskIoStats implements IContainer {
	/*
	IoServiceBytes []PerDiskStats `json:"io_service_bytes,omitempty"`
	IoServiced     []PerDiskStats `json:"io_serviced,omitempty"`
	IoQueued       []PerDiskStats `json:"io_queued,omitempty"`
	Sectors        []PerDiskStats `json:"sectors,omitempty"`
	IoServiceTime  []PerDiskStats `json:"io_service_time,omitempty"`
	IoWaitTime     []PerDiskStats `json:"io_wait_time,omitempty"`
	IoMerged       []PerDiskStats `json:"io_merged,omitempty"`
	IoTime         []PerDiskStats `json:"io_time,omitempty"`
	 */
	
	private PerDiskStats[] io_service_bytes = null;
	private PerDiskStats[] io_serviced = null;
	private PerDiskStats[] io_queued = null;
	private PerDiskStats[] sectors = null;
	private PerDiskStats[] io_service_time = null;
	private PerDiskStats[] io_wait_time = null;
	private PerDiskStats[] io_merged = null;
	private PerDiskStats[] io_time = null;

	public PerDiskStats[] getIo_service_bytes() {
		return io_service_bytes;
	}

	public void setIo_service_bytes(PerDiskStats[] io_service_bytes) {
		this.io_service_bytes = io_service_bytes;
	}

	public PerDiskStats[] getIo_serviced() {
		return io_serviced;
	}

	public void setIo_serviced(PerDiskStats[] io_serviced) {
		this.io_serviced = io_serviced;
	}

	public PerDiskStats[] getIo_queued() {
		return io_queued;
	}

	public void setIo_queued(PerDiskStats[] io_queued) {
		this.io_queued = io_queued;
	}

	public PerDiskStats[] getSectors() {
		return sectors;
	}

	public void setSectors(PerDiskStats[] sectors) {
		this.sectors = sectors;
	}

	public PerDiskStats[] getIo_service_time() {
		return io_service_time;
	}

	public void setIo_service_time(PerDiskStats[] io_service_time) {
		this.io_service_time = io_service_time;
	}

	public PerDiskStats[] getIo_wait_time() {
		return io_wait_time;
	}

	public void setIo_wait_time(PerDiskStats[] io_wait_time) {
		this.io_wait_time = io_wait_time;
	}

	public PerDiskStats[] getIo_merged() {
		return io_merged;
	}

	public void setIo_merged(PerDiskStats[] io_merged) {
		this.io_merged = io_merged;
	}

	public PerDiskStats[] getIo_time() {
		return io_time;
	}

	public void setIo_time(PerDiskStats[] io_time) {
		this.io_time = io_time;
	}

	@Override
	public void log() {
		if (io_service_bytes != null) {
			System.out.println("		io_service_bytes");
			for (PerDiskStats item : io_service_bytes) {
				item.log();
			}
		}
		
		if (io_serviced != null) {
			System.out.println("		io_serviced");
			for (PerDiskStats item : io_serviced) {
				item.log();
			}
		}
		
		if (io_queued != null) {
			System.out.println("		io_queued");
			for (PerDiskStats item : io_queued) {
				item.log();
			}
		}
		
		if (sectors != null) {
			System.out.println("		sectors");
			for (PerDiskStats item : sectors) {
				item.log();
			}
		}
		
		if (io_service_time != null) {
			System.out.println("		io_service_time");
			for (PerDiskStats item : io_service_time) {
				item.log();
			}
		}
		
		if (io_wait_time != null) {
			System.out.println("		io_wait_time");
			for (PerDiskStats item : io_wait_time) {
				item.log();
			}
		}
		
		if (io_merged != null) {
			System.out.println("		io_merged");
			for (PerDiskStats item : io_merged) {
				item.log();
			}
		}
		
		if (io_time != null) {
			System.out.println("		io_time");
			for (PerDiskStats item : io_time) {
				item.log();
			}
		}
	}

}
