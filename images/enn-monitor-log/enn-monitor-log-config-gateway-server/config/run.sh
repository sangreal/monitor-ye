#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1000m -cp /opt/enn-monitor-log-config-gateway-server.jar enn.monitor.log.config.gateway.server.EnnMonitorLogConfigGatewayServer --listen_port %LISTENPORT% --config_host 10.19.248.200 --config_port 29415
