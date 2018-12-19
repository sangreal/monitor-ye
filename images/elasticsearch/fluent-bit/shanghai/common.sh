#!/usr/bin/env bash

IMAGEURL=10.19.140.200:29006/library/elasticsearch-fluent-bit-0.12:0.10.1-SNAPSHOT
K8SAPISERVER=https://10.19.140.200:6443
K8SUSERNAME=zhtsC1002
K8SPWD=zhtsC1002

GATEWAYSERVER=enn-monitor-security-gateway-server.monitor-system-security
GATEWAYPORT=10000

CLUSTERNAME=shanghai

DOCKERLOGPATH=/data/docker

CPUREQ="100m"
CPULIMIT="1000m"

MEMREQ="300Mi"
MEMLIMIT="2Gi"
