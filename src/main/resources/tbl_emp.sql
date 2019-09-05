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

 Date: 02/09/2019 11:04:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_emp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `gender` char(1) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_emp_dept` (`d_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `tbl_dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
