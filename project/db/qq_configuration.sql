DROP TABLE IF EXISTS `qq_configuration_tb`;
CREATE TABLE `qq_configuration_tb` (
  `app_id` varchar(255) DEFAULT NULL,
  `app_key` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `get_user_open_id_uri` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  qq_configuration_tb values(
'101378587',
'6055d7c613c82d53708c430015e3ffbd',
'http://meitou.org.cn:8080/randl/register/qqUsers',
'http://meitou.org.cn:8080/randl/getUserOpenId');