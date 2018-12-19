#!/usr/bin/env bash

IMAGEURL=10.19.248.12:29006/enncloud/enn-monitor-log-train-worker-server:0.10.30-RELEASE
NUM=16

MASTER_URL=enn-monitor-log-train-master-server
MASTER_PORT=10000

CPUREQ="500m"
CPULIMIT="25000m"

MEMREQ="2G"
MEMLIMIT="6G"
