package enn.monitor.log.archive.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.cluster.node.stats.NodeStats;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsRequest;
import org.elasticsearch.action.admin.cluster.node.stats.NodesStatsResponse;
import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.cluster.node.DiscoveryNode.Role;

import enn.monitor.log.archive.util.EnnMonitorLogStorageUtil.NodeStatsStorage.NodeStatsStorageNodeInfo;
import enn.monitor.security.gateway.stats.util.EnnMonitorSecurityGatewayStatsUtil;

public class EnnMonitorLogStorageUtil {
	
	private static final Logger logger = LogManager.getLogger();
	
	private static Set<String> keySet = new HashSet<String>();
	
	private static String esCluster = null;
	
	public static void init(String esClusterParam) {
		esCluster = esClusterParam;
	}
	
	public static class NodeStatsStorage {
		public long usage = 0l;
		public long total = 0l;
		
		public List<NodeStatsStorageNodeInfo> nodeInfoList = new ArrayList<NodeStatsStorageNodeInfo>();
		
		public static class NodeStatsStorageNodeInfo {
			public String nodeName = null;
			public long usage = 0l;
			public long total = 0l;
		}
	}
	
	public static NodeStatsStorage getNodeStatsStorageInfo() {
		NodeStatsStorage storage = new NodeStatsStorage();
		
		NodeStatsStorageNodeInfo nodeInfo = null;
		
		Map<String, String> tagsMap = null;
		
		int count = 3;
		NodesStatsResponse stats = null;
		long usage = 0l;
		long total = 0l;
		
		NodesStatsRequest statsRequest = new NodesStatsRequest();
        statsRequest.clear().fs(true).indices(new CommonStatsFlags(CommonStatsFlags.Flag.Store));
		
		while (count > 0) {
			--count;
			
			try {
				stats = EnnMonitorLogElasticsearchUtil.getElasticsearchClient().admin().cluster().nodesStats(statsRequest).actionGet();
				usage = 0l;
				total = 0l;
				for (NodeStats nodeStats : stats.getNodes()) {
		            DiscoveryNode node = nodeStats.getNode();
		            
		            if (node.getRoles().contains(Role.DATA) == false) {
		            	continue;
		            }

		            logger.info(node.getName());
		            logger.info(nodeStats.getFs().getTotal().getTotal());
		            logger.info(nodeStats.getFs().getTotal().getAvailable());
		            
		            nodeInfo = new NodeStatsStorageNodeInfo();
		            nodeInfo.nodeName = node.getName();
		            nodeInfo.total = nodeStats.getFs().getTotal().getTotal().getBytes();
		            nodeInfo.usage = nodeStats.getFs().getTotal().getTotal().getBytes() - nodeStats.getFs().getTotal().getAvailable().getBytes();
		            storage.nodeInfoList.add(nodeInfo);
		            
		            total = total + nodeInfo.total;
		            usage = usage + nodeInfo.usage;
		            
		            if (keySet.contains(nodeInfo.nodeName + "total") == false) {
		            	tagsMap = new HashMap<String, String>();
		            	tagsMap.put("esNode", nodeInfo.nodeName);
		            	tagsMap.put("esCluster", esCluster);
		            	EnnMonitorSecurityGatewayStatsUtil.register(nodeInfo.nodeName + "total", "elasticSearch_storage_total", tagsMap);
		            	
		            	keySet.add(nodeInfo.nodeName + "total");
		            }
		            EnnMonitorSecurityGatewayStatsUtil.set(nodeInfo.nodeName + "total", nodeInfo.total);
		            
		            if (keySet.contains(nodeInfo.nodeName + "usage") == false) {
		            	tagsMap = new HashMap<String, String>();
		            	tagsMap.put("esNode", nodeInfo.nodeName);
		            	tagsMap.put("esCluster", esCluster);
		            	EnnMonitorSecurityGatewayStatsUtil.register(nodeInfo.nodeName + "usage", "elasticSearch_storage_usage", tagsMap);
		            	
		            	keySet.add(nodeInfo.nodeName + "usage");
		            }
		            EnnMonitorSecurityGatewayStatsUtil.set(nodeInfo.nodeName + "usage", nodeInfo.usage);
		        }
				
				logger.info("total is {}, usage is {}", total, usage);
				
				storage.total = total;
				storage.usage = usage;
				
				if (keySet.contains("es_storage_total") == false) {
	            	tagsMap = new HashMap<String, String>();
	            	tagsMap.put("esNode", "es_sum");
	            	tagsMap.put("esCluster", esCluster);
	            	EnnMonitorSecurityGatewayStatsUtil.register("es_storage_total", "elasticSearch_storage_total", tagsMap);
	            	
	            	keySet.add("es_storage_total");
	            }
	            EnnMonitorSecurityGatewayStatsUtil.set("es_storage_total", storage.total);
	            
	            if (keySet.contains("es_storage_usage") == false) {
	            	tagsMap = new HashMap<String, String>();
	            	tagsMap.put("esNode", "es_sum");
	            	tagsMap.put("esCluster", esCluster);
	            	EnnMonitorSecurityGatewayStatsUtil.register("es_storage_usage", "elasticSearch_storage_usage", tagsMap);
	            	
	            	keySet.add("es_storage_usage");
	            }
	            EnnMonitorSecurityGatewayStatsUtil.set("es_storage_usage", storage.usage);
				
				return storage;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					logger.error(e1.getMessage());
				}
			}
		}
		
		return null;
	}

}
