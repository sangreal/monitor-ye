
#!/usr/bin/env bash

IMAGEURL=10.19.248.12:29006/enncloud/enn-monitor-tracing:0.9.2.trace-SNAPSHOT

NAMESPACE=monitor-system-trace

LISTENPORT=8090
NODEPORT=29313

REPLICSNUM=1

CPUREQ="1"
CPULIMIT="2"

MEMREQ="1Gi"
MEMLIMIT="2Gi"
