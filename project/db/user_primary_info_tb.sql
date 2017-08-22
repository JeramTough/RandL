SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_primary_info_tb
-- ----------------------------
DROP TABLE IF EXISTS `primary_user_info_tb`;
CREATE TABLE `primary_user_info_tb` (
  `user_id` bigint(20) NOT NULL,
  `username` varchar(255)BINARY DEFAULT NULL,
  `nickname` nvarchar(255)BINARY DEFAULT NULL,
  `password` varchar(255)BINARY DEFAULT NULL,
  `gender` varchar(6)BINARY DEFAULT NULL,
  `age` int DEFAULT NULL,
  `phone_number` varchar(11)BINARY DEFAULT NULL,
  `email` varchar(100)BINARY DEFAULT NULL,
  `qq_number` varchar(30)BINARY DEFAULT NULL,
  `surface_image_url` nvarchar(255)BINARY DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  primary_user_info_tb values(
0,'root','administrator','root','male',18,
'12345678','12345678@ranl.com','1111',
'http://127.0.0.1:8080/randl/administrator.jpg'
);


