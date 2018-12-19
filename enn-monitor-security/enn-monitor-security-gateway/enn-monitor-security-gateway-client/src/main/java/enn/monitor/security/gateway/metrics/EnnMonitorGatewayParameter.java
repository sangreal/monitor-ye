package enn.monitor.security.gateway.metrics;

public class EnnMonitorGatewayParameter {
	
	private String tokenServer = null;
	private int port = -1;
	
	public EnnMonitorGatewayParameter(String tokenServer, int port) {
		this.tokenServer = tokenServer;
		this.port = port;
	}
	
	public String getTokenServer() {
		return tokenServer;
	}
	
	public void setTokenServer(String tokenServer) {
		this.tokenServer = tokenServer;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

}
