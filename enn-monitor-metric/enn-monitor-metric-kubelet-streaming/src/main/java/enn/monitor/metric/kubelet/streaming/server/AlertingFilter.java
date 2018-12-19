package enn.monitor.metric.kubelet.streaming.server;

import enn.monitor.framework.common.constval.OpentsdbMetricsConst;
import enn.monitor.metric.kubelet.streaming.cache.ClusterInfo;
import enn.monitor.metric.kubelet.streaming.cache.Node;
import enn.monitor.metric.kubelet.streaming.cache.pod.PodSpec;
import enn.monitor.streaming.common.proto.Metric;
import org.apache.directory.api.util.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weize on 17-9-6.
 */
public class AlertingFilter implements Serializable {
    private static Set<String> nodeAlertingMetrics = new HashSet<>();
    private static Set<String> podAlertingMetrics = new HashSet<>();

    static {
        nodeAlertingMetrics.add("cpu_total");
        nodeAlertingMetrics.add("memory_working_set");
        nodeAlertingMetrics.add("diskio_bytes_Write");
        nodeAlertingMetrics.add("diskio_bytes_Total");
        nodeAlertingMetrics.add("diskio_bytes_Read");
        nodeAlertingMetrics.add("network_rcv_bytes");
        nodeAlertingMetrics.add("network_snd_bytes");
        nodeAlertingMetrics.add("disk_available");
        nodeAlertingMetrics.add("disk_capacity");
    }

    static {
        podAlertingMetrics.add("cpu_total");
        podAlertingMetrics.add("memory_working_set");
        podAlertingMetrics.add("diskio_bytes_Write");
        podAlertingMetrics.add("diskio_bytes_Total");
        podAlertingMetrics.add("diskio_bytes_Read");
        podAlertingMetrics.add("network_rcv_bytes");
        podAlertingMetrics.add("network_snd_bytes");
    }

    public static List<Metric> filter(List<Metric> metrics, ClusterInfo clusterInfo) {
        List<Metric> result = new ArrayList<>();
        metrics.forEach(metric -> {
            try {
                if ("node".equals(metric.getType())
                        && nodeAlertingMetrics.contains(metric.getMetric())) {
                    result.add(metric);
                    if ("cpu_total".equals(metric.getMetric())) {
                        String host = metric.getTags().get(OpentsdbMetricsConst.HOST);
                        Node node = clusterInfo.getNode(metric.getTags().get(OpentsdbMetricsConst.HOST));
                        if (node == null) {
                            System.out.println("host : " + host + " not exist in clusterInfo");
                            return;
                        }
                        Metric cpuPercentage = new Metric();
                        cpuPercentage.setMetric("cpu_total_avg");
                        cpuPercentage.setTags(metric.getTags());
                        cpuPercentage.setTimestamp(metric.getTimestamp());
                        int cpuCore = clusterInfo.getNode(host)
                                .getStatus().getCapacity().getCpu();
                        System.out.println("cpuCore : " + cpuCore);
                        cpuPercentage.setValue(metric.getValue() / cpuCore);
                        result.add(cpuPercentage);
                    } else if ("memory_working_set".equals(metric.getMetric())) {
                        String host = metric.getTags().get(OpentsdbMetricsConst.HOST);
                        Node node = clusterInfo.getNode(metric.getTags().get(OpentsdbMetricsConst.HOST));
                        if (node == null) {
                            System.out.println("host : " + host + " not exist in clusterInfo");
                            return;
                        }
                        Metric memoryPercentage = new Metric();
                        memoryPercentage.setMetric("memory_ws_percent");
                        memoryPercentage.setTags(metric.getTags());
                        memoryPercentage.setTimestamp(metric.getTimestamp());
                        long memoryCap = node
                                .getStatus().getCapacity().getMemory();
                        memoryPercentage.setValue(metric.getValue() / memoryCap);
                        result.add(memoryPercentage);
                    }
                } else if ("pod".equals(metric.getType()) && Strings.isNotEmpty(metric.getTags().get(OpentsdbMetricsConst.PODNAME))
                        && podAlertingMetrics.contains(metric.getMetric())) {
                    result.add(metric);
                    if ("cpu_total".equals(metric.getMetric())) {
                        String podName = metric.getTags().get(OpentsdbMetricsConst.PODNAME);
                        String namespace = metric.getTags().get(OpentsdbMetricsConst.NAMESPACE);
                        if (clusterInfo.getPod(podName) == null) {
                            System.out.println(namespace + "\\" + podName + " not exist in clusterInfo");
                            return;
                        }
                        Metric cpuPercentage = new Metric();
                        cpuPercentage.setMetric("cpu_total_avg");
                        cpuPercentage.setTags(metric.getTags());
                        cpuPercentage.setTimestamp(metric.getTimestamp());

                        List<PodSpec.Container> containers = clusterInfo.getPod(metric.getTags().get(OpentsdbMetricsConst.PODNAME))
                                .getSpec().getContainers();
                        if (containers == null || containers.size() == 0) {
                            return;
                        }
                        float cpuLimit = containers.get(0).getResources().getLimits().getCpu();
                        cpuPercentage.setValue(metric.getValue() / cpuLimit);
                        result.add(cpuPercentage);
                    } else if ("memory_working_set".equals(metric.getMetric())) {
                        String podName = metric.getTags().get(OpentsdbMetricsConst.PODNAME);
                        String namespace = metric.getTags().get(OpentsdbMetricsConst.NAMESPACE);
                        if (clusterInfo.getPod(podName) == null) {
                            System.out.println(namespace + "\\" + podName + " not exist in clusterInfo");
                            return;
                        }

                        List<PodSpec.Container> containers = clusterInfo.getPod(podName)
                                .getSpec().getContainers();
                        if (containers == null || containers.size() == 0) {
                            return;
                        }
                        long memoryLimit = containers.get(0).getResources().getLimits().getMemory();

                        Metric memoryPercentage = new Metric();
                        memoryPercentage.setMetric("memory_ws_percent");
                        memoryPercentage.setTags(metric.getTags());
                        memoryPercentage.setTimestamp(metric.getTimestamp());
                        memoryPercentage.setValue(metric.getValue() / memoryLimit);
                        result.add(memoryPercentage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
