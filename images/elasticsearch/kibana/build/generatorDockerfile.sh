#!/usr/bin/env bash

source ../../../common_script/funcs.sh

addr=$(getClusterAddr)
source ../$addr/common.sh

cp ../../../../tools/jdk-8u102-linux-x64.tar.gz jdk-8u102-linux-x64.tar.gz
cp ../../../../tools/kibana-5.4.0-linux-x86_64.tar.gz kibana-5.4.0-linux-x86_64.tar.gz

docker build -t "$IMAGEURL" .
docker push $IMAGEURL

rm -rf jdk-8u102-linux-x64.tar.gz
rm -rf kibana-5.4.0-linux-x86_64.tar.gz
