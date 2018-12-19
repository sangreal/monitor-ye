#!/usr/bin/env bash

#IMAGEURL=127.0.0.1:29006/library/enn-monitor-security-gateway-web:0.8.4-RELEASE
IMAGEURL=10.19.140.200:29006/library/enn-monitor-security-gateway-web:0.10.1-SNAPSHOT
NUM=1

LISTENPORT=10000
NODEPORT=29307

CPUREQ="200m"
CPULIMIT="1000m"

MEMREQ="512Mi"
MEMLIMIT="1Gi"
