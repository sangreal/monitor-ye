package enn.monitor.metric.kubelet.streaming.cache;

import enn.monitor.metric.kubelet.streaming.cache.pod.PodMeta;
import enn.monitor.metric.kubelet.streaming.cache.pod.PodSpec;

import java.io.Serializable;

/**
 * Created by weize on 17-10-20.
 */
public class Pod implements Serializable {
    private PodMeta metadata;
    private PodSpec spec;

    public PodMeta getMetadata() {
        return metadata;
    }

    public PodSpec getSpec() {
        return spec;
    }
}
