package enn.monitor.framework.tracing.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 17-11-17.
 */
public class ZipkinTraceInfo implements TraceInfo {
    private Map<String, String> traceInfo = new HashMap<>();

    @Override
    public Map<String, String> getInfo() {
        return traceInfo;
    }

    public void set(String key, String value) {
        traceInfo.put(key, value);
    }

    public String get(String key) {
        return traceInfo.get(key);
    }
}
