#!/bin/sh

echo "********************************************************"
echo "Starting Transaction Agent Service ..."
echo "********************************************************"
java -Dspring.datasource.url=$SPRINGDATASOURCEURL -jar /usr/src/app/fsdemo-usercenter-0.0.1-SNAPSHOT.jar
