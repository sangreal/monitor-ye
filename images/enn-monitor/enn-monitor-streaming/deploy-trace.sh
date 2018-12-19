#!/usr/bin/env bash
source ../../common_script/funcs.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 ENV ..." >&2
  exit 1
fi
addr=$1
source $addr/trace-common.sh

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

cp streaming-trace.yml tmp/

sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" tmp/streaming-trace.yml
sed -i -e "s/%CPU_REQ_TRACE%/$CPU_REQ_TRACE/g" tmp/streaming-trace.yml
sed -i -e "s/%CPU_LIMIT_TRACE%/$CPU_LIMIT_TRACE/g" tmp/streaming-trace.yml
sed -i -e "s/%MEM_REQ_TRACE%/$MEM_REQ_TRACE/g" tmp/streaming-trace.yml
sed -i -e "s/%MEM_LIMIT_TRACE%/$MEM_LIMIT_TRACE/g" tmp/streaming-trace.yml

sed -i -e "s#%CEPH_PATH%#$CEPH_PATH#g" tmp/streaming-trace.yml
sed -i -e "s#%WORKSPACE%#$WORKSPACE#g" tmp/streaming-trace.yml
sed -i -e "s#%JOB_JAR%#$JOB_JAR#g" tmp/streaming-trace.yml
sed -i -e "s#%JOB_SH%#$JOB_SH#g" tmp/streaming-trace.yml
sed -i -e "s#%SPARK_MASTER%#$SPARK_MASTER#g" tmp/streaming-trace.yml
sed -i -e "s#%MAIN_CLASS%#$MAIN_CLASS#g" tmp/streaming-trace.yml
sed -i -e "s#%TOTAL_CORES%#$TOTAL_CORES#g" tmp/streaming-trace.yml
sed -i -e "s#%EXECUTOR_MEM%#$EXECUTOR_MEM#g" tmp/streaming-trace.yml
sed -i -e "s#%EXECUTOR_CORES%#$EXECUTOR_CORES#g" tmp/streaming-trace.yml
sed -i -e "s#%MAX_RATE%#$MAX_RATE#g" tmp/streaming-trace.yml
sed -i -e "s#%KAFKA%#$KAFKA#g" tmp/streaming-trace.yml
sed -i -e "s#%GROUP_ID%#$GROUP_ID#g" tmp/streaming-trace.yml
sed -i -e "s#%ESCLUSTERNAME%#$ESCLUSTERNAME#g" tmp/streaming-trace.yml
sed -i -e "s#%ES_REST%#$ES_REST#g" tmp/streaming-trace.yml
sed -i -e "s#%ES_TCP%#$ES_TCP#g" tmp/streaming-trace.yml

ennctl -n monitor-system-trace create app monitor-trace
ennctl -a monitor-trace create -f tmp/streaming-trace.yml

