# start etcd  
# systemctl restart etcd  
  
# secondly, start flanneld  
# systemctl restart flanneld  
  
# then, start docker  
systemctl restart docker  

# nohup kube-apiserver --insecure-bind-address=0.0.0.0 --insecure-port=8080 --cors_allowed_origins=.* --etcd_servers=http://masterkube:2379,http://slave1kube:2379,http://slave2kube:2379 --v=1 --logtostderr=false --log_dir=/var/log/k8s/apiserver --service-cluster-ip-range=172.16.0.0/16 &  

nohup kube-apiserver --insecure-bind-address=0.0.0.0 --insecure-port=8080 --bind-address=0.0.0.0 --secure-port=443 --etcd_servers=http://10.19.132.167:2379 --service-cluster-ip-range=172.16.0.0/16 --admission-control=NamespaceAutoProvision,LimitRanger,SecurityContextDeny --log_dir=/var/log/k8s/apiserver &

# sleep 20
  
nohup kube-controller-manager --master=http://10.19.132.167:8080 --log_dir=/var/log/k8s/controller-manager &  
  
nohup kube-scheduler --master=http://10.19.132.167:8080 --logtostderr=true --log_dir=/var/log/k8s/scheduler &  


