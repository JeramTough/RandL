package com.jeramtough.dao.mapper;

import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 12:50.
 */
@Mapper
@Repository
public interface UserActivityMapper
{
	
	
	@Insert("INSERT INTO " + DatabaseProperty.TABLE_NAME_6 + " values(" +
			"#{userId},0,#{registerIp}," +
			"#{registerTime},#{registerAddress},null,null,null,null)")
	public void insertUserRegisterActivity(@Param("userId") String userId,
			@Param("registerIp") String registerIp, @Param("registerTime") String registerTime,
			@Param("registerAddress") String registerAddress);
	
	@Insert("UPDATE " + DatabaseProperty.TABLE_NAME_6 + " SET is_online=1, " +
			"login_ip=#{loginIp}," + "login_time=#{loginTime},login_address=#{loginAddress}," +
			"last_logined_time=#{loginTime} WHERE user_id=#{userId}")
	public void updateUserLoginActivity(@Param("userId") String userId,
			@Param("loginIp") String loginIp, @Param("loginTime") String loginTime,
			@Param("loginAddress") String loginAddress);
}
