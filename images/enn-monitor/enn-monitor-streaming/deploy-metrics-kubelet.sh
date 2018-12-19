#!/usr/bin/env bash
source ../../common_script/funcs.sh

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 ENV ..." >&2
  exit 1
fi
addr=$1
source $addr/metrics-common.sh

if [ ! -d "tmp" ]; then
  mkdir tmp
fi

cp spark-etl-metrics.yml tmp/

sed -i -e "s#%IMAGEURL%#$IMAGEURL#g" tmp/spark-etl-metrics.yml
sed -i -e "s/%CPU_REQ_METRIC%/$CPU_REQ_METRIC/g" tmp/spark-etl-metrics.yml
sed -i -e "s/%CPU_LIMIT_METRIC%/$CPU_LIMIT_METRIC/g" tmp/spark-etl-metrics.yml
sed -i -e "s/%MEM_REQ_METRIC%/$MEM_REQ_METRIC/g" tmp/spark-etl-metrics.yml
sed -i -e "s/%MEM_LIMIT_METRIC%/$MEM_LIMIT_METRIC/g" tmp/spark-etl-metrics.yml

sed -i -e "s#%CEPH_PATH%#$CEPH_PATH#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%WORKSPACE%#$WORKSPACE#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%JOB_JAR%#$JOB_JAR#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%JOB_SH%#$JOB_SH#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%SPARK_MASTER%#$SPARK_MASTER#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%MAIN_CLASS%#$MAIN_CLASS#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%TOTAL_CORES%#$TOTAL_CORES#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%EXECUTOR_MEM%#$EXECUTOR_MEM#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%EXECUTOR_CORES%#$EXECUTOR_CORES#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%MAX_RATE%#$MAX_RATE#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%KAFKA%#$KAFKA#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%PROM_PUSH%#$PROM_PUSH#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%OPENTSDB_URL%#$OPENTSDB_URL#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%APIUSER%#$APIUSER#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%APISERVER%#$APISERVER#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%APIPASS%#$APIPASS#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%TOPIC_NAME%#$TOPIC_NAME#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%ASYNC%#$ASYNC#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%GROUP_ID%#$GROUP_ID#g" tmp/spark-etl-metrics.yml
sed -i -e "s#%RDD_PARTITION%#$RDD_PARTITION#g" tmp/spark-etl-metrics.yml

ennctl -n monitor-system-metrics create app etl-metrics
ennctl -a etl-metrics create -f tmp/spark-etl-metrics.yml

