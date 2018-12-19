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

filePath=$1/kibana-rc.yml
cp kibana-rc.yml $filePath
sed -i -e "s#%imageurl%#$IMAGEURL#g" $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/kibana-svc.yml
cp kibana-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

kubectl --namespace=$1 create configmap kibana-conf --from-file config/kibana.yml

#label_node kibana-$1 true $HOST

run_component $1

rm -rf $1/*-e

