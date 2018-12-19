package enn.monitor.metric.kubelet.streaming.cache;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;

/**
 * Created by weize on 17-10-24.
 */
public class ClusterInfoBroadcastWrapper {
    private static volatile Broadcast<ClusterInfo> instance = null;
    private static String lastVersion;

    public static Broadcast<ClusterInfo> getInstance() {
        return instance;
    }

    public static void update(JavaSparkContext jsc, CacheManager cacheManager) {
        synchronized (ClusterInfoBroadcastWrapper.class) {
            if (cacheManager.getClusterInfo().getResourceVersion().equals(lastVersion)) {
                return;
            }
            if (instance != null) {
                instance.unpersist();
            }
            ClusterInfo info = cacheManager.getClusterInfo();
            lastVersion = info.getResourceVersion();
            instance = jsc.broadcast(info);
        }
    }
}
