/*
 Navicat Premium Data Transfer

 Source Server         : root@xdubuntu1810
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : xdubuntu1810:3306
 Source Schema         : transproddb

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 13/02/2020 19:51:00
*/

SET NAMES utf8mb4;

USE transproddb;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `total_amount` int(10) UNSIGNED NOT NULL COMMENT '总份额',
  `amount_sold` int(10) UNSIGNED NOT NULL COMMENT '已销售份额',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `institution_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构编码',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品一级分类',
  `unit_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '份额单价',
  `isDynamicPrice` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否是动态价格',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


INSERT INTO `product` VALUES (1, 'win1', 500000, 0, '天天赢1号', '000001', 'retail-personal', 1.50, 0);
INSERT INTO `product` VALUES (2, 'win2', 100000, 0, '日日盈1号', '000002', 'retail-private', 2.00, 1);
INSERT INTO `product` VALUES (3, 'win3', 500000, 0, '天天赢2号', '000003', 'retail-sme', 1.20, 1);
INSERT INTO `product` VALUES (4, 'win4', 1000000, 0, '日日盈2号', '000002', 'retail-private', 3.00, 0);
INSERT INTO `product` VALUES (5, 'win5', 5000000, 0, '天天赢3号', '000003', 'retail-sme', 1.40, 0);

SET FOREIGN_KEY_CHECKS = 1;
