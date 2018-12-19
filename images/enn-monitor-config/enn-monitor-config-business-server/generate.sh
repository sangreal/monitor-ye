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

filePath=$1/enn-monitor-config-business-server-rc.yml
cp enn-monitor-config-business-server-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/enn-monitor-config-business-server-svc.yml
cp enn-monitor-config-business-server-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%PORT%/$NODEPORT/g" $filePath

run_component $1

rm -rf $1/*-e

