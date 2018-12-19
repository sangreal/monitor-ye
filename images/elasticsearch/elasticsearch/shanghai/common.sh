#!/usr/bin/env bash

#IMAGEURL="127.0.0.1:29006/library/elasticsearch-cluster-5.4.0:0.9.0-RELEASE"
IMAGEURL="10.19.140.200:29006/library/elasticsearch-cluster-5.4.0:0.9.1-RELEASE"

TokenIp="enn-monitor-security-gateway-web.monitor-system-security"
TokenPort="10000"
Token="micklongen-elasticsearch-token"

MASTERHOST=(10.19.137.141 10.19.137.146 10.19.137.151)
DATAHOST=(10.19.137.147 10.19.137.149 10.19.137.150)
CLIENTHOST="10.19.137.143 10.19.137.145 10.19.137.148"

MASTERNUM=3
DATANUM=10
CLIENTNUM=3

ES_NETWORK_MASTER_PROD=("elasticsearch-master-2, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-2")
ES_NETWORK_PROD="elasticsearch-master-1, elasticsearch-master-2, elasticsearch-master-3"
ES_PUBLISH_HOST_PROD=(0.0.0.0 0.0.0.0 0.0.0.0)
ES_PUBLISH_PORT_PROD=(9300 9300 9300)
ES_DATA_HOST_PROD="0.0.0.0"
ES_DATA_PORT_PROD=9300

MASTERCPUREQ="200m"
MASTERCPULIMIT="1500m"

DATACPUREQ="500m"
DATACPULIMIT="5000m"

CLIENTCPUREQ="500m"
CLIENTCPULIMIT="3000m"

MASTERMEMREQ="2Gi"
MASTERMEMLIMIT="2Gi"
MASTERJVMMEM="1g"

DATAMEMREQ="4Gi"
DATAMEMLIMIT="16Gi"
DATAJVMMEM="8g"

CLIENTMEMREQ="4Gi"
CLIENTMEMLIMIT="4Gi"
CLIENTJVMMEM="2g"
