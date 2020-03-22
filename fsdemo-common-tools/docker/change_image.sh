#!/bin/bash

docker tag xdorg1/$1 registry.cn-hangzhou.aliyuncs.com/xdorg1/$1
docker rmi xdorg1/$1