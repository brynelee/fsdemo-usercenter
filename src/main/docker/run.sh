#!/bin/sh

echo "********************************************************"
echo "Starting Transaction Agent Service ..."
echo "********************************************************"
java -Ddebug=$DEBUG_MODE                                                    \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/usercenter/@project.build.finalName@.jar
