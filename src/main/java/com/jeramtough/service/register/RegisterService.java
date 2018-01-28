package com.jeramtough.service.register;

import com.jeramtough.business.register.RegisterBusiness;
import com.jeramtough.service.MyService;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 23:54.
 */
@Service
public class RegisterService extends MyService implements RegisterBusiness
{
	private final InsertPrimaryUserMapper insertPrimaryUserMapper;
	
	@Autowired
	public RegisterService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper)
	{
		super(applicationContext, configurationMapper);
		this.insertPrimaryUserMapper = insertPrimaryUserMapper;
	}
	
	@Override
	public ResponseInfo savePrimaryUserToPersistentLayer(PrimaryUser primaryUser)
	{
		insertPrimaryUserMapper.insertPrimaryUser(primaryUser);
		return new OkResponseInfo("用户" + primaryUser.getUserId() + "注册成功");
	}
}
