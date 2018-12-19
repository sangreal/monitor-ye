#!/usr/bin/env bash
/opt/spark-2.1.0-bin-hadoop2.7/bin/spark-submit \
    --master spark://pre1-master1.monitor-essential-service:7077,pre1-master2.monitor-essential-service:7077,pre1-master3.monitor-essential-service:7077 \
    --class enn.monitor.metric.app.streaming.server.EnnMonitorMetricAppServer  \
    --total-executor-cores 6  \
    --conf "spark.streaming.receiver.maxRate=100000" \
    --conf "spark.executor.cores=2" \
    --conf "spark.executor.memory=4g" \
    --conf "spark.executor.extraJavaOptions=-XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=2" \
    /opt/enn-monitor-metric-app-streaming.jar  \
    --env "online"  \
    --kafkaBootstrap "pre2-kafka1.monitor-essential-service:9092,pre2-kafka2.monitor-essential-service:9092,pre2-kafka3.monitor-essential-service:9092" \
    --appTopic "monitor-app" \
    --groupid "micklongen-app" \
    --opentsdbUrl "monitoring-opentsdb.monitor-essential-service:4242" \
    --enable_metrics  \
    --gatewayHost "enn-monitor-security-gateway-web.monitor-system-security"  \
    --token "CA4B7757AEEAA7EA547776653906EA42"  \
    --gatewayPort 10000
