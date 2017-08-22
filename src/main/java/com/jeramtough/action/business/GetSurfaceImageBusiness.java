package com.jeramtough.action.business;

import java.io.InputStream;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 21:59.
 */
public interface GetSurfaceImageBusiness
{
	/**
	 * read image file as the InputStream is returned
	 */
	InputStream readSurfaceImageFileAsInputStream(String imageName);
}
