package enn.monitor.security.gateway.grpc.client;

import com.google.gson.Gson;

/**
 * Created by weize on 18-2-26.
 */
public class GrpcClientConfig {
    private String traceSwitch;
    private String gatewayHost;
    private String gatewayPort;
    private String token;

    public GrpcClientConfig(String traceSwitch, String gatewayHost, String gatewayPort, String token) {
        this.traceSwitch = traceSwitch;
        this.gatewayHost = gatewayHost;
        this.gatewayPort = gatewayPort;
        this.token = token;
    }

    public String getTraceSwitch() {
        return traceSwitch;
    }

    public void setTraceSwitch(String traceSwitch) {
        this.traceSwitch = traceSwitch;
    }

    public String getGatewayHost() {
        return gatewayHost;
    }

    public void setGatewayHost(String gatewayHost) {
        this.gatewayHost = gatewayHost;
    }

    public String getGatewayPort() {
        return gatewayPort;
    }

    public void setGatewayPort(String gatewayPort) {
        this.gatewayPort = gatewayPort;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
