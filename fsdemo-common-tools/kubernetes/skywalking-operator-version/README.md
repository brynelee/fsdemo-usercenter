
# configuration
1. this version is using skywalking swck operator to inject agent into the k8s Deployment/Pod
2. the configuration of the skywalking deployment is using the ELK service in the xdt440sk8snode1,2,3 cluster which is either accessable by elasticsearch-agent-svc & elasticsearch-agent-svc(ep) or by coreDNS routing;
3. this other configuration can refer to the fsdemo-usercenter/fsdemo-common-tools/kubernetes/skywalking/ directory.




