package enn.monitor.metric.kubelet.streaming.util;

import enn.monitor.framework.common.constval.OpentsdbMetricsConst;
import enn.monitor.framework.metrics.kubelet.proto.*;
import enn.monitor.streaming.common.proto.Metric;
import org.apache.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weize on 17-9-5.
 */
public class MetricsConvertor {
//    private static final String namespaceLabel = "io.kubernetes.pod.namespace";
//    private static final String podNameLabel = "io.kubernetes.pod.name";
//    private static final String appNameLabel = "io.kubernetes.container.name";
    private static Logger logger = Logger.getRootLogger();
    private static final Map<Long, String> DISK_MAJOR = new HashMap<>();
    static {
        DISK_MAJOR.put(8l, "scsi_0_15");
        DISK_MAJOR.put(65l, "scsi_16_31");
        DISK_MAJOR.put(66l, "scsi_32_47");
        DISK_MAJOR.put(202l, "aws");
//        DISK_MAJOR.put(253l, "alibaba");
    }

    public static List<Metric> convertor(ContainerInfo containerInfo, String token) throws Exception {
//        String namespace;
//        String podName;
//        String appName;
        
        String parts[];

        Map<String, String> tagsMap = new HashMap<>();

        ContainerStats containerStats;

        List<Metric> metricsList = new ArrayList<>();

        //2017-01-10T11:11:56.717580367+08:00
        String dateTime = containerInfo.getContainer_stats().getTimestamp();

        if (dateTime == null || dateTime.trim().equals("") == true) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        long dateTimeLong = 0;
        try {
            ZonedDateTime date = ZonedDateTime.parse(dateTime, formatter);
            dateTimeLong = date.toEpochSecond() * 1000 + date.getNano() / 1000000;
        } catch (Exception e) {
            logger.warn("parse date of " + containerInfo.getHostIp() + containerInfo.getContainer_Name()
                    + " failed : " + dateTime);
            return null;
        }

        containerStats = containerInfo.getContainer_stats();
        if (containerStats == null) {
            return null;
        }

        if (containerStats.getClustername() != null && containerStats.getClustername().trim().equals("") == false) {
            tagsMap.put(OpentsdbMetricsConst.CLUSTERNAME, containerStats.getClustername());
        }

        if (containerInfo.getHostIp() != null && containerInfo.getHostIp().trim().equals("") == false) {
            tagsMap.put(OpentsdbMetricsConst.HOST, containerInfo.getHostIp());
        }

        // k8s_POD_ipproxypool-f5d9f8d7d-wb6tf_patricktest_fd0063cf-8be0-11e8-8e47-1866da1a2629_0
        // k8s_ipproxypool_ipproxypool-f5d9f8d7d-wb6tf_patricktest_fd0063cf-8be0-11e8-8e47-1866da1a2629_0
        if (containerInfo.getContainer_Name() != null && containerInfo.getContainer_Name().trim().equals("") == false) {
        	parts = containerInfo.getContainer_Name().trim().split("_");
        	if (parts.length > 0) {
        		if (parts[0] != null && parts[0].equals("k8s") == true) {
        			if (parts.length == 6 || parts.length == 7) {
        				tagsMap.put(OpentsdbMetricsConst.NAMESPACE, parts[3]);
        				tagsMap.put(OpentsdbMetricsConst.PODNAME, parts[2]);
                	}
        		}
        	}
        	
        	tagsMap.put(OpentsdbMetricsConst.CONTAINERNAME, containerInfo.getContainer_Name().replace("\\", "-").replace(":", "_").replace("@", "_"));
        }

//        if (containerInfo.getContainer_labels() != null) {
//            namespace = containerInfo.getContainer_labels().get(namespaceLabel);
//            if (namespace != null && namespace.trim().equals("") == false) {
//                tagsMap.put(OpentsdbMetricsConst.NAMESPACE, namespace);
//            }
//
//            podName = containerInfo.getContainer_labels().get(podNameLabel);
//            if (podName != null && podName.trim().equals("") == false) {
//                tagsMap.put(OpentsdbMetricsConst.PODNAME, podName);
//            }
//
//            appName = containerInfo.getContainer_labels().get(appNameLabel);
//            if (appName != null && appName.trim().equals("") == false) {
//                tagsMap.put(OpentsdbMetricsConst.APPNAME, appName);
//            }
//        }

//        tagsMap.put(OpentsdbMetricsConst.TYPE, OpentsdbMetricsConst.TYPE_SYSTEM);
//        tagsMap.put(OpentsdbMetricsConst.TOKEN, token);

        metricsList.addAll(convertorCpu(dateTimeLong, containerInfo.getContainer_stats().getCpu(), tagsMap));
        metricsList.addAll(convertorMemory(dateTimeLong, containerInfo.getContainer_stats().getMemory(), tagsMap));
        metricsList.addAll(convertorDiskIo(dateTimeLong, containerInfo.getContainer_stats().getDiskio(), tagsMap));
        metricsList.addAll(convertorFileSystem(dateTimeLong, containerInfo.getContainer_stats().getFilesystem(), tagsMap));
        metricsList.addAll(convertorDiskQuota(dateTimeLong, containerInfo.getContainer_stats().getDiskquota(), tagsMap));
        metricsList.addAll(convertorNetworkStats(dateTimeLong, containerInfo.getContainer_stats().getNetwork(), tagsMap));
        metricsList.addAll(convertorGpu(dateTimeLong, containerInfo.getContainer_stats().getAccelerators(), tagsMap));

        return metricsList;
    }

