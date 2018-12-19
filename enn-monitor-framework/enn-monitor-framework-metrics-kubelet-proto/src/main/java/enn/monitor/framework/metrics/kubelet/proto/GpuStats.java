package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

/**
 * Created by weize on 17-6-6.
 */
public class GpuStats implements IContainer {

	/*{
            "make": "nvidia",
            "model": "GeForce GTX 1080",
            "id": "GPU-aacdf3ce-7666-eb58-57c4-fd684b4f0bac",
            "memory_total": 8506048512,
            "memory_used": 0,
            "duty_cycle": 0
        }
	 */

//    private int numofgpu;
//    private GpuCoreStats[] usages = null;
//
//    public int getNumofgpu() {
//        return numofgpu;
//    }
//
//    public GpuCoreStats[] getUsages() {
//        return usages;
//    }

    private String make = null;
    private String model = null;
    private String id = null;
    private long memory_total = 0;
    private long memory_used = 0;
    private long duty_cycle = 0;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMemory_total() {
        return memory_total;
    }

    public void setMemory_total(long memory_total) {
        this.memory_total = memory_total;
    }

    public long getMemory_used() {
        return memory_used;
    }

    public void setMemory_used(long memory_used) {
        this.memory_used = memory_used;
    }

    public long getDuty_cycle() {
        return duty_cycle;
    }

    public void setDuty_cycle(long duty_cycle) {
        this.duty_cycle = duty_cycle;
    }

    @Override
    public void log() {

    }

}
