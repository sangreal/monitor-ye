#!/usr/bin/env bash

IMAGEURL=10.19.248.12:29006/enncloud/elasticsearch-fluent-bit-0.12:0.10.2-RELEASE
K8SAPISERVER=https://10.19.248.200:6443
K8SUSERNAME=eWFuY2hlbmctcGFzc3dvcmQ=
K8SPWD=eWFuY2hlbmctdXNlcm5hbWU=

GATEWAYSERVER=enn-monitor-security-gateway-server.monitor-system-security
GATEWAYPORT=10000

CLUSTERNAME=yancheng

DOCKERLOGPATH=/data/docker

CPUREQ="100m"
CPULIMIT="1000m"

MEMREQ="1000Mi"
MEMLIMIT="2000Mi"
