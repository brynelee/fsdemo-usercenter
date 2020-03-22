SET NAMES utf8mb4;

/*
set global validate_password.policy=LOW;
set global validate_password.length=6;
*/

/* fixed the mysql 8 access issue */
USE mysql;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'time4@FUN';
CREATE USER 'xiaodong'@'%' IDENTIFIED BY 'time4@FUN';
ALTER USER 'xiaodong'@'%' IDENTIFIED WITH mysql_native_password BY 'time4@FUN';
CREATE DATABASE If NOT EXISTS usercenter CHARACTER SET utf8mb4;
CREATE DATABASE If NOT EXISTS transproddb CHARACTER SET utf8mb4;
CREATE DATABASE IF NOT EXISTS productprice CHARACTER SET utf8mb4;
CREATE DATABASE IF NOT EXISTS oauth2db CHARACTER SET utf8mb4;
GRANT ALL PRIVILEGES ON usercenter.* TO 'xiaodong'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON transproddb.* TO 'xiaodong'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON productprice.* TO 'xiaodong'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON oauth2db.* TO 'xiaodong'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
flush privileges;

USE usercenter;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token_creation_time` datetime DEFAULT NULL COMMENT 'Token创建时间',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码文本',
  `status` int(10) NOT NULL COMMENT '状态，1为正常',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子邮件',
  `personal_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号码',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

/*
INSERT INTO `usertable` VALUES ('xiaodong', '123456', 1, '4po7F0CRjUd4w7Th9Ng2RlPhQMV4h6BZ1eB29wsWRBAhzUiYsvfJxoIsFYvk7cV3BhiQAyXgeKksUgMIMHLzeMwDBIpdvU7xB0P74Ro3kBQZtj7uPJcEhpouUp3dZN3WRGHM39Ai3D8ujnqLIP9uI3FynRDGUeqQrwYD4c5GymeDgLu4qZaro4IvgOhkZixKR6Kh0VCylNRi5JD0AqsDweHQVP8ZC97e7mEQlf8t2y75zEWEVRJ8tYsbQzc3kJkV', NULL);
INSERT INTO `usertable` VALUES ('dahai', '666666', 2, NULL, NULL);
INSERT INTO `usertable` VALUES ('hesheng', '888888', 3, 'ecLoIQ7necFnKs9Kmrxn95HwQLNLGs3K4yN2Fse2xcDn1Xkfe3eBdqk7NuFStR8kytwLJKJIFXBAXGaM56tLANYgaqYEJJtDEthlrY9CP5S9a1Bca4BIVYxzCpiDTs6Tm02nawY9nbRTt4a6IUNSoGjjn0xsalkaIKpqPHTzBmubCulCki1poB781XUeTSMRzk3DIzGkdJzhkxO0ScdKIxH0mNq8ndozyqh3S0fU7z0K95QuYdawv6HXECow1IOZ', NULL);
INSERT INTO `usertable` VALUES ('xiaoming', '999999', 4, NULL, NULL);
INSERT INTO `usertable` VALUES ('xiaohu', '000000', 6, NULL, NULL);
*/

INSERT INTO `usertable` VALUES ('xiaodong','123456',1,NULL,NULL,'13088888888',1,'bryne_lxd@sina.com','110101197512128888'),('dahai','666666',2,'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ3cml0ZSJdLCJleHAiOjE1ODQ4OTQzMTUsImp0aSI6IjcyNzJiMDYzLTQ5NWMtNDlhMi1iMjQ1LWM5MDE2Nzc0NmY4OCIsImNsaWVudF9pZCI6ImZzZGVtby11c2VyY2VudGVyIn0._7PHPV2VQikbJddqpm4BKvKSd8ShRJYC9JWL_R7XWxM',NULL,'13088888889',1,'dahai_88889999@sina.com','110101197812128889'),('hesheng','888888',3,'ecLoIQ7necFnKs9Kmrxn95HwQLNLGs3K4yN2Fse2xcDn1Xkfe3eBdqk7NuFStR8kytwLJKJIFXBAXGaM56tLANYgaqYEJJtDEthlrY9CP5S9a1Bca4BIVYxzCpiDTs6Tm02nawY9nbRTt4a6IUNSoGjjn0xsalkaIKpqPHTzBmubCulCki1poB781XUeTSMRzk3DIzGkdJzhkxO0ScdKIxH0mNq8ndozyqh3S0fU7z0K95QuYdawv6HXECow1IOZ',NULL,'13088888890',1,'hesheng_88889999@sina.com','110101197812128890'),('xiaoming','999999',4,NULL,NULL,'13088888891',1,'xiaoming_88889999@sina.com','110101197812128891'),('xiaohu','000000',6,NULL,NULL,'13088888892',1,'xiaohu_88889999@163.com','110101197812128892');

SET FOREIGN_KEY_CHECKS = 1;
