#!/usr/bin/env bash

source ../../common_script/funcs.sh

addr=${1}
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE..." >&2
  exit 1
fi

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

filePath=tmp/grafana-rc.yml
cp grafana-rc.yml $filePath
sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

filePath=tmp/grafana-svc.yml
cp grafana-svc.yml $filePath

ennctl -n g-laikang-sh-qa create -f grafana-storage.yml
ennctl -n g-laikang-sh-qa create app laikang
ennctl -n g-laikang-sh-qa create configmap grafana-conf --from-file config/grafana.ini
ennctl -a laikang create -f tmp/grafana-rc.yml
ennctl -a laikang create -f tmp/grafana-svc.yml

rm -rf tmp/*-e

