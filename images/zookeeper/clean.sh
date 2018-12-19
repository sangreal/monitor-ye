#!/usr/bin/env bash

source ../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

clean_component $1
#unlabel_node zk-$1 $HOST

for ((i=1;i<=$NUM;i++)); do
  kubectl --namespace=$1 delete configmap zookeeper$i-config
done
