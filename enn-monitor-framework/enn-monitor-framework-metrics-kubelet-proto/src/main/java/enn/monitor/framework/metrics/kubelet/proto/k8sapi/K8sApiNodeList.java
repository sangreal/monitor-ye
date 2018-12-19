package enn.monitor.framework.metrics.kubelet.proto.k8sapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weize on 2017/4/13.
 */
public class K8sApiNodeList {
    private String kind;
    private String apiVersion;
    @SerializedName("items")
    private List<Node> nodes;

    public String getKind() {
        return kind;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
