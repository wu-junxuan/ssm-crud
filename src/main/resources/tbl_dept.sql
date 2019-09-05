/*
 Navicat Premium Data Transfer

 Source Server         : root_mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : ssm_crud

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 02/09/2019 11:04:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
