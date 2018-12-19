#!/usr/bin/env bash

source ../../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE ..." >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

# enn-monitor-log-analyse-template
filePath=$1/enn-monitor-log-analyse-template-rc.yml
cp enn-monitor-log-analyse-template-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s/%SPARKLISTENPORT%/$SPARKLISTENPORT/g" $filePath
sed -i -e "s/%SVRLISTENPORT%/$SVRLISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/enn-monitor-log-analyse-template-svc.yml
cp enn-monitor-log-analyse-template-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%SPARKLISTENPORT%/$SPARKLISTENPORT/g" $filePath
sed -i -e "s/%SPARKEXTERNPORT%/$SPARKEXTERNPORT/g" $filePath
sed -i -e "s/%SVRLISTENPORT%/$SVRLISTENPORT/g" $filePath
sed -i -e "s/%SVREXTERNPORT%/$SVREXTERNPORT/g" $filePath

kubectl --namespace=$1 create configmap log-template-conf --from-file config/start.sh

run_component $1

rm -rf $1/*-e

