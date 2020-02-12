docker run --name mysqltest -p 3306:3306 -v /home/hadoop/docker_data/mysql_data_test:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD='time4@FUN' -e MYSQL_DATABASE='usercenter' \
-e MYSQL_USER='xiaodong' -e MYSQL_PASSWORD='time4FUN' \
-v /home/hadoop/Gitroot/fsdemo/fsdemo-usercenter/fsdemo-common-tools/kubernetes/mysql-sql:/docker-entrypoint-initdb.d \
-d mysql:8.0
