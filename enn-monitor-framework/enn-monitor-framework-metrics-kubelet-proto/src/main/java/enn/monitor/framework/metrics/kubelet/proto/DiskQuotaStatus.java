package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

/*
"capacity": 666474053632,
"curusesize": 1412356986,
"curquotasize": 204063375360,
"avaliablesize": "availableSize:619.39GB, availabeQuotaSize:430.65GB",
"mountpath": "/xfs/disk2/"
*/

public class DiskQuotaStatus implements IContainer {
	
	private long capacity = 0;
	private long curusesize = 0;
	private long curquotasize = 0;
	private String mountpath = null;
	
	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getCurusesize() {
		return curusesize;
	}

	public void setCurusesize(long curusesize) {
		this.curusesize = curusesize;
	}

	public long getCurquotasize() {
		return curquotasize;
	}

	public void setCurquotasize(long curquotasize) {
		this.curquotasize = curquotasize;
	}

	public String getMountpath() {
		return mountpath;
	}

	public void setMountpath(String mountpath) {
		this.mountpath = mountpath;
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
