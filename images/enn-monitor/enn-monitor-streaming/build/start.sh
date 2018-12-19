#!/usr/bin/env bash
mkdir -p ${WORKSPACE}

cp -r ${CEPH_PATH}/* ${WORKSPACE}

${WORKSPACE}/${JOB_SH}
# JOB_SH 内容：
#spark-submit \
#    --master $SPARK_MASTER \
#    --class enn.monitor.trace.streaming.Collector \
#    --total-executor-cores $TOTAL_CORES \
#    --conf "spark.executor.memory=$EXECUTOR_MEM" \
#    --conf "spark.executor.cores=$EXECUTOR_CORES" \
#    --conf "spark.streaming.receiver.maxRate=$MAX_RATE" \
#    $JOB_JAR \
#    --env "online" \
#    --groupid "$GROUP_ID" \
#    --esClusterName "es-log" \
#    --kafkaZk "$KAFKA_ZK" \
#    --esRest "$ES_REST" \
#    --esTcp "$ES_TCP"
