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

for ((i=1;i<=$NUM;i++)); do
  filePath=$1/server.properties
  rm -rf $filePath
  cp config/server.properties $filePath

  sed -i "s/%KAFKAID%/$i/g" $filePath
  if [ $ISEXTERNPORT = true ]
    then
      sed -i -e "s/%LISTENERURL%/${KAFKAAPI_EXPORT[$i-1]}/g" $filePath
      sed -i -e "s/%ZKURL%/$KAFKAZK_EXPORT/g" $filePath
    else
      sed -i -e "s/%LISTENERURL%/${KAFKAAPI_INTERNAL[$i-1]}/g" $filePath
      sed -i -e "s/%ZKURL%/$KAFKAZK_INTERNAL/g" $filePath
  fi

  kubectl --namespace=$1 create configmap kafka$i-config --from-file $filePath
done

for ((i=1;i<=$NUM;i++)); do
  filePath=$1/kafka$i-rc.yml
  cp k1_rc.yml $filePath
  sed -i -e "s/%KAFKA_NUM%/$i/g" $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%HOST%/${HOST[$i - 1]}/g" $filePath
  sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
  sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
  sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
  sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
  sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

  if [ $ISEXTERNPORT = true ]
    then
      filePath=$1/kafka$i-svc.yml
      cp k1_svc_export.yml $filePath
      sed -i -e "s/%KAFKA_NUM%/$i/g" $filePath
      sed -i -e "s/%NAMESPACE%/$1/g" $filePath
      sed -i -e "s/%KAFKA_PORT1%/${KAFKAAPIPORT[$i-1]}/g" $filePath
    else
      filePath=$1/kafka$i-svc.yml
      cp k1_svc.yml $filePath
      sed -i -e "s/%KAFKA_NUM%/$i/g" $filePath
      sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  fi
done

run_component $1

rm -rf $1/*-e

