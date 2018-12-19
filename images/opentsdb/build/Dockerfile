FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update
RUN apt-get -y upgrade
RUN apt-get install -y openjdk-8-jdk autoconf make python rpm alien tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ENV GIT_SSL_NO_VERIFY=1
ENV COMPRESSION=NONE
ENV LD_LIBRARY_PATH=/usr/local/lib:$LD_LIBRARY_PATH
ENV PATH=/usr/local/bin:$PATH

# Setup JDK
RUN ln -s /usr/lib/jvm/java-8-openjdk-amd64 /usr/lib/jvm/default-java
ENV JAVA_HOME=/usr/lib/jvm/default-java

COPY opentsdb-2.3.0.tar.gz opentsdb-2.3.0.tar.gz
RUN  tar -zxvf opentsdb-2.3.0.tar.gz && \
     rm -rf opentsdb-2.3.0.tar.gz

# install opentsdb
RUN cd opentsdb-2.3.0 && \
    mkdir build && ln -s ../third_party build && \
    sh build.sh rpm && \
    alien build/opentsdb-2.3.0-1-*.rpm && \
    dpkg -i opentsdb_2.3.0-2_all.deb && \
    mkdir -p /etc/opentsdb
    
# ADD configure file
RUN rm -rf /etc/opentsdb/logback.xml && ln -s /opt/conf/logback.xml /etc/opentsdb/logback.xml
RUN rm -rf /etc/opentsdb/opentsdb.conf && ln -s /opt/conf/opentsdb.conf /etc/opentsdb/opentsdb.conf

ENTRYPOINT ["tsdb", "tsd"]
