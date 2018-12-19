#!/usr/bin/env bash

source ../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE..." >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

filePath=$1/opentsdb-rc.yml
cp opentsdb-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%HOST_LABEL%/opentsdb-$1/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/opentsdb-svc.yml
cp opentsdb-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%PORT%/$PORT/g" $filePath

filePath=$1/logback.xml
cp config/logback.xml $filePath

filePath=$1/opentsdb.conf
cp config/opentsdb.conf $filePath

kubectl --namespace=$1 create configmap opentsdb-conf --from-file $1/logback.xml --from-file $1/opentsdb.conf

#label_node opentsdb-$1 true $HOST
run_component $1

rm -rf $1/*-e

