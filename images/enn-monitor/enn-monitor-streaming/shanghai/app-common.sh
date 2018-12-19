#!/usr/bin/env bash

CPU_LIMIT_APP=1
MEM_LIMIT_APP=2Gi
CPU_REQ_APP=300m
MEM_REQ_APP=1Gi

IMAGEURL=10.19.140.200:29006/enncloud/monitor-streaming-jobs:0.9.0.release

CEPH_PATH=/opt/ceph
WORKSPACE=/opt/workspace
JOB_JAR=enn-monitor-metric-app-streaming-0.10.0-SNAPSHOT.jar
JOB_SH=start-etl-app_1.sh
MAIN_CLASS=enn.monitor.metric.app.streaming.Collector
SPARK_MASTER=spark://pre2-master1.monitor-essential-service:7077,pre2-master2.monitor-essential-service:7077,pre2-master3.monitor-essential-service:7077
TOTAL_CORES=2
EXECUTOR_MEM=1g
EXECUTOR_CORES=1
MAX_RATE=500
KAFKA=pre2-kafka1.monitor-essential-service:9092,pre2-kafka2.monitor-essential-service:9092,pre2-kafka3.monitor-essential-service:9092
TOPIC_NAME=monitor-app
GROUP_ID=etl2
OPENTSDB=monitoring-opentsdb.monitor-essential-service:4242
