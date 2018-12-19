package enn.monitor.log.event.extraction.pattern;

import static java.util.Objects.hash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import enn.monitor.framework.common.security.MD5Util;

/**
 * Created by weize on 18-6-21.
 */
public class Pattern implements Serializable {
    private Map<String, Integer> tokenPos;
    private String[] tokens;
    private int length;
    private String eventType;
    private String md5;

    public Pattern(String[] tokens, String eventType) {
        this.length = tokens.length;
        this.tokens = tokens;
        tokenPos = new HashMap<>();
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < tokens.length; i ++) {
            tokenPos.put(tokens[i], i);
            tmp.append(tokens[i]);
            tmp.append('-');
        }
        this.eventType = eventType;
        this.md5 = MD5Util.getMD5(tmp.toString());
    }

    public int getPos(String token) {
        return tokenPos.get(token);
    }

    public int getLength() {
        return length;
    }

    public String getEventType() {
        return eventType;
    }

    public String[] getTokens() {
        return tokens;
    }

    public String getMd5() {
        return md5;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pattern)) {
            return false;
        }
        Pattern ins = (Pattern) obj;
        if (tokens.length != ins.tokens.length) {
            return false;
        }
        return md5.equals(ins.getMd5());
    }

    @Override
    public int hashCode() {
        return hash(md5);
    }
}
