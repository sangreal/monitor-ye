package enn.monitor.metric.kubelet.streaming.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 2017/4/14.
 */
public class ClusterInfo implements Serializable {
    private String resourceVersion;
    private Map<String, Node> nodes = new HashMap<>();
    private Map<String, Pod> pods = new HashMap<>();

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public Node getNode(String nodeIp) {
        return nodes.get(nodeIp);
    }


    public void setPods(Map<String, Pod> pods) {
        this.pods = pods;
    }

    public Pod getPod(String podName) {
        return pods.get(podName);
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }
}
