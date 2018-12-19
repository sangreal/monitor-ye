#!/usr/bin/env bash

function run_component {
if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

for file in $(ls $1/ | grep storage)
do
    ennctl create -f $1/$file
done

for file in $(ls $1/ | grep rc)
do
    ennctl create -a monitor-app -f $1/$file
done

for file in $(ls $1/ | grep svc)
do
    ennctl create -a monitor-app -f $1/$file
done

}

function stop_component {

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

#for file in $(ls $1/ | grep svc)
#do
#    ennctl delete -f $1/$file
#done

for file in $(ls $1/ | grep rc)
do
    ennctl delete -f $1/$file
done

for file in $(ls $1/ | grep storage)
do
    ennctl delete -f $1/$file
done
}

function clean_component {
if [ "$#" -lt 1 ]; then
  echo "Usage: $0 NAMESPACE" >&2
  exit 1
fi

stop_component $1
rm -rf $1
}

function getClusterAddr {
  addr=""

  IFS='='

  path=`pwd`
  path=${path%%images*}

  while read k v
  do
    if [ $k = "addr" ]; then
       addr=$v
    fi
  done < $path/.conf/cluster
  
  echo $addr
}
