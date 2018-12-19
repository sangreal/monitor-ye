package enn.monitor.trace.dataapi.util;

import okhttp3.OkHttpClient;

/**
 * Created by wst_dreg on 2018-03-20 .
 */
public class EnnMonitorElasticsearchHttpClient {
    public static final OkHttpClient client = new OkHttpClient().newBuilder().build();
}
