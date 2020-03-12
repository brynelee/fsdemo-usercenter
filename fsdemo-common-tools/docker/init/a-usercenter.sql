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
CREATE TABLE `usertable`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `token` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token_creation_time` datetime(0) DEFAULT NULL COMMENT 'Token创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `usertable` VALUES ('xiaodong', '123456', 1, '4po7F0CRjUd4w7Th9Ng2RlPhQMV4h6BZ1eB29wsWRBAhzUiYsvfJxoIsFYvk7cV3BhiQAyXgeKksUgMIMHLzeMwDBIpdvU7xB0P74Ro3kBQZtj7uPJcEhpouUp3dZN3WRGHM39Ai3D8ujnqLIP9uI3FynRDGUeqQrwYD4c5GymeDgLu4qZaro4IvgOhkZixKR6Kh0VCylNRi5JD0AqsDweHQVP8ZC97e7mEQlf8t2y75zEWEVRJ8tYsbQzc3kJkV', NULL);
INSERT INTO `usertable` VALUES ('dahai', '666666', 2, NULL, NULL);
INSERT INTO `usertable` VALUES ('hesheng', '888888', 3, 'ecLoIQ7necFnKs9Kmrxn95HwQLNLGs3K4yN2Fse2xcDn1Xkfe3eBdqk7NuFStR8kytwLJKJIFXBAXGaM56tLANYgaqYEJJtDEthlrY9CP5S9a1Bca4BIVYxzCpiDTs6Tm02nawY9nbRTt4a6IUNSoGjjn0xsalkaIKpqPHTzBmubCulCki1poB781XUeTSMRzk3DIzGkdJzhkxO0ScdKIxH0mNq8ndozyqh3S0fU7z0K95QuYdawv6HXECow1IOZ', NULL);
INSERT INTO `usertable` VALUES ('xiaoming', '999999', 4, NULL, NULL);
INSERT INTO `usertable` VALUES ('xiaohu', '000000', 6, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
