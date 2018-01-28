package com.jeramtough.service.register;

import com.jeramtough.business.register.QQUserRegisterBusiness;
import com.jeramtough.component.PrimaryUserFactory;
import com.jeramtough.component.qqs.QQAccessToken;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.QQConfigurationMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 15:49.
 */
@Service
public class QQUserRegisterService extends RegisterService implements QQUserRegisterBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	private final QQConfigurationMapper qqConfigurationMapper;
	
	@Autowired
	public QQUserRegisterService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper,
			SelectPrimaryUserMapper selectPrimaryUserMapper,
			QQConfigurationMapper qqConfigurationMapper)
	{
		super(applicationContext, configurationMapper, insertPrimaryUserMapper);
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
		this.qqConfigurationMapper = qqConfigurationMapper;
	}
	
	@Override
	public PrimaryUser createUserForQQUser(String qqUserOpenId,
			QQAccessToken.QQUserPrimaryInformation qqUserPrimaryInformation)
	{
		String userId = selectPrimaryUserMapper.getSumOfUser() + "";
		PrimaryUser primaryUser = PrimaryUserFactory
				.processPrimaryUser(userId, qqUserOpenId, qqUserPrimaryInformation);
		return primaryUser;
	}
	
	@Override
	public String getUserOpenIdUri()
	{
		return qqConfigurationMapper.getUserOpenIdUri();
	}
	
}
