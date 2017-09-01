package com.jeramtough.dao.mapper;

import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 14:41.
 */
@Mapper
@Repository
public interface UpdatePrimaryUserMapper
{
	
	@Update("call randl_db.updatePrimaryUser(#{userId},#{username},#{nickname},#{password}," +
			"#{gender},#{age},#{phoneNumber},#{email},#{qqNumber},#{surfaceImageUrl});")
	void updatePrimaryUser(@Param("userId") Long userId, @Param("username") String username,
			@Param("nickname") String nickname, @Param("password") String password,
			@Param("gender") String gender, @Param("age") Integer age,
			@Param("phoneNumber") String phoneNumber, @Param("email") String email,
			@Param("qqNumber") String qqNumber,
			@Param("surfaceImageUrl") String surfaceImageUrl);
	
	
	@Update("UPDATE " + DatabaseProperty.TABLE_NAME_2 + " SET password=#{newPassword} WHERE " +
			"phone_number=#{phoneNumber}")
	void updateUserPasswordByPhoneNumber(@Param("newPassword") String newPassword,
			@Param("phoneNumber") String phoneNumber);
	
	
	@Update("UPDATE " + DatabaseProperty.TABLE_NAME_2 + " SET password=#{newPassword} WHERE " +
			"email=#{emailAddress}")
	void updateUserPasswordByEmail(@Param("newPassword") String newPassword,
			@Param("emailAddress") String emailAddress);
}
