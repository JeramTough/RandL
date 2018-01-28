package com.jeramtough.init;

import com.jeramtough.dao.mapper.QQConfigurationMapper;
import com.qq.connect.utils.QQConnectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 17:45.
 */
@Component
public class InitQQConfiguration implements ApplicationListener<ContextRefreshedEvent>
{
	private final QQConfigurationMapper qqConfigurationMapper;
	
	@Autowired
	public InitQQConfiguration(QQConfigurationMapper qqConfigurationMapper)
	{
		this.qqConfigurationMapper = qqConfigurationMapper;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		QQConnectConfig.updateProperties("app_ID", qqConfigurationMapper.getAppId());
		QQConnectConfig.updateProperties("app_KEY", qqConfigurationMapper.getAppKey());
		QQConnectConfig 
				.updateProperties("redirect_URI", qqConfigurationMapper.getRedirectUri());
		/*Properties properties = new Properties();
		Resource resource = event.getApplicationContext()
				.getResource("classpath:qqconnectconfig.properties");
		try
		{
			String path = resource.getURI().getPath();
			
			InputStream inputStream = resource.getInputStream();
			properties.load(inputStream);
			
			QQConnectConfig.updateProperties("app_ID", qqConfigurationMapper.getAppId());
			QQConnectConfig.updateProperties("app_KEY",qqConfigurationMapper.getAppKey());
			QQConnectConfig.updateProperties("app_ID", qqConfigurationMapper.getAppId());
			
			*//*properties.setProperty("app_ID", qqConfigurationMapper.getAppId());
			properties.setProperty("app_KEY",qqConfigurationMapper.getAppKey());
			properties.setProperty("redirect_URI",qqConfigurationMapper.getRedirectUri());
			
			OutputStream outputStream=new FileOutputStream(new File(path));
			properties.store(outputStream,"");*//*
			
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
	}
	
}
