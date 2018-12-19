#!/bin/sh

sed -i "s/%KAFKAID%/$KAFKAID/g" /opt/kafka_2.11-0.10.0.0/config/server.properties
sed -i "s/%HOSTNAME%/$HOSTNAME/g" /opt/kafka_2.11-0.10.0.0/config/server.properties
sed -i "s/%LISTENERURL%/$LISTENERURL/g" /opt/kafka_2.11-0.10.0.0/config/server.properties
sed -i "s#%ZKURL%#$ZKURL#g" /opt/kafka_2.11-0.10.0.0/config/server.properties

/opt/kafka_2.11-0.10.0.0/bin/kafka-server-start.sh /opt/kafka_2.11-0.10.0.0/config/server.properties

/usr/sbin/sshd -D
