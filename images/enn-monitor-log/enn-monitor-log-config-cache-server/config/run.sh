#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1500m -cp /opt/enn-monitor-log-config-cache-server.jar enn.monitor.log.config.cache.server.EnnMonitorLogConfigCacheServer --workThreadNum 16 --listen_port %LISTENPORT% --log_config_ip %CONFIGIP% --log_config_port %CONFIGPORT%
