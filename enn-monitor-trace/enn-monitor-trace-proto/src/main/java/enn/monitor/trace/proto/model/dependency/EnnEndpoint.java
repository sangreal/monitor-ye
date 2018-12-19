package enn.monitor.trace.proto.model.dependency;

import java.io.Serializable;

/**
 * Created by weize on 18-1-3.
 */
public class EnnEndpoint implements Serializable {
    String bizLine;
    String serviceName;
    String instance;
    String resource;

    public EnnEndpoint(String bizLine, String serviceName, String instance, String resource) {
        this.bizLine = bizLine;
        this.serviceName = serviceName;
        this.instance = instance;
        this.resource = resource;
    }

    public String getBizLine() {
        return bizLine;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getInstance() {
        return instance;
    }

    public String getResource() {
        return resource;
    }

    public EnnEndpoint(String bizLine, String serviceName, String instance) {
        this(bizLine, serviceName, instance, "unknown");
    }

    public String id() {
        return bizLine + "-" + serviceName + "-" + instance + "-" + resource;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this.bizLine == null ? 1 : this.bizLine.hashCode();
        h *= 1000003;
        h ^= this.serviceName == null ? 1 : this.serviceName.hashCode();
        h *= 1000003;
        h ^= this.instance == null ? 1 : this.instance.hashCode();
        h *= 1000003;
        h ^= this.resource == null ? 1 : this.resource.hashCode();
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof EnnEndpoint)) return false;
        EnnEndpoint p = (EnnEndpoint) obj;
        return bizLine != null && bizLine.equals(p.bizLine)
                && serviceName != null && serviceName.equals(p.serviceName)
                && instance != null && instance.equals(p.instance)
                && resource != null && resource.equals(p.resource);
    }
}
