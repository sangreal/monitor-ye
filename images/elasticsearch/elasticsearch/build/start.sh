#!/bin/bash

mkdir -p /data/db
chmod -R 777 /data/db

sed -i -e "s/%XMS%/$XMS/g" /opt/elasticsearch-5.4.0/config/jvm.options
sed -i -e "s/%XMX%/$XMX/g" /opt/elasticsearch-5.4.0/config/jvm.options

sysctl -w vm.max_map_count=655360
