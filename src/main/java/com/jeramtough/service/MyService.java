package com.jeramtough.service;

import com.jeramtough.dao.mapper.ConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 22:31.
 */
public class MyService
{
	private final WebApplicationContext applicationContext;
	private final ConfigurationMapper configurationMapper;
	
	@Autowired
	public MyService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper)
	{
		this.applicationContext = applicationContext;
		this.configurationMapper = configurationMapper;
	}
	
	public WebApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
	
	public ConfigurationMapper getConfigurationMapper()
	{
		return configurationMapper;
	}
}
