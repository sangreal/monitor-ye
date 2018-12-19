FROM ubuntu:16.04

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

# zk
COPY zookeeper-3.4.9.tar.gz zookeeper-3.4.9.tar.gz
RUN tar -xvf zookeeper-3.4.9.tar.gz && rm -rf zookeeper-3.4.9.tar.gz

ENV ZK_HOME /opt/zookeeper-3.4.9
ENV PATH $PATH:$ZK_HOME/bin

# COPY ./zoo.cfg /opt/zookeeper-3.4.9/conf/zoo.cfg
# COPY ./log4j.properties /opt/zookeeper-3.4.9/conf/log4j.properties
# COPY ./zkServer.sh /opt/zookeeper-3.4.9/bin/zkServer.sh

COPY ./start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

RUN rm -rf /opt/zookeeper-3.4.9/conf/zoo.cfg && ln -s /opt/conf/zoo.cfg /opt/zookeeper-3.4.9/conf/zoo.cfg
RUN rm -rf /opt/zookeeper-3.4.9/conf/log4j.properties && ln -s /opt/conf/log4j.properties /opt/zookeeper-3.4.9/conf/log4j.properties

ENTRYPOINT ["/sbin/my_init", "/opt/start.sh"]
