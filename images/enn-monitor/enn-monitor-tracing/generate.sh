#!/usr/bin/env bash

# source ../../common_script/funcs.sh
# addr=$(getClusterAddr)

addr=${1}
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE ..." >&2
  exit 1
fi

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

# enn-monitor-trace-data-api
filePath=tmp/enn-monitor-tracing-deploy.yml
cp enn-monitor-tracing-deploy.yml $filePath
sed -i -e "s/%NAMESPACE%/$NAMESPACE/g" $filePath
sed -i -e "s/%REPLICSNUM%/$REPLICSNUM/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=tmp/enn-monitor-tracing-svc.yml
cp enn-monitor-tracing-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$NAMESPACE/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%NODEPORT%/$NODEPORT/g" $filePath

ennctl create -a enn-monitor-tracing -f tmp/enn-monitor-tracing-deploy.yml
ennctl create -a enn-monitor-tracing -f tmp/enn-monitor-tracing-svc.yml

rm -rf tmp

