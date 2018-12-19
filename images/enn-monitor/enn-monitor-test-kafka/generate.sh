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
filePath=$1/enn-monitor-test-kafka-deploy.yml
cp enn-monitor-test-kafka-deploy.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM_PRODUCER%/$REPLICSNUM_PRODUCER/g" $filePath
sed -i -e "s/%REPLICSNUM_CONSUMER%/$REPLICSNUM_CONSUMER/g" $filePath
sed -i -e "s/%LISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL_PRODUCER%#$IMAGEURL_PRODUCER#g" $filePath
sed -i -e "s#%IMAGEURL_CONSUMER%#$IMAGEURL_CONSUMER#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

ennctl -n $1 create app enn-monitor-test-kafka
ennctl create -a enn-monitor-test-kafka -f $1/enn-monitor-test-kafka-deploy.yml

rm -rf $1/*-e

