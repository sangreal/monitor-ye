package enn.monitor.metric.kubelet.streaming.cache;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weize on 17-10-20.
 */
public class K8sApiPodList {
    private String kind;
    private String apiVersion;
    @SerializedName("items")
    private List<Pod> pods;

    public K8sApiPodList() {
    }

    public String getKind() {
        return this.kind;
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public List<Pod> getPods() {
            return this.pods;
        }
}
