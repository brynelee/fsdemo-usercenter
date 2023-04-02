# 说明

es-service-agent.yaml 
用来在k8s部署支持es外部访问的service和endpoint

kibana-service-agent.yaml
用来在k8s部署支持kibana外部访问的service和endpoint

后来这两个可以不用，注意这个service在使用时需要带上default.svc.cluster.local的后缀

