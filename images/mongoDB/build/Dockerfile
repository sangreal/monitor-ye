# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y vim tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz
RUN tar -zxvf mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz && rm -rf mongodb-linux-x86_64-ubuntu1604-3.4.2.tgz

RUN ln -s /opt/conf/.mongorc.js /root/.mongorc.js

RUN chmod -R a+x /opt/mongodb-linux-x86_64-ubuntu1604-3.4.2/bin/mongod

ADD ./start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

ENTRYPOINT ["/opt/start.sh"]


