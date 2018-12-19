
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update && apt-get install -y openjdk-8-jdk vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ADD enn-monitor-tracing-1.0.0.jar enn-monitor-tracing-1.0.0.jar