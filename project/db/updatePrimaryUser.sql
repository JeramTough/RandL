-- ----------------------------
-- Procedure structure for updatePrimaryUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `updatePrimaryUser`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePrimaryUser`(IN `user_id` bigint(20), IN `username` varchar(255),IN `nickname` varchar(255),IN `password` varchar(255),IN `gender` varchar(6),IN `age` int, IN `phone_number` varchar(11),IN `email` varchar(100),IN `qq_number` varchar(30),IN `surface_image_url` varchar(255))
BEGIN

	#用户名
	set @a=(SELECT username FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.username=username WHERE primary_user_info_tb.user_id=user_id;
  END IF;

	#昵称
	set @a=(SELECT nickname FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.nickname=nickname WHERE primary_user_info_tb.user_id=user_id;
  END IF; 

	#密码
	set @a=(SELECT password FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.password=password WHERE primary_user_info_tb.user_id=user_id;
  END IF; 


	#性别
	set @a=(SELECT gender FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.gender=gender WHERE primary_user_info_tb.user_id=user_id;
  END IF; 


	#年龄
	set @a=(SELECT age FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.age=age WHERE primary_user_info_tb.user_id=user_id;
  END IF; 


	#手机号码
	set @a=(SELECT phone_number FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.username=phone_number 
		WHERE (primary_user_info_tb.user_id=user_id AND primary_user_info_tb.username=primary_user_info_tb.phone_number);

		UPDATE primary_user_info_tb SET primary_user_info_tb.phone_number=phone_number WHERE primary_user_info_tb.user_id=user_id;
  END IF; 


	#邮箱
	set @a=(SELECT email FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.username=email 
		WHERE (primary_user_info_tb.user_id=user_id AND primary_user_info_tb.username=primary_user_info_tb.email);

		UPDATE primary_user_info_tb SET primary_user_info_tb.email=email WHERE primary_user_info_tb.user_id=user_id;
  END IF; 


	#QQ号码
	set @a=(SELECT qq_number FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.qq_number=qq_number WHERE primary_user_info_tb.user_id=user_id;
  END IF; 

	#头像URL
	set @a=(SELECT surface_image_url FROM randl_db.primary_user_info_tb WHERE primary_user_info_tb.user_id=user_id);  
	IF @a IS NOT NULL
  THEN
		UPDATE primary_user_info_tb SET primary_user_info_tb.surface_image_url=surface_image_url WHERE primary_user_info_tb.user_id=user_id;
  END IF;




	SELECT * FROM primary_user_info_tb;


END
;;
DELIMITER ;
