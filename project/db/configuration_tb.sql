/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : randl_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-11 20:18:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for configuration_tb
-- ----------------------------
DROP TABLE IF EXISTS `configuration_tb`;
CREATE TABLE `configuration_tb` (
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of configuration_tb
-- ----------------------------
INSERT INTO  configuration_tb values(
'default_surface_image_folder_path','D:/Developer/My codes/Intellij IDEA codes/RandL/surface images/');

INSERT INTO  configuration_tb values(
'default_surface_image_url','http://localhost:8666/randl/surfaceImage');

INSERT INTO  configuration_tb values(
'is_needed_sms_validation','true');

INSERT INTO  configuration_tb values(
'is_needed_email_validation','true');

INSERT INTO  configuration_tb values(
'system_port','8666');

