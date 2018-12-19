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

filePath=$1/es-fluent-rc.yml
cp es-fluent-rc.yml $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s#%KAFKAURL%#$KAFKAURL#g" $filePath
sed -i -e "s#%GATEWAYSERVER%#$GATEWAYSERVER#g" $filePath
sed -i -e "s#%GATEWAYPORT%#$GATEWAYPORT#g" $filePath
sed -i -e "s#%APIURL%#$K8SAPISERVER#g" $filePath
sed -i -e "s/%CLUSTERNAME%/$CLUSTERNAME/g" $filePath
sed -i -e "s#%DOCKERLOGPATH%#$DOCKERLOGPATH#g" $filePath
sed -i -e "s/%USERNAME%/$K8SUSERNAME/g" $filePath
sed -i -e "s/%PASSWD%/$K8SPWD/g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

kubectl -n $1 create configmap fluentd-conf --from-file config/fluent-bit.conf --from-file config/parsers.conf --from-file config/run.sh

run_component $1

rm -rf $1/*-e

