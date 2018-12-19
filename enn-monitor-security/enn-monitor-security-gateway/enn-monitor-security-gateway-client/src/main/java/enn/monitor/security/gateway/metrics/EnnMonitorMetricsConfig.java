package enn.monitor.security.gateway.metrics;

import org.apache.logging.log4j.util.Strings;

import com.google.gson.Gson;

/**
 * Created by weize on 18-3-13.
 */
public class EnnMonitorMetricsConfig {
    private String traceSwitch;
    private String metricSwitch;
    private String statsSwitch;
    private String gatewayHost;
    private String gatewayPort;
    private String token;
    private int metricsFrequency;
    private int statsFrequency;

    public EnnMonitorMetricsConfig(String traceSwitch, String metricSwitch, String statsSwitch,
                                   String gatewayHost, String gatewayPort, String token,
                                   int metricsFrequency, int statsFrequency) {
        this.traceSwitch = traceSwitch;
        this.metricSwitch = metricSwitch;
        this.statsSwitch = statsSwitch;
        this.gatewayHost = gatewayHost;
        this.gatewayPort = gatewayPort;
        this.token = token;
        this.metricsFrequency = metricsFrequency;
        this.statsFrequency = statsFrequency;
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

    public boolean enableMetrics() {
        return Strings.isNotEmpty(metricSwitch) && "on".equals(metricSwitch);
    }

    public boolean enableTrace() {
        return Strings.isNotEmpty(traceSwitch) && "on".equals(traceSwitch);
    }
    
    public boolean enableStats() {
    	return Strings.isNotEmpty(statsSwitch) && "on".equals(statsSwitch);
    }

    public int getMetricsFrequency() {
        return metricsFrequency;
    }
    
    public int getStatsFrequency() {
    	return statsFrequency;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
