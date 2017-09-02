package com.jeramtough.dao.mapper;

import com.jeramtough.dao.db.DatabaseProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 20:17.
 */
@Mapper
@Repository
@Scope("singleton")
public interface ConfigurationMapper
{
	@Select("SELECT value FROM "+ DatabaseProperty.TABLE_NAME_1+" WHERE " +
			"name='default_surface_image_folder_path'")
	String getDefaultSurfaceImageFolderPath();
	
	@Select("SELECT value FROM "+ DatabaseProperty.TABLE_NAME_1+" WHERE "+
	"name='default_surface_image_url'")
	String getDefaultSurfaceImageUrl();
	
	@Select("SELECT value FROM "+ DatabaseProperty.TABLE_NAME_1+" WHERE "+
			"name='is_needed_sms_validation'")
	boolean getNeededSmsValidation();
	
	@Select("SELECT value FROM "+ DatabaseProperty.TABLE_NAME_1+" WHERE "+
			"name='is_needed_email_validation'")
	boolean getNeededEmailValidation();
	
	@Select("SELECT value FROM "+ DatabaseProperty.TABLE_NAME_1+" WHERE "+
			"name='system_port'")
	int getSystemPort();
}
