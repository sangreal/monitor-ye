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

filePath=$1/enn-monitor-log-analyse-storage-server-rc.yml
cp enn-monitor-log-analyse-storage-server-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath

filePath=$1/run.sh
cp config/run.sh $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%KAFKAURL%/$KAFKAURL/g" $filePath
sed -i -e "s/%CONFIGIP%/$CONFIGIP/g" $filePath
sed -i -e "s/%CONFIGPORT%/$CONFIGPORT/g" $filePath

filePath=$1/enn-monitor-log-analyse-storage-server-svc.yml
cp enn-monitor-log-analyse-storage-server-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%EXTERNPORT%/$EXTERNPORT/g" $filePath

kubectl -n $1 create configmap log-analyse-storage-conf --from-file $1/run.sh

run_component $1

rm -rf $1/*-e

