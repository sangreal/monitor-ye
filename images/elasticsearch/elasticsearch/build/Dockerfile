# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update && apt-get install -y vim iputils-ping tzdata
# RUN apt-get -y upgrade

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY jdk-8u102-linux-x64.tar.gz jdk-8u102-linux-x64.tar.gz
RUN tar -zxvf jdk-8u102-linux-x64.tar.gz && rm -rf jdk-8u102-linux-x64.tar.gz

ENV JAVA_HOME /opt/jdk1.8.0_102
ENV PATH $PATH:$JAVA_HOME/bin:$JAVA_HOME/jre/bin

COPY elasticsearch-5.4.0.tar.gz elasticsearch-5.4.0.tar.gz
RUN tar -zxvf elasticsearch-5.4.0.tar.gz

RUN mkdir -p /opt/es/data && mkdir -p /opt/es/logs

RUN groupadd es && useradd es -g es -p 1
RUN chown -R es:es /opt/elasticsearch-5.4.0 && chown -R es:es /opt/es && chown -R es:es /opt/jdk1.8.0_102

RUN rm -rf /etc/security/limits.conf && ln -s /opt/conf/limits.conf /etc/security/limits.conf
RUN rm -rf /opt/elasticsearch-5.4.0/config/log4j2.properties && ln -s /opt/conf/log4j2.properties /opt/elasticsearch-5.4.0/config/log4j2.properties
RUN rm -rf /opt/elasticsearch-5.4.0/config/elasticsearch.yml && ln -s /opt/conf/elasticsearch.yml /opt/elasticsearch-5.4.0/config/elasticsearch.yml
RUN rm -rf /opt/elasticsearch-5.4.0/config/jvm.options && ln -s /opt/conf/jvm.options /opt/elasticsearch-5.4.0/config/jvm.options
RUN rm -rf /opt/jdk1.8.0_102/jre/lib/security/java.policy && ln -s /opt/conf/java.policy /opt/jdk1.8.0_102/jre/lib/security/java.policy
RUN rm -rf /opt/elasticsearch-5.4.0/lib/guava-18.0.jar

COPY elasticsearch-sql-5.4.0.0.zip elasticsearch-sql-5.4.0.0.zip
RUN /opt/elasticsearch-5.4.0/bin/elasticsearch-plugin install file:///opt/elasticsearch-sql-5.4.0.0.zip

COPY start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

RUN chmod a+x /opt/elasticsearch-5.4.0/lib/elasticsearch-5.4.0.jar

ENTRYPOINT ["/sbin/my_init", "/opt/start.sh"]
