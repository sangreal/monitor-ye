#!/usr/bin/env bash

source ../../../common_script/funcs.sh

addr=$(getClusterAddr)
source ../$addr/common.sh

docker build -t "$IMAGEURL" .
docker push $IMAGEURL
