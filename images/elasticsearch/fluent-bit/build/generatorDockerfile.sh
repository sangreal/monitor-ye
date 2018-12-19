#!/usr/bin/env bash

source ../../../common_script/funcs.sh

addr=$(getClusterAddr)
source ../$addr/common.sh

cp ../../../../tools/librdkafka.tar.gz librdkafka.tar.gz
cp ../../../../tools/fluent-bit.tar.gz fluent-bit.tar.gz
cp ../../../../tools/fluent-shared-lib.tar.gz fluent-shared-lib.tar.gz

docker build -t "$IMAGEURL" .
docker push $IMAGEURL

rm -rf fluent-shared-lib.tar.gz
rm -rf fluent-bit.tar.gz
rm -rf librdkafka.tar.gz
