#!/usr/bin/env bash

es_url=$1

#curl -XPUT 'http://10.19.140.200:31921/_template/enn-dependency:template' -d '{
curl -u elastic:changeme -XPUT http://${es_url}/_template/shanghai-logs -d '{
  "template": "shanghai-*",
  "settings": {
    "number_of_shards": 3,
    "number_of_replicas": 1
  },
  "mappings": {
    "log_type": {
      "_source": {
        "enabled": false
      },
      "properties": {
        "dateTime": {
          "type": "date",
          "format": "dateOptionalTime"
        },
        "createTime": {
          "type": "date"
        },
        "logLevel": {
          "type": "keyword"
        },
        "logType": {
          "type": "keyword"
        },
        "clusterName": {
          "type": "keyword"
        },
        "nodeName": {
          "type": "ip"
        },
        "appName": {
          "type": "keyword"
        },
        "nameSpace": {
          "type": "keyword"
        },
        "podName": {
          "type": "keyword"
        },
        "position": {
          "type": "text"
        },
        "pid": {
          "type": "keyword"
        },
        "threadName": {
          "type": "text"
        },
        "traceId": {
          "type": "keyword"
        },
        "parentPod": {
          "type": "keyword"
        },
        "log": {
          "type": "text"
        },
        "token": {
          "type": "keyword"
        }
      }
    }
  }
}'
