#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1500m -cp /opt/enn-monitor-log-analyse-tester.jar enn.monitor.log.analyse.tester.server.EnnMonitorLogAnalyseTesterServer --listen_port %LISTENPORT% --enable_test --es_host elasticsearch-client.monitor-essential-service --es_port 9200 --mongoUrl mongodb://10.19.248.200:31018/EnnMonitorLogAnalyseStorage --dbName EnnMonitorLogAnalyseStorage
