#!/usr/bin/env bash

IMAGEURL=10.38.240.34:29006/enncloud/elasticsearch-fluent-bit-0.12:0.8.2-RELEASE
K8SAPISERVER=https://10.38.240.28:6443
K8SUSERNAME=admin
K8SPWD=admin

GATEWAYSERVER=enn-monitor-security-gateway-server.monitor-system-security
GATEWAYPORT=10000

CLUSTERNAME=langfang-ecej

DOCKERLOGPATH=/data/docker/containers

CPUREQ="500m"
CPULIMIT="1500m"

MEMREQ="500Mi"
MEMLIMIT="10000Mi"
