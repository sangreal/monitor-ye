package enn.monitor.metric.kubelet.streaming.cache;

import org.apache.log4j.Logger;

/**
 * Created by weize on 2017/4/14.
 *
 * v1: read data from k8s api and cache in java heap
 */
public class CacheManager {
    private static CacheManager cacheManager;
    private K8sApiReader k8sApiReader;
    private volatile ClusterInfo clusterInfo;

    private CacheManager(String apiServer,
                         String apiUser, String apiPass) {
        k8sApiReader = new K8sApiReader(apiServer, apiUser, apiPass);
        refreshClusterInfo();
    }

    public static CacheManager getInstance(String apiServer,
                                           String apiUser, String apiPass) {
        if (cacheManager == null) {
            synchronized (CacheManager.class) {
                if (cacheManager == null) {
                    cacheManager = new CacheManager(apiServer, apiUser, apiPass);
                }
            }
        }
        return cacheManager;
    }

    public static CacheManager getInstance() {
        return cacheManager;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(30000);
                    } catch (Exception e) {

                    }
                    refreshClusterInfo();
                }
            }
        }).start();
    }

    public void refreshClusterInfo() {
        try {
            clusterInfo = k8sApiReader.getClusterInfo();
        } catch (Exception e){
            Logger.getRootLogger().error("Update clusterinfo from k8s apiserver failed, reason : " + e.getMessage());
        }
    }

    public ClusterInfo getClusterInfo() {
        return clusterInfo;
        // kubernetes.default:443
    }

    public static void main(String[] args) {
        CacheManager cacheManager = new CacheManager("10.19.140.6:6443", "admin", "admin");
        cacheManager.getClusterInfo();
    }
}
