package enn.monitor.metric.kubelet.streaming.cache.node;

import java.io.Serializable;

/**
 * Created by weize on 2017/4/13.
 */
public class NodeStatus implements Serializable {
    private NodeCapacity capacity;

    public NodeCapacity getCapacity() {
        return capacity;
    }
}
