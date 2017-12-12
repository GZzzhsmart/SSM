/*
Navicat MySQL Data Transfer

Source Server         : MySQL连接
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-12 15:35:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cash
-- ----------------------------
DROP TABLE IF EXISTS `t_cash`;
CREATE TABLE `t_cash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `money` decimal(10,2) DEFAULT NULL,
  `cash_time` datetime DEFAULT NULL,
  `cash_customer` varchar(100) DEFAULT NULL,
  `cash_type` bigint(20) DEFAULT NULL,
  `pay_type` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cash
-- ----------------------------
INSERT INTO `t_cash` VALUES ('1', '1000.00', '2017-12-08 10:54:07', '小王', '3', '1', '1', '2017-12-09 10:54:53');
INSERT INTO `t_cash` VALUES ('2', '500.00', '2017-12-08 10:56:12', '小李', '7', '2', '1', '2017-12-10 10:56:47');
INSERT INTO `t_cash` VALUES ('3', '100.00', '2017-12-08 10:57:30', '小美', '5', '3', '1', '2017-12-12 10:57:53');

-- ----------------------------
-- Table structure for t_cash_type
-- ----------------------------
DROP TABLE IF EXISTS `t_cash_type`;
CREATE TABLE `t_cash_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cash_type
-- ----------------------------
INSERT INTO `t_cash_type` VALUES ('1', '0', '收入', '收入');
INSERT INTO `t_cash_type` VALUES ('2', '0', '支出', '支出');
INSERT INTO `t_cash_type` VALUES ('3', '1', '项目收入', '项目收入');
INSERT INTO `t_cash_type` VALUES ('4', '1', '稿费', '稿费');
INSERT INTO `t_cash_type` VALUES ('5', '2', '餐饮支出', '餐饮支出');
INSERT INTO `t_cash_type` VALUES ('6', '2', '旅游支出', '旅游支出');
INSERT INTO `t_cash_type` VALUES ('7', '2', '活动支出', '活动支出');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` varchar(100) DEFAULT NULL,
  `is_online` tinyint(4) DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('1', '1', '2017-12-12 15:21:23', '127.0.0.1', '0', '2017-12-12 15:22:15');
INSERT INTO `t_login_log` VALUES ('2', '1', '2017-12-12 15:26:28', '127.0.0.1', '1', null);

-- ----------------------------
-- Table structure for t_pay_type
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_type`;
CREATE TABLE `t_pay_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pay_type
-- ----------------------------
INSERT INTO `t_pay_type` VALUES ('1', '微信支付');
INSERT INTO `t_pay_type` VALUES ('2', '支付宝支付');
INSERT INTO `t_pay_type` VALUES ('3', '银行转账');
INSERT INTO `t_pay_type` VALUES ('4', '现金');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `realname` varchar(10) NOT NULL,
  `reg_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '18720865791', '4QrcOUm6Wau+VuBX8g+IPg==', '曾志湖', '2017-12-07 14:53:15');
INSERT INTO `t_user` VALUES ('2', '13177619167', '4QrcOUm6Wau+VuBX8g+IPg==', '王美丽', '2017-12-11 19:34:01');
