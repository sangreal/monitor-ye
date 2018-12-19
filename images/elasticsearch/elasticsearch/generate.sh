#!/usr/bin/env bash

source ../../common_script/funcs.sh

addr=$(getClusterAddr)
source $addr/common.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE ..." >&2
  exit 1
fi

if [ ! -d "$1" ]; then
  mkdir $1
fi

#master

for ((i=1;i<=$MASTERNUM;i++)); do
  filePath=$1/es-master-rc-$i.yml
  cp es-master-rc.yml $filePath
  sed -i -e "s#%imageurl%#$IMAGEURL#g" $filePath
  sed -i -e "s/%index%/$i/g" $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%MEMREQ%/$MASTERMEMREQ/g" $filePath
  sed -i -e "s/%CPUREQ%/$MASTERCPUREQ/g" $filePath
  sed -i -e "s/%MEMLIMIT%/$MASTERMEMLIMIT/g" $filePath
  sed -i -e "s/%MASTERJVMMEM%/$MASTERJVMMEM/g" $filePath
  sed -i -e "s/%CPULIMIT%/$MASTERCPULIMIT/g" $filePath
  sed -i -e "s/%TokenIp%/$TokenIp/g" $filePath
  sed -i -e "s/%TokenPort%/$TokenPort/g" $filePath
  sed -i -e "s/%Token%/$Token/g" $filePath
  
  sed -i -e "s/%ES_NETWORK_WORK%/${ES_NETWORK_MASTER_PROD[$i - 1]}/g" $filePath
  sed -i -e "s/%PUBLISH_HOST%/${ES_PUBLISH_HOST_PROD[$i - 1]}/g" $filePath
  sed -i -e "s/%PUBLISH_PORT%/${ES_PUBLISH_PORT_PROD[$i - 1]}/g" $filePath

  filePath=$1/es-master-svc-$i.yml
  cp es-master-svc.yml $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%index%/$i/g" $filePath

  filePath=$1/es-master-pva-$i.yml
  cp es-master-pva.yml $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%index%/$i/g" $filePath

  filePath=$1/es-master-pvc-$i.yml
  cp es-master-pvc.yml $filePath
  sed -i -e "s/%NAMESPACE%/$1/g" $filePath
  sed -i -e "s/%index%/$i/g" $filePath
done

#data
filePath=$1/es-data-rc.yml
cp es-data-rc.yml $filePath
sed -i -e "s#%imageurl%#$IMAGEURL#g" $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%DATANUM%/$DATANUM/g" $filePath
sed -i -e "s/%MEMREQ%/$DATAMEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$DATACPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$DATAMEMLIMIT/g" $filePath
sed -i -e "s/%DATAJVMMEM%/$DATAJVMMEM/g" $filePath
sed -i -e "s/%CPULIMIT%/$DATACPULIMIT/g" $filePath
sed -i -e "s/%TokenIp%/$TokenIp/g" $filePath
sed -i -e "s/%TokenPort%/$TokenPort/g" $filePath
sed -i -e "s/%Token%/$Token/g" $filePath

sed -i -e "s/%ES_NETWORK_WORK%/$ES_NETWORK_PROD/g" $filePath
sed -i -e "s/%PUBLISH_HOST%/$ES_DATA_HOST_PROD/g" $filePath
sed -i -e "s/%PUBLISH_PORT%/$ES_DATA_PORT_PROD/g" $filePath

filePath=$1/es-data-svc.yml
cp es-data-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

filePath=$1/es-data-pva.yml
cp es-data-pva.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

filePath=$1/es-data-pvc.yml
cp es-data-pvc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath

#client
filePath=$1/es-client-rc.yml
cp es-client-rc.yml $filePath
sed -i -e "s#%imageurl%#$IMAGEURL#g" $filePath
sed -i -e "s/%CLIENTNUM%/$CLIENTNUM/g" $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%MEMREQ%/$CLIENTMEMREQ/g" $filePath
sed -i -e "s/%CPUREQ%/$CLIENTCPUREQ/g" $filePath
sed -i -e "s/%MEMLIMIT%/$CLIENTMEMLIMIT/g" $filePath
sed -i -e "s/%CLIENTJVMMEM%/$CLIENTJVMMEM/g" $filePath
sed -i -e "s/%CPULIMIT%/$CLIENTCPULIMIT/g" $filePath
sed -i -e "s/%HOST_LABEL%/es-client-svc-$1/g" $filePath
sed -i -e "s/%TokenIp%/$TokenIp/g" $filePath
sed -i -e "s/%TokenPort%/$TokenPort/g" $filePath
sed -i -e "s/%Token%/$Token/g" $filePath

sed -i -e "s/%ES_NETWORK_WORK%/$ES_NETWORK_PROD/g" $filePath
sed -i -e "s/%PUBLISH_HOST%/${ES_PUBLISH_HOST_PROD[$i - 1]}/g" $filePath
sed -i -e "s/%PUBLISH_PORT%/${ES_PUBLISH_PORT_PROD[$i - 1]}/g" $filePath

filePath=$1/es-client-svc.yml
cp es-client-svc.yml $filePath
sed -i -e "s/%NAMESPACE%/$1/g" $filePath
sed -i -e "s/%index%/$i/g" $filePath

kubectl --namespace=$1 create configmap es-conf --from-file config/elasticsearch.yml --from-file config/jvm.options --from-file config/limits.conf --from-file config/log4j2.properties --from-file config/java.policy

# label_node es-search-$1 true $HOST
run_component $1

rm -rf $1/*-e

