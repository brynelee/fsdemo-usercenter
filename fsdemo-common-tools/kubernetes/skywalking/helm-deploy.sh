export SKYWALKING_RELEASE_NAME=skywalking  # change the release name according to your scenario
export SKYWALKING_RELEASE_NAMESPACE=default  # change the namespace to where you want to install SkyWalking

export REPO=skywalking

#helm repo add ${REPO} https://apache.jfrog.io/artifactory/skywalking-helm

helm install "${SKYWALKING_RELEASE_NAME}" ${REPO}/skywalking -n "${SKYWALKING_RELEASE_NAMESPACE}" \
  --set oap.image.tag=9.2.0 \
  --set oap.storageType=elasticsearch \
  --set ui.image.tag=9.2.0 \
  --set ui.service.type=NodePort \
  --set elasticsearch.imageTag=6.8.6 \
  --set elasticsearch.persistence.enabled=true \
  --set elasticsearch.volumeClaimTemplate.resources.requests.storage="5Gi" \
  --set elasticsearch.volumeClaimTemplate.storageClassName=nfs-storage

# 或者使用my-values.yaml文件
# helm install "${SKYWALKING_RELEASE_NAME}" ${REPO}/skywalking -n "${SKYWALKING_RELEASE_NAMESPACE}" -f my-values.yaml