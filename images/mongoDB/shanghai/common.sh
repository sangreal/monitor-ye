#!/usr/bin/env bash

IMAGEURL="127.0.0.1:29006/library/monitor-mongodb:0.8.0-RELEASE"
HOST=(10.19.137.141 10.19.137.142 10.19.137.143)
NUM=3

CPUREQ="200m"
CPULIMIT="1000m"

MEMREQ="500Mi"
MEMLIMIT="500Mi"