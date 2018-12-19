#!/usr/bin/env bash
source ../../common_script/funcs.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 ENV ..." >&2
  exit 1
fi
addr=$1
source $addr/log-app-common.sh

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

cp spark-etl-log-app.yml tmp/

sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" tmp/spark-etl-log-app.yml
sed -i -e "s/%CPU_REQ_LOG_APP%/$CPU_REQ_LOG_APP/g" tmp/spark-etl-log-app.yml
sed -i -e "s/%CPU_LIMIT_LOG_APP%/$CPU_LIMIT_LOG_APP/g" tmp/spark-etl-log-app.yml
sed -i -e "s/%MEM_REQ_LOG_APP%/$MEM_REQ_LOG_APP/g" tmp/spark-etl-log-app.yml
sed -i -e "s/%MEM_LIMIT_LOG_APP%/$MEM_LIMIT_LOG_APP/g" tmp/spark-etl-log-app.yml

sed -i -e "s#%CEPH_PATH%#$CEPH_PATH#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%WORKSPACE%#$WORKSPACE#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%JOB_JAR%#$JOB_JAR#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%JOB_SH%#$JOB_SH#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%SPARK_MASTER%#$SPARK_MASTER#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%MAIN_CLASS%#$MAIN_CLASS#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%TOTAL_CORES%#$TOTAL_CORES#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%EXECUTOR_MEM%#$EXECUTOR_MEM#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%EXECUTOR_CORES%#$EXECUTOR_CORES#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%MAX_RATE%#$MAX_RATE#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%KAFKA%#$KAFKA#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%PROM_PUSH%#$PROM_PUSH#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%ES_URL%#$ES_URL#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%RECEIVER_COUNT%#$RECEIVER_COUNT#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%TOPIC_NAME%#$TOPIC_NAME#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%ASYNC%#$ASYNC#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%GROUP_ID%#$GROUP_ID#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%ES_BATCH%#$ES_BATCH#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%RDD_PARTITION%#$RDD_PARTITION#g" tmp/spark-etl-log-app.yml
sed -i -e "s#%SPARK_BATCH%#$SPARK_BATCH#g" tmp/spark-etl-log-app.yml

ennctl -n monitor-system-log create app etl-log
ennctl -a etl-log create -f tmp/spark-etl-log-app.yml

