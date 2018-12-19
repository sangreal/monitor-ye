#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1000m -cp /opt/enn-monitor-log-config-server.jar enn.monitor.log.config.server.EnnMonitorLogConfigServer --workThreadNum 16 --listen_port %LISTENPORT% --mongoUrl mongodb://mongo1.monitor-essential-service:27017,mongo2.monitor-essential-service:27017,mongo3.monitor-essential-service:27017/EnnMonitorLogConfig --dbName EnnMonitorLogConfig
