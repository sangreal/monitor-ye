package enn.monitor.framework.tracing.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 17-11-17.
 */
public class ZipkinWrapper<T> {
    private Map<String, String> traceInfo = new HashMap<>();
    private T rawData;

    public ZipkinWrapper(T rawData) {
        this.rawData = rawData;
    }

    public T getRawData() {
        return rawData;
    }

    public void set(String key, String value) {
        traceInfo.put(key, value);
    }

    public String get(String key) {
        return traceInfo.get(key);
    }
}
