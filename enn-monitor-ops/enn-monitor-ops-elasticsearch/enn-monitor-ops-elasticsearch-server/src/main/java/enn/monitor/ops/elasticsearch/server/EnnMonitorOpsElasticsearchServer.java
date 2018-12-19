package enn.monitor.ops.elasticsearch.server;

import com.google.gson.Gson;

import enn.monitor.ops.elasticsearch.data.cluster.EnnMonitorOpsElasticsearchDataClusterRerouteRequest;
import enn.monitor.ops.elasticsearch.data.cluster.EnnMonitorOpsElasticsearchDataClusterRerouteRequest.ClusterRerouteMove;
import enn.monitor.ops.elasticsearch.httpclient.EnnMonitorOpsElasticsearchHttpClient;

public class EnnMonitorOpsElasticsearchServer {
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) throws Exception {
		String[] indexes = {"yancheng-g-laikang-2018-11-20", "yancheng-monitor-system-metrics-2018-11-20", 
				"yancheng-system-tools-2018-11-20", "yancheng-nanjing-jc-2018-11-20"};
		int[] shards = {2, 2, 2, 2};
		
		EnnMonitorOpsElasticsearchHttpClient client = new EnnMonitorOpsElasticsearchHttpClient("10.19.248.200", 31921);
		
		for (int i = 0; i < indexes.length; ++i) {
			try {
				exec(client, indexes[i], shards[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Thread.sleep(10000);
	}
	
	private static void exec(EnnMonitorOpsElasticsearchHttpClient client, String index, int shard) throws Exception {
		String json = null;
		
		ClusterRerouteMove rerouteCommandMove = null;
		
		EnnMonitorOpsElasticsearchDataClusterRerouteRequest commands = new EnnMonitorOpsElasticsearchDataClusterRerouteRequest();
		
		rerouteCommandMove = new ClusterRerouteMove();
		rerouteCommandMove.setIndex(index);
		rerouteCommandMove.setShard(shard);
		rerouteCommandMove.setFrom_node("elasticsearch-data-b74547b77-8x56s");
		rerouteCommandMove.setTo_node("elasticsearch-data-b74547b77-m56jg");
		commands.addCommand(rerouteCommandMove);
		
		json = gson.toJson(commands);
		client.put(json);
		
	}

}
