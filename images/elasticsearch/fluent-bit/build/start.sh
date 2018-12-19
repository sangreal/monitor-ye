#!/bin/bash

mkdir -p ./localconf
cp /opt/conf/run.sh ./localconf/
chmod a+x ./localconf/run.sh
./localconf/run.sh