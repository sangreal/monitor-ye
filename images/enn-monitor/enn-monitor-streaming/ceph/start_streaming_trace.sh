#!/usr/bin/env bash
spark-submit \
    --master $SPARK_MASTER \
    --class $MAIN_CLASS\
    --total-executor-cores $TOTAL_CORES \
    --conf "spark.executor.memory=$EXECUTOR_MEM" \
    --conf "spark.executor.cores=$EXECUTOR_CORES" \
    --conf "spark.streaming.kafka.maxRatePerPartition=$MAX_RATE" \
#    --conf "spark.streaming.backpressure.enabled=true" \
    $WORKSPACE/$JOB_JAR \
    --env "online" \
    --groupid "$GROUP_ID" \
    --esClusterName "$ESCLUSTERNAME" \
    --kafkaBootstrap "$KAFKA" \
    --esRest "$ES_REST" \
    --esTcp "$ES_TCP"
