#!/usr/bin/env bash
/opt/spark-2.1.0-bin-hadoop2.7/bin/spark-submit \
    --master spark://pre1-master1.monitor-essential-service:7077,pre1-master2.monitor-essential-service:7077,pre1-master3.monitor-essential-service:7077 \
    --class enn.monitor.metric.kubelet.streaming.server.EnnMonitorMetricKubeletServer  \
    --total-executor-cores 6  \
    --conf "spark.streaming.receiver.maxRate=100000" \
    --conf "spark.executor.cores=2" \
    --conf "spark.executor.memory=4g" \
    --conf "spark.executor.extraJavaOptions=-XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=2" \
    /opt/enn-monitor-metric-kubelet-streaming.jar  \
    --env "online"  \
    --batchInterval "1" \
    --kafkaBootstrap "pre2-kafka1.monitor-essential-service:9092,pre2-kafka2.monitor-essential-service:9092,pre2-kafka3.monitor-essential-service:9092" \
    --metricsTopic "k8s-kubelet" \
    --groupid "micklongen-kubelet" \
    --opentsdbUrl "monitoring-opentsdb.monitor-essential-service:4242" \
    --prometheusPushUrl "prometheus-pushgateway.monitor-system-alert:27091" \
    --apiuser "eWFuY2hlbmctcGFzc3dvcmQ=" \
    --apiserver "10.19.248.200:6443" \
    --apipass "eWFuY2hlbmctdXNlcm5hbWU=" \
    --async "off"   \
    --enable_metrics  \
    --gatewayHost "enn-monitor-security-gateway-web.monitor-system-security"  \
    --token "4B2603051E64250C2DC528B325AA242A"  \
    --gatewayPort 10000
