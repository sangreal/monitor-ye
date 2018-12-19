package enn.monitor.metric.kubelet.streaming.cache.pod;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by weize on 17-10-20.
 */
public class PodMeta implements Serializable
{
    private String name;
    private String namespace;
    private Map<String, String> labels;
    private Map<String, String> annotations;
    private String uid;
    private String resourceVersion;
    private Date creationTimestamp;

    public PodMeta() {
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public Map<String, String> getLabels() {
        return this.labels;
    }

    public Map<String, String> getAnnotations() {
        return this.annotations;
    }

    public String getUid() {
        return this.uid;
    }

    public String getResourceVersion() {
        return this.resourceVersion;
    }

    public Date getCreationTimestamp() {
        return this.creationTimestamp;
    }
}