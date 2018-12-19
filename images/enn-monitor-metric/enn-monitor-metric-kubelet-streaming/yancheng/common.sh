#!/usr/bin/env bash

IMAGEURL=10.19.248.12:29006/enncloud/enn-monitor-metric-kubelet-streaming:0.10.2-RELEASE
NUM=1

LISTENPORT=4040
EXTERNPORT=29042

CPUREQ="500m"
CPULIMIT="2000m"

MEMREQ="1G"
MEMLIMIT="2G"

