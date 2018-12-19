#!/usr/bin/env bash

HDFS_ADDR=hdfs://namenode1:8020/hbase

ZK_NODES=zk1,zk2,zk3

HBASE_MASTER_EXIP="10.19.137.141"
HBASEHOST=(10.19.137.141 10.19.137.142 10.19.137.143)

HBASEMASTERURL=127.0.0.1:29006/library/hbase-master:1.0
HBASENODEURL=127.0.0.1:29006/library/hbase-node:1.0
