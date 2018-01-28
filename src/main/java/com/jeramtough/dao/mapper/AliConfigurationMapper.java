package com.jeramtough.dao.mapper;

import com.jeramtough.component.ali.AliCommunicationConfig;
import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 18:26.
 */
@Mapper
@Repository
public interface AliConfigurationMapper
{
	@Select("SELECT * FROM " + DatabaseProperty.TABLE_NAME_3)
	AliCommunicationConfig getAliCommunicationConfiguration();
	
	@Select("SELECT limiting_second FROM " + DatabaseProperty.TABLE_NAME_3)
	int getLimitingSecond();
}
