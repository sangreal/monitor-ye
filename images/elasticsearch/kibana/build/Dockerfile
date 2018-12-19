# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y vim iputils-ping tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY jdk-8u102-linux-x64.tar.gz jdk-8u102-linux-x64.tar.gz
RUN tar -zxvf jdk-8u102-linux-x64.tar.gz && rm -rf jdk-8u102-linux-x64.tar.gz

ENV JAVA_HOME /opt/jdk1.8.0_102
ENV PATH $PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin

COPY kibana-5.4.0-linux-x86_64.tar.gz kibana-5.4.0-linux-x86_64.tar.gz
RUN tar -zxvf kibana-5.4.0-linux-x86_64.tar.gz

RUN rm -rf /opt/kibana-5.4.0-linux-x86_64/config/kibana.yml && ln -s /opt/conf/kibana.yml /opt/kibana-5.4.0-linux-x86_64/config/kibana.yml

ENTRYPOINT ["/opt/kibana-5.4.0-linux-x86_64/bin/kibana"]
