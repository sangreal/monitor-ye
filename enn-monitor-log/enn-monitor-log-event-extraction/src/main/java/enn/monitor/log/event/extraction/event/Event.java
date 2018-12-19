package enn.monitor.log.event.extraction.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weize on 18-6-21.
 */
public class Event {
    private String rawLog;
    private String type;
    private Map<String, String> entities;
    private String token;
    private long timestamp;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Event() {
        entities = new HashMap<>();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEntities(Map<String, String> entities) {
        this.entities = entities;
    }

    public Map<String, String> getEntities() {
        return entities;
    }

    public void addEntity(String key, String value) {
        entities.put(key, value);
    }

    public String getType() {
        return type;
    }

    public String getRawLog() {
        return rawLog;
    }

    public void setRawLog(String rawLog) {
        this.rawLog = rawLog;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
