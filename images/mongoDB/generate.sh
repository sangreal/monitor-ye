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

for ((i=1;i<=$NUM;i++)); do
  filePath=$1/mongo$i-rc.yml
  cp mongo-rc.yml $filePath
  sed -i -e "s/%MONGO_NUM%/$i/g" $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" $filePath
  sed -i -e "s/%MEMREQ%/$MEMREQ/g" $filePath
  sed -i -e "s/%CPUREQ%/$CPUREQ/g" $filePath
  sed -i -e "s/%MEMLIMIT%/$MEMLIMIT/g" $filePath
  sed -i -e "s/%CPULIMIT%/$CPULIMIT/g" $filePath

  filePath=$1/mongo$i-svc.yml
  cp mongo-svc.yml $filePath
  sed -i -e "s/%MONGO_NUM%/$i/g" $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
done

filePath=$1/mongo.conf
cp config/mongo.conf $filePath

filePath=$1/.mongorc.js
cp config/.mongorc.js $filePath

filePath=$1/mongo.js
cp config/mongo.js $filePath
memberlist=""
for i in `seq $NUM`; do
  index=`expr $i - 1`
  if [ "${memberlist}" = "" ]
    then
      memberlist="{_id:($index),host:\"mongo$i.$1:27017\"}"
    else
      memberlist=$memberlist",{_id:($index),host:\"mongo$i.$1:27017\"}"
  fi
done
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%memberlist%/$memberlist/g" $filePath

filePath=$1/mongo-pva.yml
cp mongo-pva.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

filePath=$1/mongo-pvc.yml
cp mongo-pvc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

kubectl --namespace=$1 create configmap mongo-conf --from-file $1/mongo.conf --from-file $1/mongo.js --from-file $1/.mongorc.js

run_component $1

rm -rf $1/*-e

