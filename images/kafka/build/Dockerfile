FROM ubuntu:16.04

# install package
WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# JDK
COPY jdk-8u102-linux-x64.tar.gz jdk-8u102-linux-x64.tar.gz
RUN tar -zxvf jdk-8u102-linux-x64.tar.gz && rm -rf jdk-8u102-linux-x64.tar.gz

ENV JAVA_HOME /opt/jdk1.8.0_102
ENV PATH $PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin

# KAFKA
COPY kafka_2.11-0.10.0.0.tgz kafka_2.11-0.10.0.0.tgz

RUN tar xzf kafka_2.11-0.10.0.0.tgz \
  && rm kafka_2.11-0.10.0.0.tgz

RUN rm -rf /opt/kafka_2.11-0.10.0.0/config/server.properties && ln -s /opt/conf/server.properties /opt/kafka_2.11-0.10.0.0/config/server.properties

ENTRYPOINT ["/opt/kafka_2.11-0.10.0.0/bin/kafka-server-start.sh", "/opt/kafka_2.11-0.10.0.0/config/server.properties"]
