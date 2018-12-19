#!/usr/bin/env bash

source ../../common_script/funcs.sh

addr=$(getClusterAddr)
source ../$addr/common.sh

cp ../../../tools/mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz

docker build -t "$IMAGEURL" .
docker push $IMAGEURL

rm -rf mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz
