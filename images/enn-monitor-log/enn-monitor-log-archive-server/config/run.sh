#!/bin/bash

cp /opt/conf/monitor.properties .

java -cp /opt/enn-monitor-log-archive-server.jar enn.monitor.log.archive.server.EnnMonitorLogArchiveServer --listen_port 10000 --mongoUrl mongodb://mongo1.monitor-essential-service:27017,mongo2.monitor-essential-service:27017,mongo3.monitor-essential-service:27017/EnnMonitorLogArchive --dbName EnnMonitorLogArchive --es_url elasticsearch-client.monitor-essential-service:9300 --es_cluster_name es-log
