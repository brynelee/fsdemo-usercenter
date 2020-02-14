#!/bin/bash
#set -x
#******************************************************************************
# @file    : entrypoint.sh
# @author  : xiaodong
# @date    : 2020-02-14 12:31:00
#
# @brief   : entry point for manage service start order
# history  : alpha
#******************************************************************************

: ${SLEEP_SECOND:=3}

wait_for() {
    echo Waiting for $1 to listen on $2...
    while ! nc -z $1 $2; do echo waiting...; sleep $SLEEP_SECOND; done
}

declare DEPENDS
declare CMD

while getopts "d:c:" arg
do
    case $arg in
        d)
            DEPENDS=$OPTARG
            ;;
        c)
            CMD=$OPTARG
            ;;
        ?)
            echo "unkonw argument"
            exit 1
            ;;
    esac
done

for var in ${DEPENDS//,/ }
do
    host=${var%:*}
    port=${var#*:}
    wait_for $host $port
done

eval $CMD
