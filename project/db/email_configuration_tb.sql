DROP TABLE IF EXISTS `email_configuration_tb`;
CREATE TABLE `email_configuration_tb` (
  `host` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `default_encoding` varchar(20) DEFAULT NULL,
  `subject` nvarchar(100) DEFAULT NULL,
  `from_email` varchar(255) DEFAULT NULL,
  `registered_text` text DEFAULT NULL,
  `password_retaked_text` text DEFAULT NULL,
  `port` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  email_configuration_tb values(
'smtp.qq.com','1171867004@qq.com',
'eptnxsifwctwjieg','UTF-8',
'每投网验证码',
'1171867004@qq.com',
'您注册用户的激活码为%s',
'您找回密码的验证码为%s',
587);