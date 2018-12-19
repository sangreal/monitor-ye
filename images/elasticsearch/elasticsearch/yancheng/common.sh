#!/usr/bin/env bash

IMAGEURL="10.19.248.12:29006/enncloud/elasticsearch-cluster-5.4.0:0.9.5-RELEASE"

TokenIp="enn-monitor-security-gateway-web.monitor-system-security"
TokenPort="10000"
Token="micklongen-elasticsearch-token"

MASTERNUM=3
DATANUM=20
CLIENTNUM=3

ES_NETWORK_MASTER_PROD=("elasticsearch-master-2, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-2")
ES_NETWORK_PROD="elasticsearch-master-1, elasticsearch-master-2, elasticsearch-master-3"
ES_PUBLISH_HOST_PROD=(0.0.0.0 0.0.0.0 0.0.0.0)
ES_PUBLISH_PORT_PROD=(9300 9300 9300)
ES_DATA_HOST_PROD="0.0.0.0"
ES_DATA_PORT_PROD=9300

MASTERCPUREQ="500m"
MASTERCPULIMIT="3000m"

DATACPUREQ="500m"
DATACPULIMIT="10000m"

CLIENTCPUREQ="500m"
CLIENTCPULIMIT="10000m"

MASTERMEMREQ="9Gi"
MASTERMEMLIMIT="9Gi"
MASTERJVMMEM="4g"

DATAMEMREQ="32Gi"
DATAMEMLIMIT="32Gi"
DATAJVMMEM="16g"

CLIENTMEMREQ="32Gi"
CLIENTMEMLIMIT="32Gi"
CLIENTJVMMEM="16g"
