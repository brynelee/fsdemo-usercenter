#!/bin/sh

echo "********************************************************"
echo "Starting Transaction Agent Service ..."
echo "********************************************************"
java -Ddebug=$DEBUG_MODE \
     -Dserver.port=$SERVERPORT \
     -Dspring.datasource.url=$SPRINGDATASOURCEURL \
     -Dspring.datasource.username=$USERNAME \
     -Dspring.datasource.password=$PASSWORD -jar /usr/local/usercenter/@project.build.finalName@.jar
