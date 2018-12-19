package enn.monitor.metric.kubelet.streaming.cache.node;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by weize on 2017/4/13.
 */
public class NodeCapacity implements Serializable {
    @SerializedName("alpha.kubernetes.io/nvidia-gpu")
    private int gpu;
    private int cpu;
    private String memory;
    private int pods;

    public int getGpu() {
        return gpu;
    }

    public int getCpu() {
        return cpu;
    }

    public long getMemory() {
        return Long.parseLong(memory.replaceAll("Ki", "")) * 1000;
    }

    public int getPods() {
        return pods;
    }
}
