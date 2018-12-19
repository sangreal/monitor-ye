#!/usr/bin/env bash

IMAGEURL="10.38.240.34:29006/enncloud/elasticsearch-cluster-5.4.0:0.8.0-RELEASE"

TokenIp="enn-monitor-security-gateway-web.monitor-system-security"
TokenPort="10000"
Token="micklongen-elasticsearch-token"

MASTERNUM=3
DATANUM=15
CLIENTNUM=3

ES_NETWORK_MASTER_PROD=("elasticsearch-master-2, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-3" "elasticsearch-master-1, elasticsearch-master-2")
ES_NETWORK_PROD="elasticsearch-master-1, elasticsearch-master-2, elasticsearch-master-3"
ES_PUBLISH_HOST_PROD=(0.0.0.0 0.0.0.0 0.0.0.0)
ES_PUBLISH_PORT_PROD=(9300 9300 9300)
ES_DATA_HOST_PROD="0.0.0.0"
ES_DATA_PORT_PROD=9300

MASTERCPUREQ="1000m"
MASTERCPULIMIT="3000m"

DATACPUREQ="3000m"
DATACPULIMIT="24000m"

CLIENTCPUREQ="2000m"
CLIENTCPULIMIT="24000m"

MASTERMEMREQ="6Gi"
MASTERMEMLIMIT="6Gi"
MASTERJVMMEM="3g"

DATAMEMREQ="32Gi"
DATAMEMLIMIT="32Gi"
DATAJVMMEM="16g"

CLIENTMEMREQ="32Gi"
CLIENTMEMLIMIT="32Gi"
CLIENTJVMMEM="16g"

