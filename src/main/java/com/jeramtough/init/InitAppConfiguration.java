package com.jeramtough.init;

import com.jeramtough.dao.mapper.ConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.*;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by 11718
 * on 2017  九月 02 星期六 18:18.
 */
@Component
public class InitAppConfiguration implements EmbeddedServletContainerCustomizer
{
	
	private final ConfigurationMapper configurationMapper;
	
	
	@Autowired
	public InitAppConfiguration(ConfigurationMapper configurationMapper)
	{
		this.configurationMapper = configurationMapper;
	}
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container)
	{
		container.setPort(configurationMapper.getSystemPort());
	}
}