    private static List<Metric> convertorGpu(long dateTimeLong, GpuStats[] gpuStats, Map<String, String> tagsMap) throws Exception {
        List<Metric> gpuList = new ArrayList<Metric>();

        if (gpuStats == null)
            return gpuList;

        Map<String, String> gpusMap = null;
        Metric opentsdbMetrics = null;
        String gpuId = "gpu";

        for (GpuStats item : gpuStats) {
            gpusMap = new HashMap<>(tagsMap);
            gpusMap.put(OpentsdbMetricsConst.GPU_NAME, item.getMake() + "_" + item.getModel().replace(" ", "-"));
            gpusMap.put(OpentsdbMetricsConst.GPU_NO, item.getId());

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric(gpuId + "_memory_capacity");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getMemory_total());
            opentsdbMetrics.setTags(gpusMap);
            gpuList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric(gpuId + "_memory_usage");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getMemory_used());
            opentsdbMetrics.setTags(gpusMap);
            gpuList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric(gpuId + "_util");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getDuty_cycle());
            opentsdbMetrics.setTags(gpusMap);
            gpuList.add(opentsdbMetrics);
        }

        return gpuList;
    }

    private static List<Metric> convertorCpu(long dateTimeLong, CpuStats cpuStats, Map<String, String> tagsMap) throws Exception {
        Metric opentsdbMetrics = null;

        Map<String, String> coresMap = null;

        CpuUsage cpuUsage = null;

        List<Metric> cpuList = new ArrayList<Metric>();

        if (cpuStats == null || cpuStats.getUsage() == null) {
            return cpuList;
        }

        cpuUsage = cpuStats.getUsage();

        // cpu total
        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("cpu_total");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(cpuUsage.getTotal() / 1000000000.0);
        opentsdbMetrics.setTags(tagsMap);
        cpuList.add(opentsdbMetrics);

        // cpu
        if (cpuUsage.getPer_cpu_usage() != null) {
            for (int i = 0; i < cpuUsage.getPer_cpu_usage().length; ++i) {
                coresMap = new HashMap<>(tagsMap);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("cpu_core");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(cpuUsage.getPer_cpu_usage()[i] / 1000000000.0);
                coresMap.put(OpentsdbMetricsConst.CPU_CORE, "" + i);
                opentsdbMetrics.setTags(coresMap);
                cpuList.add(opentsdbMetrics);
            }
        }

        // system
        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("cpu_system");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(cpuUsage.getSystem() / 1000000000.0);
        opentsdbMetrics.setTags(tagsMap);
        cpuList.add(opentsdbMetrics);

        // user
        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("cpu_user");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(cpuUsage.getUser() / 1000000000.0);
        opentsdbMetrics.setTags(tagsMap);
        cpuList.add(opentsdbMetrics);

        return cpuList;
    }

    private static List<Metric> convertorMemory(long dateTimeLong, MemoryStats memoryStats, Map<String, String> tagsMap) {
        Metric opentsdbMetrics = null;

        List<Metric> memoryList = new ArrayList<Metric>();

        if (memoryStats == null) {
            return memoryList;
        }

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("memory_usage");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(memoryStats.getUsage());
        opentsdbMetrics.setTags(tagsMap);
        memoryList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("memory_working_set");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(memoryStats.getWorking_set());
        opentsdbMetrics.setTags(tagsMap);
        memoryList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("memory_cache");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(memoryStats.getCache());
        opentsdbMetrics.setTags(tagsMap);
        memoryList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("memory_rss");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(memoryStats.getRss());
        opentsdbMetrics.setTags(tagsMap);
        memoryList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("memory_swap");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(memoryStats.getSwap());
        opentsdbMetrics.setTags(tagsMap);
        memoryList.add(opentsdbMetrics);

        return memoryList;
    }
	
	/*
	 8 block        scsi 磁盘(0-15)
                 0 = /dev/sda                第1个 scsi 磁盘(整个磁盘)
                16 = /dev/sdb                第2个 scsi 磁盘(整个磁盘)
                32 = /dev/sdc                第3个 scsi 磁盘(整个磁盘)
                   ...
               240 = /dev/sdp                第16个 scsi 磁盘(整个磁盘)
               分区表示方法如下(以第3个 scsi 磁盘为例)
                33 = /dev/sdc1                第1个分区
                34 = /dev/sdc2                第2个分区
                   ...
                47 = /dev/sdc15        第15个分区
               对于linux/i386来说，分区1-4是主分区，5-15是逻辑分区。

               参考： http://blog.sina.com.cn/s/blog_707dde150102wp10.html

     对于阿里云(253)和aws(202)，情况比较特殊
	 */

    private static void addDiskIoMetrics(List<Metric> diskIoList,
                                         PerDiskStats[] diskIO, long dateTimeLong, DiskIoStats diskIoStats,
                                         Map<String, String> tagsMap, String tags) {
        Metric opentsdbMetrics = null;
        Map<String, Long> statsMap = null;
        Map<String, String> devTagsMap = null;

        for (PerDiskStats item : diskIO) {
            if (!DISK_MAJOR.containsKey(item.getMajor())) {
                continue;
            }

            if (item.getMinor() % 16 != 0) {
                continue;
            }

            statsMap = item.getStats();
            if (statsMap == null) {
                continue;
            }

            for (Map.Entry<String, Long> entry : statsMap.entrySet()) {
                devTagsMap = new HashMap<>(tagsMap);
                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric(tags + "_" + entry.getKey());
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(entry.getValue());
                devTagsMap.put(OpentsdbMetricsConst.DEVNO, "" + item.getMinor() / 16);
                opentsdbMetrics.setTags(devTagsMap);
                diskIoList.add(opentsdbMetrics);
            }
        }
    }

    private static List<Metric> convertorDiskIo(long dateTimeLong, DiskIoStats diskIoStats, Map<String, String> tagsMap) {
        PerDiskStats[] diskIO = null;
        List<Metric> diskIoList = new ArrayList<Metric>();

        diskIO = diskIoStats.getIo_service_bytes();
        if (diskIO != null) {
            addDiskIoMetrics(diskIoList, diskIO, dateTimeLong, diskIoStats, tagsMap, "diskio_bytes");
        }

        diskIO = diskIoStats.getIo_serviced();
        if (diskIO != null) {
            addDiskIoMetrics(diskIoList, diskIO, dateTimeLong, diskIoStats, tagsMap, "diskio_pkgs");
        }

        diskIO = diskIoStats.getIo_wait_time();
        if (diskIO != null) {
            addDiskIoMetrics(diskIoList, diskIO, dateTimeLong, diskIoStats, tagsMap, "diskio_wait_time");
        }

        return diskIoList;
    }

    private static List<Metric> convertorFileSystem(long dateTimeLong, FsStats[] fsStats, Map<String, String> tagsMap) {
        Metric opentsdbMetrics = null;
        Map<String, String> fsMap = null;

        List<Metric> fileSystemList = new ArrayList<Metric>();

        if (fsStats == null) {
            return fileSystemList;
        }

        for (FsStats item : fsStats) {
            fsMap = new HashMap<>(tagsMap);
            fsMap.put(OpentsdbMetricsConst.DEVFS, item.getDevice().replace("\\", "-").replace(":", "_"));

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_usage");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getUsage());
            opentsdbMetrics.setTags(fsMap);
            fileSystemList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_capacity");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getCapacity());
            opentsdbMetrics.setTags(fsMap);
            fileSystemList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_available");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(item.getAvailable());
            opentsdbMetrics.setTags(fsMap);
            fileSystemList.add(opentsdbMetrics);
        }

        return fileSystemList;
    }

    private static List<Metric> convertorDiskQuota(long dateTimeLong, DiskQuota diskQuota, Map<String, String> tagsMap) {
        List<Metric> diskQuotaList = new ArrayList<Metric>();

        Metric opentsdbMetrics = null;
        Map<String, String> xfsMap = null;

        if (diskQuota == null || diskQuota.getNumofquotadisk() <= 0) {
            return diskQuotaList;
        }

        xfsMap = new HashMap<>(tagsMap);
        xfsMap.put(OpentsdbMetricsConst.DEVXFS, OpentsdbMetricsConst.DEVXFSVALUE);
        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("disk_quato_capacity");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(diskQuota.getCapacity());
        opentsdbMetrics.setTags(xfsMap);
        diskQuotaList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("disk_quato_curusesize");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(diskQuota.getCurusesize());
        opentsdbMetrics.setTags(xfsMap);
        diskQuotaList.add(opentsdbMetrics);

        opentsdbMetrics = new Metric();
        opentsdbMetrics.setMetric("disk_quato_curquotasize");
        opentsdbMetrics.setTimestamp(dateTimeLong);
        opentsdbMetrics.setValue(diskQuota.getCurquotasize());
        opentsdbMetrics.setTags(xfsMap);
        diskQuotaList.add(opentsdbMetrics);

        for (DiskQuotaStatus diskQuotaStatus : diskQuota.getDiskstatus()) {
            xfsMap = new HashMap<>(tagsMap);
            xfsMap.put(OpentsdbMetricsConst.DEVXFS, diskQuotaStatus.getMountpath());

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_quato_capacity");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(diskQuotaStatus.getCapacity());
            opentsdbMetrics.setTags(xfsMap);
            diskQuotaList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_quato_curusesize");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(diskQuotaStatus.getCurusesize());
            opentsdbMetrics.setTags(xfsMap);
            diskQuotaList.add(opentsdbMetrics);

            opentsdbMetrics = new Metric();
            opentsdbMetrics.setMetric("disk_quato_curquotasize");
            opentsdbMetrics.setTimestamp(dateTimeLong);
            opentsdbMetrics.setValue(diskQuotaStatus.getCurquotasize());
            opentsdbMetrics.setTags(xfsMap);
            diskQuotaList.add(opentsdbMetrics);
        }

        return diskQuotaList;
    }

    private static List<Metric> convertorNetworkStats(long dateTimeLong, NetworkStats networkStats, Map<String, String> tagsMap) {
        InterfaceStats[] interfaceStats = null;
        Metric opentsdbMetrics = null;
        Map<String, String> networkMap = null;

        List<Metric> networkList = new ArrayList<Metric>();

        interfaceStats = networkStats.getInterfaces();
        if (interfaceStats != null) {
            for (InterfaceStats item : interfaceStats) {
                networkMap = new HashMap<>(tagsMap);
                networkMap.put(OpentsdbMetricsConst.DEVNET, item.getName().replace("\\", "-").replace(":", "_"));

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_rcv_bytes");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getRx_bytes());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_rcv_packets");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getRx_packets());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_rcv_errors");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getRx_errors());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_rcv_dropped");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getRx_dropped());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_snd_bytes");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getTx_bytes());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_snd_packets");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getTx_packets());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_snd_errors");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getTx_errors());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);

                opentsdbMetrics = new Metric();
                opentsdbMetrics.setMetric("network_snd_dropped");
                opentsdbMetrics.setTimestamp(dateTimeLong);
                opentsdbMetrics.setValue(item.getTx_dropped());
                opentsdbMetrics.setTags(networkMap);
                networkList.add(opentsdbMetrics);
            }
        }

        return networkList;
    }
}
