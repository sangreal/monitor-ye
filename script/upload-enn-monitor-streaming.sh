#!/usr/bin/env bash
gradle \
enn-monitor-streaming:enn-monitor-streaming-common:upload \
enn-monitor-streaming:enn-monitor-streaming-sink-elasticsearch:upload \
enn-monitor-streaming:enn-monitor-streaming-sink-opentsdb:upload \
enn-monitor-streaming:enn-monitor-streaming-sink-pushgateway:upload \
enn-monitor-streaming:enn-monitor-streaming-sink-pushprom:upload \
-x javadoc
