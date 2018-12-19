# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y openssh-server openjdk-8-jdk vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# enable to run `/usr/sbin/sshd -D`
RUN mkdir -p /var/run/sshd

COPY tomcat-7.0.52-utf8.tar.gz tomcat-7.0.52-utf8.tar.gz

ADD enn-monitor-log-config-web.war enn-monitor-log-config-web.war

RUN tar -zxvf tomcat-7.0.52-utf8.tar.gz \
	&& mv /opt/enn-monitor-log-config-web.war /opt/apache-tomcat-7.0.52/webapps \
        && rm -rf tomcat-7.0.52-utf8.tar.gz

COPY tomcat.sh tomcat.sh
RUN chmod a+x /opt/tomcat.sh

ENTRYPOINT ["/opt/tomcat.sh"]
