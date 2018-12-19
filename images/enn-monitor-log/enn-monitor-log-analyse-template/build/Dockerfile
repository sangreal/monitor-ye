# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y openjdk-8-jdk vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY spark-2.1.0-bin-hadoop2.7.tgz spark-2.1.0-bin-hadoop2.7.tgz
RUN tar zxvf spark-2.1.0-bin-hadoop2.7.tgz

ADD enn-monitor-log-analyse-template.jar enn-monitor-log-analyse-template.jar

