#!/usr/bin/env bash
spark-submit \
    --master $SPARK_MASTER \
    --class $MAIN_CLASS \
    --conf "spark.streaming.kafka.maxRatePerPartition=$MAX_RATE" \
#    --conf "spark.streaming.backpressure.enabled=true" \
    --conf "spark.streaming.kafka.consumer.cache.enabled=false" \
    --total-executor-cores $TOTAL_CORES \
    --conf "spark.executor.cores=$EXECUTOR_CORES" \
    --conf "spark.executor.memory=$EXECUTOR_MEM" \
    $WORKSPACE/$JOB_JAR \
    --env "online" \
    --logTopic "$TOPIC_NAME" \
    --kafka "$KAFKA" \
    --prompushUrl "$PROM_PUSH" \
    --esUrl "$ES_URL" \
    --groupid "$GROUP_ID" \
    --esInsertBatch $ES_BATCH \
    --rddRepatitionNum $RDD_PARTITION \
    --kafkaReceiverPerTopic $RECEIVER_COUNT \
    --batchInterval $SPARK_BATCH \
    --async "$ASYNC"