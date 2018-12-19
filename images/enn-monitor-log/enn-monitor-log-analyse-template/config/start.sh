#!/usr/bin/env bash
/opt/spark-2.1.0-bin-hadoop2.7/bin/spark-submit \
    --master spark://pre1-master1.monitor-essential-service:7077,pre1-master2.monitor-essential-service:7077,pre1-master3.monitor-essential-service:7077 \
    --class enn.monitor.log.analyse.template.server.EnnMonitorLogAnalyseTemplateServer  \
    --total-executor-cores 45  \
    --conf "spark.executor.cores=15" \
    --conf "spark.executor.memory=4g" \
    --conf "spark.executor.extraJavaOptions=-XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=2" \
    /opt/enn-monitor-log-analyse-template.jar  \
    --listen_port 10000  \
    --enable_metrics  \
    --gatewayHost "10.19.248.200"  \
    --token "CD13213D0AFC1B3DB59705F2F51583D1"  \
    --gatewayPort 30112  \
    --enable_client \
    --es_host "elasticsearch-client.monitor-essential-service"  \
    --es_port "9200" \
    --config_gateway_host "10.19.248.200"  \
    --config_gateway_port "29319"
