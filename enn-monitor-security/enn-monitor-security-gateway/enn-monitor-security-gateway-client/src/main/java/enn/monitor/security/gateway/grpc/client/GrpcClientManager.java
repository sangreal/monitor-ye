package enn.monitor.security.gateway.grpc.client;

/**
 * Created by weize on 18-2-26.
 */
public class GrpcClientManager {
    private static class SingletonHolder {
        private static final EnnMonitorSecurityGatewayGrpcClient INSTANCE = GrpcClientBuilder.get();
    }
    private GrpcClientManager (){}
    public static final EnnMonitorSecurityGatewayGrpcClient getInstance() {
        return GrpcClientManager.SingletonHolder.INSTANCE;
    }

    public static final GrpcClientConfig getConfig() {
        return GrpcClientBuilder.config;
    }
}
