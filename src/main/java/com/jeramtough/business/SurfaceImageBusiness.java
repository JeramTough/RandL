package com.jeramtough.business;

import com.jeramtough.bean.responsebody.ResponseInfo;

import java.io.InputStream;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 21:59.
 */
public interface SurfaceImageBusiness
{
	/**
	 * read image file as the InputStream is returned
	 */
	InputStream readSurfaceImageFileAsInputStream(String imageName);
	
	ResponseInfo writeSurfaceImageFileToLocal(InputStream inputStream,String suffix);
}
