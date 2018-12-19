package enn.monitor.framework.metrics.kubelet.proto.k8sapi;

/**
 * Created by weize on 2017/4/13.
 */
public class Node {
    private NodeStatus status;
    private NodeMeta metadata;

    public NodeStatus getStatus() {
        return status;
    }

    public NodeMeta getMetadata() {
        return metadata;
    }
}
