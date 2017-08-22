package com.jeramtough.dao.mapper;

import com.jeramtough.bean.EmailConfiguration;
import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 14:17.
 */
@Mapper
@Repository
public interface EmailConfigurationMapper
{
	@Select("SELECT * FROM " + DatabaseProperty.TABLE_NAME_4)
	public EmailConfiguration selectEmailConfiguration();
}
