#!/usr/bin/env bash

HDFS_ADDR=hdfs://namenode1:8020/hbase

ZK_NODES=zk1,zk2,zk3

HBASE_MASTER_EXIP="10.19.248.12"
HBASEHOST=(10.19.248.35 10.19.248.36 10.19.248.39)

HBASEMASTERURL=10.19.248.12:29006/enncloud/hbase-master:1.0
HBASENODEURL=10.19.248.12:29006/enncloud/hbase-node:1.0
