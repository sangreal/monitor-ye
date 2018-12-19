#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1500m -cp /opt/enn-monitor-log-analyse-storage-server.jar enn.monitor.log.analyse.storage.server.EnnMonitorLogAnalyseStorageServer --workThreadNum 16 --listen_port %LISTENPORT% --mongoUrl mongodb://mongo1.monitor-essential-service:27017,mongo2.monitor-essential-service:27017,mongo3.monitor-essential-service:27017/EnnMonitorLogAnalyseStorage --dbName EnnMonitorLogAnalyseStorage
