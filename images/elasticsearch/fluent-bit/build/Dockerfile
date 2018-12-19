# Please use command below to build the image:
#   docker build -f Dockerfile -t dojoyn:<version> .
FROM ubuntu:16.04

WORKDIR /opt

# ADD sources.list /etc/apt/sources.list
RUN apt-get update
RUN apt-get -y upgrade

RUN apt-get install -y vim gcc g++ make cmake jq tzdata python autoconf automake libtool libsystemd-dev
RUN apt-get install -y curl

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY librdkafka.tar.gz /opt/librdkafka.tar.gz
RUN tar -zxvf librdkafka.tar.gz && rm -rf librdkafka.tar.gz
RUN cd /opt/librdkafka && ./configure && make && make install

COPY fluent-shared-lib.tar.gz /opt/fluent-shared-lib.tar.gz
RUN tar -zxvf fluent-shared-lib.tar.gz && rm -rf fluent-shared-lib.tar.gz
RUN cp -r fluent-shared-lib/* /usr/local/lib
 
COPY ld.so.conf /etc/ld.so.conf
RUN ldconfig

COPY fluent-bit.tar.gz /opt/fluent-bit.tar.gz
RUN tar -zxvf fluent-bit.tar.gz && rm -rf fluent-bit.tar.gz
RUN cd /opt/fluent-bit && cmake . && make

ADD ./start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

ENTRYPOINT ["/opt/start.sh"]
