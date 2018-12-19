package enn.monitor.ops.elasticsearch.data.cluster;

import java.util.ArrayList;
import java.util.List;

public class EnnMonitorOpsElasticsearchDataClusterRerouteRequest {
	
//	POST /_cluster/reroute
//	{
//	    "commands" : [
//	        {
//	            "move" : {
//	                "index" : "test", "shard" : 0,
//	                "from_node" : "node1", "to_node" : "node2"
//	            }
//	        },
//	        {
//	          "allocate_replica" : {
//	                "index" : "test", "shard" : 1,
//	                "node" : "node3"
//	          }
//	        }
//	    ]
//	}
	
	private List<ClusterRerouteCommandInterface> commands = new ArrayList<ClusterRerouteCommandInterface>();
	
	public List<ClusterRerouteCommandInterface> getCommands() {
		return commands;
	}

	public void addCommand(ClusterRerouteCommandInterface command) {
		commands.add(command);
	}
	
	public static class ClusterRerouteMove implements ClusterRerouteCommandInterface {
		private ClusterRerouteCommand move = new ClusterRerouteCommand();
		
		public String getIndex() {
			return move.index;
		}

		public void setIndex(String index) {
			move.index = index;
		}

		public int getShard() {
			return move.shard;
		}

		public void setShard(int shard) {
			move.shard = shard;
		}

		public String getFrom_node() {
			return move.from_node;
		}

		public void setFrom_node(String from_node) {
			move.from_node = from_node;
		}

		public String getTo_node() {
			return move.to_node;
		}

		public void setTo_node(String to_node) {
			move.to_node = to_node;
		}
	}
	
	public static class ClusterRerouteAlloacationReplica implements ClusterRerouteCommandInterface {
		private ClusterRerouteCommand allocate_replica = new ClusterRerouteCommand();
		
		public String getIndex() {
			return allocate_replica.index;
		}

		public void setIndex(String index) {
			allocate_replica.index = index;
		}

		public int getShard() {
			return allocate_replica.shard;
		}

		public void setShard(int shard) {
			allocate_replica.shard = shard;
		}

		public String getNode() {
			return allocate_replica.node;
		}

		public void setNode(String node) {
			allocate_replica.node = node;
		}
	}

	public static class ClusterRerouteCommand { 
		protected String index = null;
		protected int    shard = 0;
		
		protected String from_node = null;
		protected String to_node = null;
		
		protected String node = null;
		
	}
	
	public static interface ClusterRerouteCommandInterface {
		
	}
	
}
