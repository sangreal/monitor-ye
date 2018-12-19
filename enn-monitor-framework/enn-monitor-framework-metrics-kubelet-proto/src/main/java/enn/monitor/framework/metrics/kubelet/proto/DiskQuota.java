package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

/*
"diskquota": {
    "numofquotadisk": 3,
    "capacity": 1999420788736,
    "curusesize": 4626996535,
    "curquotasize": 602685833216,
    "avaliablesize": "availableSize:1.81TB, availabeQuotaSize:1.27TB",
    "diskstatus": [
        {
            "capacity": 666473005056,
            "curusesize": 1317393985,
            "curquotasize": 199875362816,
            "avaliablesize": "availableSize:619.47GB, availabeQuotaSize:434.55GB",
            "mountpath": "/xfs/disk1/"
        },
        {
            "capacity": 666473730048,
            "curusesize": 1897245564,
            "curquotasize": 198747095040,
            "avaliablesize": "availableSize:618.94GB, availabeQuotaSize:435.60GB",
            "mountpath": "/xfs/disk3/"
        },
        {
            "capacity": 666474053632,
            "curusesize": 1412356986,
            "curquotasize": 204063375360,
            "avaliablesize": "availableSize:619.39GB, availabeQuotaSize:430.65GB",
            "mountpath": "/xfs/disk2/"
        }
    ]
},
*/

public class DiskQuota implements IContainer {
	
	private int numofquotadisk = 0;
	private long capacity = 0;
	private long curusesize = 0;
	private long curquotasize = 0;
	
	private DiskQuotaStatus[] diskstatus = null;
	
	public int getNumofquotadisk() {
		return numofquotadisk;
	}

	public void setNumofquotadisk(int numofquotadisk) {
		this.numofquotadisk = numofquotadisk;
	}

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

	public DiskQuotaStatus[] getDiskstatus() {
		return diskstatus;
	}

	public void setDiskstatus(DiskQuotaStatus[] diskstatus) {
		this.diskstatus = diskstatus;
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
