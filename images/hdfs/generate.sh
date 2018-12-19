#!/usr/bin/env bash

source ../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 2 ]; then
  echo "Usage: $0 NAMESPACE PEER_NUM..." >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

cp *.yaml $1/

for file in `ls $1 |grep .yaml`; do
  path="$1/$file"
  sed -i -e "s/%NAMESPACE%/$1/g" $path
done

sed -i -e "s/%HOST%/$HDFSMASTER1/g" $1/namenode-rc.yaml
sed -i -e "s#%IMAGEURL%#$HDFSMASTER1URL#g" $1/namenode-rc.yaml
sed -i -e "s/%NAMENODE_EXIP%/$HDFSEXIP/g" $1/namenode-svc.yaml

sed -i -e "s/%HOST%/$HDFSMASTER2/g" $1/standby-rc.yaml
sed -i -e "s#%IMAGEURL%#$HDFSMASTER2URL#g" $1/standby-rc.yaml

for ((i=1;i<=$2;i++)); do
  sed -i -e "s/%HOST%/${HDFSDATA[$i - 1]}/g" $1/datanode$i-rc.yaml
  sed -i -e "s#%IMAGEURL%#$HDFSDATAURL#g" $1/datanode$i-rc.yaml
done

#label_node hdfs-$1 true $HDFSHOST
run_component $1

rm -rf $1/*-e
