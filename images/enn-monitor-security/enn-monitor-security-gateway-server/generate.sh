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

# enn-monitor-security-gateway-server
filePath=$1/enn-monitor-security-gateway-server-deploy.yml
cp enn-monitor-security-gateway-server-deploy.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%REPLICSNUM%/$NUM/g" $filePath
sed -i -e "s/%ENNMONITORSECURITYGATEWAYLISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%KAFKAURL%/$KAFKAURL/g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=$1/enn-monitor-security-gateway-server-svc.yml
cp enn-monitor-security-gateway-server-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%ENNMONITORSECURITYGATEWAYLISTENPORT%/$LISTENPORT/g" $filePath
sed -i -e "s/%PORT%/$NODEPORT/g" $filePath

ennctl create app monitor-security-gateway-server -n $1
ennctl create -a monitor-security-gateway-server -f $1/enn-monitor-security-gateway-server-deploy.yml
ennctl create -a monitor-security-gateway-server -f $1/enn-monitor-security-gateway-server-svc.yml

rm -rf $1/*-e

