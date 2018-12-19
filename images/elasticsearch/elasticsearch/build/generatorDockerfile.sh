#!/usr/bin/env bash

source ../../../common_script/funcs.sh

addr=$(getClusterAddr)
echo $addr
source ../$addr/common.sh

cp ../../../../tools/elasticsearch-5.4.0.tar.gz elasticsearch-5.4.0.tar.gz
cp ../../../../tools/jdk-8u102-linux-x64.tar.gz jdk-8u102-linux-x64.tar.gz
cp ../../../../tools/elasticsearch-sql-5.4.0.0.zip elasticsearch-sql-5.4.0.0.zip

docker build -t "$IMAGEURL" .
docker push $IMAGEURL

rm -rf elasticsearch-sql-5.4.0.0.zip
rm -rf elasticsearch-5.4.0.tar.gz
rm -rf jdk-8u102-linux-x64.tar.gz
