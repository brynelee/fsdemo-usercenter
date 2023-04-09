export SKYWALKING_RELEASE_VERSION=4.3.0  # change the release version according to your need
export SKYWALKING_RELEASE_NAME=skywalking  # change the release name according to your scenario
export SKYWALKING_RELEASE_NAMESPACE=default  # change the namespace to where you want to install SkyWalking

helm install "${SKYWALKING_RELEASE_NAME}" \
oci://registry-1.docker.io/apache/skywalking-helm \
-n "${SKYWALKING_RELEASE_NAMESPACE}" \
-f ./values-my-es.yaml

