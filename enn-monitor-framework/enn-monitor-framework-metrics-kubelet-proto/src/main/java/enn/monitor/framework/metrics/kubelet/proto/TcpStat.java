package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class TcpStat implements IContainer {
	/*
	Established uint64
	SynSent uint64
	SynRecv uint64
	FinWait1 uint64
	FinWait2 uint64
	TimeWait uint64
	Close uint64
	CloseWait uint64
	LastAck uint64
	Listen uint64
	Closing uint64
	 */
	
	private long Established;
	private long SynSent;
	private long SynRecv;
	private long FinWait1;
	private long FinWait2;
	private long TimeWait;
	private long Close;
	private long CloseWait;
	private long LastAck;
	private long Listen;
	private long Closing;

	@Override
	public void log() {
		System.out.println("			TcpStat Established: " + Established);
		System.out.println("			TcpStat SynSent: " + SynSent);
		System.out.println("			TcpStat SynRecv: " + SynRecv);
		System.out.println("			TcpStat FinWait1: " + FinWait1);
		System.out.println("			TcpStat FinWait2: " + FinWait2);
		System.out.println("			TcpStat TimeWait: " + TimeWait);
		System.out.println("			TcpStat Close: " + Close);
		System.out.println("			TcpStat CloseWait: " + CloseWait);
		System.out.println("			TcpStat LastAck: " + LastAck);
		System.out.println("			TcpStat Listen: " + Listen);
		System.out.println("			TcpStat Closing: " + Closing);
	}

}
