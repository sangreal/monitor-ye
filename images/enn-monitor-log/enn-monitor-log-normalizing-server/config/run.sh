#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1500m -cp /opt/enn-monitor-log-normalizing-server.jar enn.monitor.log.normalizing.server.EnnMonitorLogNormalizingServer --workThreadNum 16 --listen_port %LISTENPORT% --kafka_url %KAFKAURL% --group_id micklongen --from_topic monitor-log --to_topic_normal monitor-log-normal --to_topic_origin monitor-log-origin --log_config_ip %CONFIGIP% --log_config_port %CONFIGPORT%
