package enn.monitor.metric.kubelet.streaming.cache;

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
    private MetaData metadata;

    public String getKind() {
        return kind;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    class MetaData {
        private String selfLink;
        private String resourceVersion;

        public String getSelfLink() {
            return selfLink;
        }

        public String getResourceVersion() {
            return resourceVersion;
        }
    }
}
