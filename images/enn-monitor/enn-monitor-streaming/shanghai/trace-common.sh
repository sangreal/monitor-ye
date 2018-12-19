#!/usr/bin/env bash

CPU_LIMIT_TRACE=1
MEM_LIMIT_TRACE=2Gi
CPU_REQ_TRACE=500m
MEM_REQ_TRACE=2Gi

IMAGEURL=10.19.140.200:29006/enncloud/monitor-streaming-jobs:0.9.0.release

CEPH_PATH=/opt/ceph
WORKSPACE=/opt/workspace
JOB_JAR=enn-monitor-trace-streaming-0.10.0-SNAPSHOT.jar
JOB_SH=start-streaming-trace.sh
SPARK_MASTER=spark://pre2-master1.monitor-essential-service:7077,pre2-master2.monitor-essential-service:7077,pre2-master3.monitor-essential-service:7077
MAIN_CLASS=enn.monitor.trace.streaming.Collector
TOTAL_CORES=3
EXECUTOR_MEM=2g
EXECUTOR_CORES=1
MAX_RATE=500
KAFKA=pre2-kafka1.monitor-essential-service:9092,pre2-kafka2.monitor-essential-service:9092,pre2-kafka3.monitor-essential-service:9092
GROUP_ID=etl1
ESCLUSTERNAME="es-log"
ES_REST=elasticsearch-client.monitor-essential-service:9200
ES_TCP=elasticsearch-client.monitor-essential-service:9300

