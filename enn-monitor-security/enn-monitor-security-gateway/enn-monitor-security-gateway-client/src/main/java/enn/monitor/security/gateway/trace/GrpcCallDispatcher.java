package enn.monitor.security.gateway.trace;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import enn.monitor.framework.tracing.model.TracerConfig;
import org.apache.log4j.Logger;

import enn.monitor.security.gateway.grpc.client.EnnMonitorSecurityGatewayGrpcClient;
import enn.monitor.security.gateway.parameters.EnnMonitorSecurityGatewayRequest;
import okhttp3.internal.Util;
import zipkin2.reporter.internal.AwaitableCallback;

/**
 * Created by weize on 17-12-18.
 */
public class GrpcCallDispatcher {
    private ThreadPoolExecutor executor;
    private EnnMonitorSecurityGatewayGrpcClient client;

    @Inject
    private TracerConfig config;

    public GrpcCallDispatcher(){}
    public void init() {
        executor = new ThreadPoolExecutor(10, 256, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                Util.threadFactory("EnnGatewaySender grpc pool", true));

        String[] hostPort = config.getReportServer().split(":");
        client = new EnnMonitorSecurityGatewayGrpcClient(hostPort[0], Integer.parseInt(hostPort[1]), null);
        client.attachHeader("mute", "true");
    }

    private static volatile GrpcCallDispatcher instance;
    public static GrpcCallDispatcher getInstance() {
        if(instance == null) {
            synchronized(GrpcCallDispatcher.class) {
                if(instance == null) {
                    instance = TracerBuilder.guiceInjector.getInstance(GrpcCallDispatcher.class);
                    instance.init();
                }
            }
        }
        return instance;
    }

    public void request(byte[] message, AwaitableCallback callback) {
        executor.execute(new GrpcCallRunnable(new Runnable() {
            @Override
            public void run() {
                EnnMonitorSecurityGatewayRequest.Builder request = EnnMonitorSecurityGatewayRequest.newBuilder();
                request.setToken(config.getToken());
                request.setSource("zipkin_gw");
                request.setJsonList(new String(message));
//                Logger.getRootLogger().info("start sending span message, size: " + message.length);
                client.put(request.build());
//                Logger.getRootLogger().info("finish sending span message, size: " + message.length);
            }
        }, callback));
    }

    class GrpcCallRunnable implements Runnable {
        private Runnable runnable;
        private AwaitableCallback callback;

        public GrpcCallRunnable(Runnable runnable, AwaitableCallback callback) {
            this.runnable = runnable;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch(Exception e) {
                e.printStackTrace();
                callback.onError(e);
            }
            callback.onSuccess(null);
        }
    }


}
