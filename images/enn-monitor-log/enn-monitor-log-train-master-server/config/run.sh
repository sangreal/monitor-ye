#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms512m -Xmx1500m -cp /opt/enn-monitor-log-train-master-server.jar enn.monitor.log.train.master.server.EnnMonitorLogTrainMasterServer --workthread_num 16 --listen_port %LISTENPORT%
