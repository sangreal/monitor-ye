package enn.monitor.framework.metrics.kubelet.proto;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

/**
 * Created by weize on 17-6-6.
 */
public class GpuCoreStats implements IContainer {
    private String gpu_name;
    private String gpu_num;
    private String gpu_util;
    private String gpu_fan;
    private String gpu_temp;
    private String gpu_power_capacity;
    private String gpu_power_usage;
    private String gpu_memory_capacity;
    private String gpu_memory_usage;

    public String getGpuName() {
        return gpu_name;
    }

    public String getGpuNum() {
        return gpu_num;
    }

    public double getGpuUtil() {
        try {
            return Double.parseDouble(gpu_util.replaceAll("%", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public double getGpuFan() {
        try {
            return Double.parseDouble(gpu_fan.replaceAll("%", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getGpuTemp() {
        try {
            return Integer.parseInt(gpu_temp.replaceAll("C", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getGpuPowerCapacity() {
        try {
            return Integer.parseInt(gpu_power_capacity.replaceAll("W", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public int getGpuPowerUsage() {
        try {
            return Integer.parseInt(gpu_power_usage.replaceAll("W", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public long getGpuMemoryCapacity() {
        try {
            return Long.parseLong(gpu_memory_capacity.replaceAll("MiB", "")) * 1024 * 1024;
        } catch (Exception e) {
            return 0;
        }
    }

    public long getGpuMemoryUsage() {
        try {
            return Long.parseLong(gpu_memory_usage.replaceAll("MiB", "")) * 1024 * 1024;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void log() {

    }
}
