#!/usr/bin/env bash
spark-submit \
    --master $SPARK_MASTER \
    --class $MAIN_CLASS \
    --total-executor-cores $TOTAL_CORES \
    --conf "spark.streaming.kafka.maxRatePerPartition=$MAX_RATE" \
#    --conf "spark.streaming.backpressure.enabled=true" \
    --conf "spark.executor.cores=$EXECUTOR_CORES" \
    --conf "spark.executor.memory=$EXECUTOR_MEM" \
    --conf "spark.executor.extraJavaOptions=-XX:+UseG1GC -XX:InitiatingHeapOccupancyPercent=35 -XX:ConcGCThreads=2" \
    $WORKSPACE/$JOB_JAR \
    --env "online" \
    --metricsTopic "$TOPIC_NAME" \
    --kafkaBootstrap "$KAFKA" \
    --opentsdbUrl "$OPENTSDB_URL" \
    --prometheusPushUrl "$PROM_PUSH" \
    --apiuser "$APIUSER" \
    --apiserver "$APISERVER" \
    --apipass "$APIPASS" \
    --groupid "$GROUP_ID" \
    --rddRepatitionNum $RDD_PARTITION \
    --async "$ASYNC"