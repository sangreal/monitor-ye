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

# enn-monitor-metric-kubelet-streaming
filePath=$1/enn-monitor-metric-kubelet-streaming-rc.yml
cp enn-monitor-metric-kubelet-streaming-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/enn-monitor-metric-kubelet-streaming-svc.yml
cp enn-monitor-metric-kubelet-streaming-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%EXTERNPORT%/$EXTERNPORT/g" $filePath

kubectl --namespace=$1 create configmap metric-kubelet-streaming-conf --from-file config/start.sh

run_component $1

rm -rf $1/*-e

