DROP TABLE IF EXISTS `ali_communication_configuration_tb`;
CREATE TABLE `ali_communication_configuration_tb` (
 `limiting_second` int DEFAULT NULL,
  `access_key_id` varchar(255) DEFAULT NULL,
  `access_key_secret` varchar(255) DEFAULT NULL,
  `signature` nvarchar(50) DEFAULT NULL,
  `template_code` varchar(100) DEFAULT NULL,
  `sent_code_name` varchar(100) DEFAULT NULL,
  `your_out_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  ali_communication_configuration_tb values(
120,'**********','*********','每投网','SMS_85570038','number',null);