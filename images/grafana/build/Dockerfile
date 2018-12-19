FROM ubuntu:16.04

WORKDIR /opt

RUN apt-get update && apt-get install -y fontconfig-config fonts-dejavu-core libfontconfig1 sysv-rc-conf tzdata

RUN	echo Asia/Shanghai > /etc/timezone									&&	\
	ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY grafana_4.4.3_amd64.deb grafana_4.4.3_amd64.deb
RUN dpkg -i grafana_4.4.3_amd64.deb

RUN rm -rf /etc/grafana/grafana.ini && ln -s /opt/conf/grafana.ini /etc/grafana/grafana.ini

ADD ./start.sh /opt/start.sh
RUN chmod a+x /opt/start.sh

ENTRYPOINT ["/sbin/my_init", "/opt/start.sh"]



