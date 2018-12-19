#!/usr/bin/env bash

source ../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE ..." >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

filePath=$1/log4j.properties
cp config/log4j.properties $filePath

for ((i=1;i<=$NUM;i++)); do
  filePath=$1/zoo.cfg
  rm -rf $filePath
  cp config/zoo.cfg $filePath

  serverList=""
  for ((j=1;j<=$NUM;j++)); do
    serverurl="server.$j=zk$j:2888:3888"
    if [ $j = $i ]
      then
        serverurl="server.$j=0.0.0.0:2888:3888"
    fi

    if [ "${serverList}" = "" ]
      then
        serverList=$serverurl
      else
        serverList=$serverList"\n"$serverurl
    fi
  done
  sed -i -e "s/%serverlist%/$serverList/g" $filePath

  kubectl --namespace=$1 create configmap zookeeper$i-config --from-file $1/log4j.properties --from-file $1/zoo.cfg
done

for ((i=1;i<=$NUM;i++)); do
  filePath=$1/zookeeper$i-rc.yml
  cp zookeeper-rc.yml $filePath
  sed -i -e "s/%ZK_NUM%/$i/g" $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%NUM%/$NUM/g" $filePath
  sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
  sed -i -e "s/%HOST%/${HOST[$i - 1]}/g" $filePath
  sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
  sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
  sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
  sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

  if [ $ISEXTERNPORT = true ]
    then
      filePath=$1/zookeeper$i-svc.yml
      cp zookeeper-svc-extern-port.yml $filePath
      sed -i -e "s/%ZK_NUM%/$i/g" $filePath
      sed -i -e "s/%NAMESPACE%/$1/g" $filePath
      sed -i -e "s/%ZK_PORT%/${ZKPORT[$i-1]}/g" $filePath
    else
      filePath=$1/zookeeper$i-svc.yml
      cp zookeeper-svc.yml $filePath
      sed -i -e "s/%ZK_NUM%/$i/g" $filePath
      sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  fi
done

#label_node zk-$1 true $HOST
run_component $1

rm -rf $1/*-e

