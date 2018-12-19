package enn.monitor.trace.proto.model.service;

import com.google.gson.Gson;

/**
 * Created by wst_dreg on 2018-03-22.
 */
public class ResponseType {
    public String toString() {
        return new Gson().toJson(this);
    }
}
