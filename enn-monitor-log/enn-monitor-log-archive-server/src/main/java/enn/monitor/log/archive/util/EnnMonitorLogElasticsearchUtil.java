package enn.monitor.log.archive.util;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class EnnMonitorLogElasticsearchUtil {
	
	private static TransportClient esTransportClient = null;

	public static void init(String esUrl, String clusterName) throws Exception {
		Settings settings = Settings.builder()
		        .put("cluster.name", clusterName)
		        .put("client.transport.sniff", false)
		        .build();
		
		esTransportClient = new PreBuiltTransportClient(settings);
		
		String[] hostList = esUrl.split(",");
		String[] ipPort = null;
		for (int i = 0; i < hostList.length; ++i) {
			ipPort = hostList[i].split(":");
			esTransportClient.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
		}
	}
	
	public static TransportClient getElasticsearchClient() {
		return esTransportClient;
	}
}
