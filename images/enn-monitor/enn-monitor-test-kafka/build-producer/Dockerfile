# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update && apt-get install -y openjdk-8-jdk vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ADD monitor.properties monitor.properties
ADD enn-monitor-test-kafka-1.0.jar enn-monitor-test-kafka-1.0.jar

