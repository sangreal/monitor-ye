FROM cat:base

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get install -y vim mysql-server openssh-server

RUN mkdir -p /var/run/sshd

RUN sed -i -e"s/^bind-address[ \t]*=[ ]*127.0.0.1/#bind-address = 0.0.0.0/" /etc/mysql/mysql.conf.d/mysqld.cnf
RUN sed -i "40i sql_mode        = ONLY_FULL_GROUP_BY,NO_AUTO_VALUE_ON_ZERO,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION,PIPES_AS_CONCAT,ANSI_QUOTES" /etc/mysql/mysql.conf.d/mysqld.cnf

RUN service mysql start && \
	mysql -e "UPDATE mysql.user SET authentication_string=PASSWORD('1') where USER='root';" && \ 
        mysql -e "flush privileges;" && \
 	mysql -uroot -p1 -e "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '1' WITH GRANT OPTION;" && \
 	mysql -uroot -p1 -e "flush privileges;"

ADD mysqlStart.sh /opt

ENTRYPOINT ["sh", "/opt/mysqlStart.sh"]

# ENTRYPOINT ["/usr/sbin/service","mysql","start"]
# ENTRYPOINT [`/usr/sbin/sshd -D`]
