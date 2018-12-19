#!/bin/bash

cp /opt/conf/monitor.properties .

java -Xms1024m -Xmx3500m -cp /opt/enn-monitor-log-train-worker-server.jar enn.monitor.log.train.worker.server.EnnMonitorLogTrainWorkerServer --master_url %MASTER_URL% --master_port %MASTER_PORT%
