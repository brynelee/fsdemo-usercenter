# This Dockerfile is used to be combined with maven tools after maven done package phase
# The Docker file in the component root directory is used with hub.docker.com for complete maven build procedure and docker image packaging
FROM bryne/spmiabaseimage
RUN mkdir -p /usr/local/usercenter
ADD @project.build.finalName@.jar /usr/local/usercenter/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh