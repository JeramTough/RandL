/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : randl_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-19 23:55:58
*/


-- ----------------------------
-- Table structure for user_activity_table
-- ----------------------------
DROP TABLE IF EXISTS `user_activity_tb`;
CREATE TABLE `user_activity_tb` (
  `user_id` bigint(20) NOT NULL,
  `is_online` int(10) DEFAULT NULL,
  `register_ip` varchar(255) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `register_address` varchar(255) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_address` varchar(255) DEFAULT NULL,
  `last_logined_time` datetime DEFAULT NULL,
	FOREIGN KEY (user_id) REFERENCES randl_db.primary_user_info_tb (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_activity_tb VALUES
(0,0,'192.168.0.1','2017-08-27 16:15','广西',null,null,null,null);


/*
,
  FOREIGN KEY (user_id) REFERENCES primary_user_info_table (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
*/