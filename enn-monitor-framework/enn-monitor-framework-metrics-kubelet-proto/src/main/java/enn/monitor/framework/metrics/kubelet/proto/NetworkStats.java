package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class NetworkStats implements IContainer {
	/*Interfaces     []InterfaceStats `json:"interfaces,omitempty"`
	// TCP connection stats (Established, Listen...)
	Tcp TcpStat `json:"tcp"`
	// TCP6 connection stats (Established, Listen...)
	Tcp6 TcpStat `json:"tcp6"`*/
	
	private InterfaceStats[] interfaces = null;
	private TcpStat tcp = null;
	private TcpStat tcp6 = null;
	
	public InterfaceStats[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(InterfaceStats[] interfaces) {
		this.interfaces = interfaces;
	}

	public TcpStat getTcp() {
		return tcp;
	}

	public void setTcp(TcpStat tcp) {
		this.tcp = tcp;
	}

	public TcpStat getTcp6() {
		return tcp6;
	}

	public void setTcp6(TcpStat tcp6) {
		this.tcp6 = tcp6;
	}

	@Override
	public void log() {
		if (interfaces != null) {
			for (InterfaceStats item : interfaces) {
				item.log();
			}
		}
		if (tcp != null) {
			tcp.log();
		}
		if (tcp6 != null) {
			tcp6.log();
		}
	}

}
