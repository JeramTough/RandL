package com.jeramtough.dao.mapper;

import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 09:56.
 */
@Mapper
@Repository
public interface InsertPrimaryUserMapper
{
	@Insert("INSERT INTO "+ DatabaseProperty.TABLE_NAME_2+" values(" +
			"#{userId},#{username},#{nickname}," +
			"#{password},#{gender},#{age}," +
			"#{phoneNumber},#{email},#{qqNumber}," +
			"#{surfaceImageUrl})")
	public void insertPrimaryUser(PrimaryUser primaryUser);
}
