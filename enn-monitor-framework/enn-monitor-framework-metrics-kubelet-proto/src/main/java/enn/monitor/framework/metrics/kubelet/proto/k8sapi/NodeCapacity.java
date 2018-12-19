package enn.monitor.framework.metrics.kubelet.proto.k8sapi;
import com.google.gson.annotations.SerializedName;

/**
 * Created by weize on 2017/4/13.
 */
public class NodeCapacity {
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
        return Long.parseLong(memory.replaceAll("Ki", ""));
    }

    public int getPods() {
        return pods;
    }
}
