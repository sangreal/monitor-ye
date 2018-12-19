#!/bin/bash

chmod 777 /data/grafana

sysv-rc-conf grafana-server on
service grafana-server start

tail -f /var/log/grafana/grafana.log
