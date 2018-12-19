package enn.monitor.log.archive.test;

import java.net.InetAddress;

import org.elasticsearch.action.admin.cluster.stats.ClusterStatsRequest;
import org.elasticsearch.action.admin.cluster.stats.ClusterStatsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESTest {
	
	private static TransportClient esTransportClient = null;
	
	public static void main(String[] args) throws Exception {
    	Settings settings = Settings.builder()
		        .put("cluster.name", "es-log")
		        .put("client.transport.sniff", false)
		        .build();
		
		esTransportClient = new PreBuiltTransportClient(settings);
		
		String[] hostList = "10.19.248.200:31931".split(",");
		String[] ipPort = null;
		for (int i = 0; i < hostList.length; ++i) {
			ipPort = hostList[i].split(":");
			esTransportClient.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
		}
		
		ClusterStatsResponse result = esTransportClient.admin().cluster().clusterStats(new ClusterStatsRequest()).actionGet();
		
		System.out.println(result.getNodesStats().getFs().getTotal());
		System.out.println(result.getNodesStats().getFs().getAvailable());
		//System.out.println(result);
    }

}
