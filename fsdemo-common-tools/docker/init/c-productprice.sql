/*
 Navicat Premium Data Transfer

 Source Server         : root@xdubuntu1810
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : xdubuntu1810:3306
 Source Schema         : productprice

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 19/02/2020 18:33:05
*/

SET NAMES utf8mb4;
USE productprice;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for productpricetable
-- ----------------------------
DROP TABLE IF EXISTS `productpricetable`;
CREATE TABLE `productpricetable`  (
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '产品ID',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产品名称',
  `product_price` decimal(10, 2) DEFAULT NULL COMMENT '产品价格',
  `update_time` datetime(0) DEFAULT NULL COMMENT '产品价格更新时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productpricetable
-- ----------------------------
INSERT INTO `productpricetable` VALUES (1, 'win1', 2.50, NULL);
INSERT INTO `productpricetable` VALUES (2, 'win2', 3.10, '2020-02-17 16:37:30');
INSERT INTO `productpricetable` VALUES (3, 'win3', 50.00, NULL);
INSERT INTO `productpricetable` VALUES (4, 'win4', NULL, '2020-02-16 16:37:20');
INSERT INTO `productpricetable` VALUES (5, 'win5', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
