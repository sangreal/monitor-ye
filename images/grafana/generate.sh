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

filePath=$1/grafana-rc.yml
cp grafana-rc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/grafana-svc.yml
cp grafana-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

filePath=$1/grafana.ini
cp config/grafana.ini $filePath

kubectl --namespace=$1 create configmap grafana-conf --from-file $1/grafana.ini

run_component $1

rm -rf $1/*-e

