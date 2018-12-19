#!/usr/bin/env bash
source ../../common_script/funcs.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 ENV ..." >&2
  exit 1
fi
addr=$1
source $addr/app-common.sh

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

cp spark-etl-app.yml tmp/

sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" tmp/spark-etl-app.yml
sed -i -e "s/%CPU_REQ_APP%/$CPU_REQ_APP/g" tmp/spark-etl-app.yml
sed -i -e "s/%CPU_LIMIT_APP%/$CPU_LIMIT_APP/g" tmp/spark-etl-app.yml
sed -i -e "s/%MEM_REQ_APP%/$MEM_REQ_APP/g" tmp/spark-etl-app.yml
sed -i -e "s/%MEM_LIMIT_APP%/$MEM_LIMIT_APP/g" tmp/spark-etl-app.yml

sed -i -e "s#%CEPH_PATH%#$CEPH_PATH#g" tmp/spark-etl-app.yml
sed -i -e "s#%WORKSPACE%#$WORKSPACE#g" tmp/spark-etl-app.yml
sed -i -e "s#%JOB_JAR%#$JOB_JAR#g" tmp/spark-etl-app.yml
sed -i -e "s#%JOB_SH%#$JOB_SH#g" tmp/spark-etl-app.yml
sed -i -e "s#%SPARK_MASTER%#$SPARK_MASTER#g" tmp/spark-etl-app.yml
sed -i -e "s#%MAIN_CLASS%#$MAIN_CLASS#g" tmp/spark-etl-app.yml
sed -i -e "s#%TOTAL_CORES%#$TOTAL_CORES#g" tmp/spark-etl-app.yml
sed -i -e "s#%EXECUTOR_MEM%#$EXECUTOR_MEM#g" tmp/spark-etl-app.yml
sed -i -e "s#%EXECUTOR_CORES%#$EXECUTOR_CORES#g" tmp/spark-etl-app.yml
sed -i -e "s#%MAX_RATE%#$MAX_RATE#g" tmp/spark-etl-app.yml
sed -i -e "s#%KAFKA%#$KAFKA#g" tmp/spark-etl-app.yml
sed -i -e "s#%TOPIC_NAME%#$TOPIC_NAME#g" tmp/spark-etl-app.yml
sed -i -e "s#%GROUP_ID%#$GROUP_ID#g" tmp/spark-etl-app.yml
sed -i -e "s#%OPENTSDB%#$OPENTSDB#g" tmp/spark-etl-app.yml

ennctl -n monitor-system-metrics create app etl-metrics
ennctl -a etl-metrics create -f tmp/spark-etl-app.yml

