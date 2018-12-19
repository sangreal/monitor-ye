package enn.monitor.trace.proto.response;

import com.google.gson.Gson;

/**
 * Created by weize on 18-2-5.
 */
public class Response {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
