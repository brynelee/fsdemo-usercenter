USE mysql;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'time4@FUN';
ALTER USER 'xiaodong'@'%' IDENTIFIED WITH mysql_native_password BY 'time4@FUN';
GRANT ALL PRIVILEGES ON usercenter.* TO 'xiaodong'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
flush privileges;

