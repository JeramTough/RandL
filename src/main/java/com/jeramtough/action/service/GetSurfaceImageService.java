package com.jeramtough.action.service;

import com.jeramtough.action.business.GetSurfaceImageBusiness;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 22:01.
 */
@Service
public class GetSurfaceImageService extends MyService implements GetSurfaceImageBusiness
{
	
	public GetSurfaceImageService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper)
	{
		super(applicationContext, configurationMapper);
	}
	
	@Override
	public InputStream readSurfaceImageFileAsInputStream(String imageName)
	{
		String path=getConfigurationMapper().getDefaultSurfaceImageFolderPath();
		Resource resource = getApplicationContext().getResource("file:///"+path+imageName);
		try
		{
			return resource.getInputStream();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
