#!/usr/bin/env bash
spark-submit \
    --master $SPARK_MASTER \
    --class $MAIN_CLASS \
    --total-executor-cores $TOTAL_CORES \
    --conf "spark.streaming.kafka.maxRatePerPartition=$MAX_RATE" \
#    --conf "spark.streaming.backpressure.enabled=true" \
    --conf "spark.executor.cores=$EXECUTOR_CORES" \
    --conf "spark.executor.memory=$EXECUTOR_MEM" \
    $WORKSPACE/$JOB_JAR \
    --env "online" \
    --metricsTopic "$TOPIC_NAME" \
    --kafkaBootstrap "$KAFKA" \
    --opentsdbUrl "$OPENTSDB_URL" \
    --groupid "$GROUP_ID"
