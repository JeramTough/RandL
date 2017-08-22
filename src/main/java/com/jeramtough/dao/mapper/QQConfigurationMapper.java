package com.jeramtough.dao.mapper;

import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 17:49.
 */

@Mapper
@Repository
public interface QQConfigurationMapper
{
	@Select("SELECT app_id FROM "+ DatabaseProperty.TABLE_NAME_5)
	String getAppId();
	
	@Select("SELECT app_key FROM "+ DatabaseProperty.TABLE_NAME_5)
	String getAppKey();
	
	@Select("SELECT redirect_uri FROM "+ DatabaseProperty.TABLE_NAME_5)
	String getRedirectUri();
	
	@Select("SELECT get_user_open_id_uri FROM "+ DatabaseProperty.TABLE_NAME_5)
	String getUserOpenIdUri();
}
