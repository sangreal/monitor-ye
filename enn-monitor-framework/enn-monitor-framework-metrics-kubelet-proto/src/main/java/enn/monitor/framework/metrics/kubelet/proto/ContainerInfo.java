package enn.monitor.framework.metrics.kubelet.proto;

import java.util.Map;
import java.util.Map.Entry;

import enn.monitor.framework.metrics.kubelet.common.IContainer;

public class ContainerInfo implements IContainer {
	
	/* type detailSpec struct {
		Timestamp       time.Time            `json:"timestamp"`
		MachineName     string               `json:"machine_name,omitempty"`
		HostIp          string               `json:"hostIp,omitempty"`
		ContainerName   string               `json:"container_Name,omitempty"`
		ContainerID     string               `json:"container_Id,omitempty"`
		ContainerLabels map[string]string    `json:"container_labels,omitempty"`
		ContainerStats  *info.ContainerStats `json:"container_stats,omitempty"`
	} */
	
	private String timestamp;
	private String machine_name;
	private String hostIp;
	private String container_Name;
	private String container_Id;
	private Map<String, String> container_labels;
	private ContainerStats container_stats;
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMachine_name() {
		return machine_name;
	}
	
	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}
	
	public String getHostIp() {
		return hostIp;
	}
	
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	
	public String getContainer_Name() {
		return container_Name;
	}
	
	public void setContainer_Name(String container_Name) {
		this.container_Name = container_Name;
	}
	
	public String getContainer_Id() {
		return container_Id;
	}
	
	public void setContainer_Id(String container_Id) {
		this.container_Id = container_Id;
	}
	
	public Map<String, String> getContainer_labels() {
		return container_labels;
	}
	
	public void setContainer_labels(Map<String, String> container_labels) {
		this.container_labels = container_labels;
	}
	
	public ContainerStats getContainer_stats() {
		return container_stats;
	}

	public void setContainer_stats(ContainerStats container_stats) {
		this.container_stats = container_stats;
	}

	@Override
	public void log() {
		System.out.println("MetricsData log");
		
		System.out.printf("timestamp: %s\n", timestamp);
		System.out.printf("machine_name: %s\n", machine_name);
		System.out.printf("hostIp: %s\n", hostIp);
		System.out.printf("container_Name: %s\n", container_Name);
		System.out.printf("container_Id: %s\n", container_Id);
		
		if (container_labels != null) {
			System.out.printf("container_labels\n");
			for (Entry<String, String> entry: container_labels.entrySet()) {
				System.out.printf("container_labels key: %s   value: %s \n", entry.getKey(), entry.getValue());
			}
		}
		
		if (container_stats != null) {
			container_stats.log();
		}
		
		System.out.println();
	}

}
