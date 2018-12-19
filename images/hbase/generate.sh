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

cp master* $1/
for ((i=1;i<=$2;i++)); do
  cp regionserver-rc.yaml $1/rs$i-rc.yaml
  cp regionserver-svc.yaml $1/rs$i-svc.yaml
done

for file in `ls $1 |grep .yaml`; do
  path="$1/$file"
  sed -i -e "s/%NAMESPACE%/$1/g" $path
  sed -i -e "s#%HDFS_ADDR%#$HDFS_ADDR#g" $path
  sed -i -e "s/%ZK_NODES%/$ZK_NODES/g" $path
done

sed -i -e "s/%MASTER_EXTERNAL_IP%/$HBASE_MASTER_EXIP/g" $1/master-svc.yaml
sed -i -e "s/%HOST%/$HBASE_MASTER_EXIP/g" $1/master-rc.yaml
sed -i -e "s#%IMAGEURL%#$HBASEMASTERURL#g" $1/master-rc.yaml

for ((i=1;i<=$2;i++)); do
  filePath=$1/rs$i-rc.yaml
  sed -i -e "s/%SLAVE_NUM%/$i/g" $filePath
  sed -i -e "s/%HOST%/${HBASEHOST[$i - 1]}/g" $filePath
  sed -i -e "s#%IMAGEURL%#$HBASENODEURL#g" $filePath

  filePath=$1/rs$i-svc.yaml
  sed -i -e "s/%SLAVE_NUM%/$i/g" $filePath
done

#label_node hbase-$1 true $HBASEHOST
run_component $1

rm -rf $1/*-e



