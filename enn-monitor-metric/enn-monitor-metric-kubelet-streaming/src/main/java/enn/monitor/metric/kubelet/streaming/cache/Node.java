package enn.monitor.metric.kubelet.streaming.cache;


import enn.monitor.metric.kubelet.streaming.cache.node.NodeMeta;
import enn.monitor.metric.kubelet.streaming.cache.node.NodeStatus;

import java.io.Serializable;

/**
 * Created by weize on 2017/4/13.
 */
public class Node implements Serializable {
    private NodeStatus status;
    private NodeMeta metadata;

    public NodeStatus getStatus() {
        return status;
    }

    public NodeMeta getMetadata() {
        return metadata;
    }
}
