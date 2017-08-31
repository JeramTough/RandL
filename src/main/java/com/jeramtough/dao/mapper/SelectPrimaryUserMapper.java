package com.jeramtough.dao.mapper;

import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 19:02.
 */
@Mapper
@Repository
public interface SelectPrimaryUserMapper
{
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_2)
	long getSumOfUser();
	
	@Select("SELECT gender FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE user_id = #{userId}")
	String getGenderByUserId(String userId);
	
	@Select("SELECT COUNT(username) FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE username = #{value}")
	int getTheSameValueCountForUsername(String value);
	
	@Select("SELECT COUNT(phone_number) FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE phone_number = #{value}")
	int getTheSameValueCountForPhoneNumber(String value);
	
	@Select("SELECT COUNT(email) FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE email = #{value}")
	int getTheSameValueCountForEmail(String value);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE username = #{userOpenId}")
	int getEspecialQQUserCountIfExisted(String userOpenId);
	
	@Select("SELECT * FROM " + DatabaseProperty.TABLE_NAME_2 +
			" WHERE (username=#{uep} AND password=#{password}) " +
			"OR (email=#{uep} AND password=#{password}) " +
			"OR (phone_number=#{uep} AND password=#{password});")
	PrimaryUser getPrimaryUser(@Param("uep") String uep, @Param("password") String password);
	
	@Select("SELECT COUNT(*) FROM " + DatabaseProperty.TABLE_NAME_2 + " WHERE " +
			"user_id=#{userId}")
	int getUserCountByUserId(String userId);
}
