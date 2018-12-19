#!/usr/bin/env bash

source ../../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

clean_component $1

kubectl -n $1 delete configmap log-normalizing-conf
