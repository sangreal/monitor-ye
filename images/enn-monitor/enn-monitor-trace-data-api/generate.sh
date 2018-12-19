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

# enn-monitor-trace-data-api
filePath=$1/enn-monitor-trace-data-api-deploy.yml
cp enn-monitor-trace-data-api-deploy.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/enn-monitor-trace-data-api-svc.yml
cp enn-monitor-trace-data-api-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%PORT%/$NODEPORT/g" $filePath

ennctl -n $1 create app enn-monitor-trace-data-api
ennctl create -a enn-monitor-trace-data-api -f $1/enn-monitor-trace-data-api-deploy.yml
ennctl create -a enn-monitor-trace-data-api -f $1/enn-monitor-trace-data-api-svc.yml

rm -rf $1/*-e

