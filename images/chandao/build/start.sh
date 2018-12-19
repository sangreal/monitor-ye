#!/bin/bash

if [ ! -f "/opt/zbox/zbox" ]; then 
   tar -zxvf ZenTaoPMS.9.8.3.zbox_64.tar.gz -C /opt
fi

/opt/zbox/zbox start

sleep 3650000d
