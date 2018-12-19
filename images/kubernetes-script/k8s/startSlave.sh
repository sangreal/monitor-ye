nohup kube-proxy --master=http://10.19.132.167:8080 --logtostderr=true --v=0 &
nohup kubelet --allow-privileged=false --api-servers=http://10.19.132.167:8080 --v=2 --hostname-override=10.19.132.167 --pod-infra-container-image=index.alauda.cn/googlecontainer/pause-amd64:3.0 --cluster_dns=172.16.100.0 --cluster_domain=cn.enn --storage_driver=kafka --storage_driver_kafka_broker_list=10.19.140.4:30191,10.19.140.5:30192,10.19.140.12:30193,10.19.140.13:30194,10.19.140.15:30195 --storage_driver_kafka_topic=k8s-kubelet --storage_driver_kafka_ssl_verify=false &

