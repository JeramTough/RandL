package com.jeramtough.service;

import com.jeramtough.business.SurfaceImageBusiness;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.util.IdUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 22:01.
 */
@Service
public class SurfaceImageService extends MyService implements SurfaceImageBusiness
{
	
	public SurfaceImageService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper)
	{
		super(applicationContext, configurationMapper);
	}
	
	@Override
	public InputStream readSurfaceImageFileAsInputStream(String imageName)
	{
		String path = getConfigurationMapper().getDefaultSurfaceImageFolderPath();
		Resource resource = getApplicationContext().getResource("file:///" + path + imageName);
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
	
	@Override
	public ResponseInfo writeSurfaceImageFileToLocal(InputStream inputStream, String suffix)
	{
		String path = getConfigurationMapper().getDefaultSurfaceImageFolderPath();
		
		//process the surfaceImage name
		String imageFileName = IdUtil.getUUID() + IdUtil.randomNumber(0, 99, 2) + "." +
				suffix;
		
		try
		{
			StreamUtils.copy(inputStream, new FileOutputStream(new File(path+imageFileName)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new ResponseInfo(106, e.getMessage());
		}
		
		String defaultSurfaceImageUrl=getConfigurationMapper().getDefaultSurfaceImageUrl();
		String url=defaultSurfaceImageUrl+"?name="+imageFileName;
		
		return new OkResponseInfo("头像文件上传成功！头像URL="+url);
	}
}
