#!/usr/bin/env bash

source ../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

for ((i=1;i<=$NUM;i++)); do
  kubectl --namespace=$1 delete configmap kafka$i-config
done

clean_component $1
