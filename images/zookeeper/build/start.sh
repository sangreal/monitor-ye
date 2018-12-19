#!/bin/bash

touch /data/db/myid
echo ${myid} >> /data/db/myid

zkServer.sh start-foreground
