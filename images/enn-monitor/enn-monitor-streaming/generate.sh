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

# streaming-trace
filePath=$1/streaming-trace.yml
cp streaming-trace.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%KAFKA_ZK%/$KAFKA_ZK/g" $filePath

filePath=$1/streaming-trace-storage.yml
cp streaming-trace-storage $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

ennctl create app streaming-jobs -a $1
ennctl create -a streaming-jobs -f $1/streaming-trace-storage.yml
ennctl create -a streaming-jobs -f $1/streaming-trace.yml

rm -rf $1/*-e

