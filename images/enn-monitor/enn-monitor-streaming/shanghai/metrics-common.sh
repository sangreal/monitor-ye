#!/usr/bin/env bash

CPU_LIMIT_METRIC=1
MEM_LIMIT_METRIC=3Gi
CPU_REQ_METRIC=500m
MEM_REQ_METRIC=2Gi

IMAGEURL=10.19.140.200:29006/enncloud/monitor-streaming-jobs:0.9.0.release

CEPH_PATH=/opt/ceph
WORKSPACE=/opt/workspace
JOB_JAR=enn-monitor-metric-kubelet-streaming-0.10.0-SNAPSHOT.jar
JOB_SH=start-etl-metrics_1.sh
SPARK_MASTER=spark://pre2-master1.monitor-essential-service:7077,pre2-master2.monitor-essential-service:7077,pre2-master3.monitor-essential-service:7077
MAIN_CLASS=enn.monitor.metric.kubelet.streaming.Collector
TOTAL_CORES=3
EXECUTOR_MEM=1g
EXECUTOR_CORES=1
MAX_RATE=500
KAFKA=pre2-kafka1.monitor-essential-service:9092,pre2-kafka2.monitor-essential-service:9092,pre2-kafka3.monitor-essential-service:9092
OPENTSDB_URL=monitoring-opentsdb.monitor-essential-service:4242
TOPIC_NAME=k8s-kubelet
APIUSER=zhtsC1002
APISERVER=10.19.140.200:6443
APIPASS=zhtsC1002
ASYNC="off"
GROUP_ID=etl-metrics
RDD_PARTITION=0
PROM_PUSH=prometheus-pushgateway.monitor-system-alert:27091