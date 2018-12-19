package enn.monitor.metric.kubelet.streaming.cache.node;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by weize on 2017/4/14.
 */
public class NodeMeta implements Serializable {
    private String name;
    private Map<String, String> labels;
    private Map<String, String> annotations;
    private String uid;
    private String resourceVersion;
    private Date creationTimestamp;

    public String getName() {
        return name;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public String getUid() {
        return uid;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }
}
