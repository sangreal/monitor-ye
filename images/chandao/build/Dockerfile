FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y vim tzdata unzip

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

COPY ZenTaoPMS.9.8.3.zbox_64.tar.gz ZenTaoPMS.9.8.3.zbox_64.tar.gz

ENTRYPOINT ["/opt/start.sh"]
