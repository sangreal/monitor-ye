package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class InterfaceStats implements IContainer {
	
	/*
	 Name string `json:"name"`
	RxBytes uint64 `json:"rx_bytes"`
	RxPackets uint64 `json:"rx_packets"`
	RxErrors uint64 `json:"rx_errors"`
	RxDropped uint64 `json:"rx_dropped"`
	TxBytes uint64 `json:"tx_bytes"`
	TxPackets uint64 `json:"tx_packets"`
	TxErrors uint64 `json:"tx_errors"`
	TxDropped uint64 `json:"tx_dropped"`
	 */
	
	private String name;
	private long rx_bytes;
	private long rx_packets;
	private long rx_errors;
	private long rx_dropped;
	private long tx_bytes;
	private long tx_packets;
	private long tx_errors;
	private long tx_dropped;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRx_bytes() {
		return rx_bytes;
	}

	public void setRx_bytes(long rx_bytes) {
		this.rx_bytes = rx_bytes;
	}

	public long getRx_packets() {
		return rx_packets;
	}

	public void setRx_packets(long rx_packets) {
		this.rx_packets = rx_packets;
	}

	public long getRx_errors() {
		return rx_errors;
	}

	public void setRx_errors(long rx_errors) {
		this.rx_errors = rx_errors;
	}

	public long getRx_dropped() {
		return rx_dropped;
	}

	public void setRx_dropped(long rx_dropped) {
		this.rx_dropped = rx_dropped;
	}

	public long getTx_bytes() {
		return tx_bytes;
	}

	public void setTx_bytes(long tx_bytes) {
		this.tx_bytes = tx_bytes;
	}

	public long getTx_packets() {
		return tx_packets;
	}

	public void setTx_packets(long tx_packets) {
		this.tx_packets = tx_packets;
	}

	public long getTx_errors() {
		return tx_errors;
	}

	public void setTx_errors(long tx_errors) {
		this.tx_errors = tx_errors;
	}

	public long getTx_dropped() {
		return tx_dropped;
	}

	public void setTx_dropped(long tx_dropped) {
		this.tx_dropped = tx_dropped;
	}

	@Override
	public void log() {
		if (name != null) {
			System.out.println("			InterfaceStats name: " + name);
		}
		System.out.println("			InterfaceStats rx_bytes: " + rx_bytes);
		System.out.println("			InterfaceStats rx_packets: " + rx_packets);
		System.out.println("			InterfaceStats rx_errors: " + rx_errors);
		System.out.println("			InterfaceStats rx_dropped: " + rx_dropped);
		System.out.println("			InterfaceStats tx_bytes: " + tx_bytes);
		System.out.println("			InterfaceStats tx_packets: " + tx_packets);
		System.out.println("			InterfaceStats tx_errors: " + tx_errors);
		System.out.println("			InterfaceStats tx_dropped: " + tx_dropped);
	}

}
