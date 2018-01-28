package com.jeramtough.service;

import com.jeramtough.business.WriteUserInfoBusiness;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 10:31.
 */
@Service
public class WriteUserInfoService implements WriteUserInfoBusiness
{
	private final InsertPrimaryUserMapper insertPrimaryUserMapper;
	
	@Autowired
	public WriteUserInfoService(InsertPrimaryUserMapper insertPrimaryUserMapper)
	{
		this.insertPrimaryUserMapper = insertPrimaryUserMapper;
	}
	
	@Override
	public void writePrimaryUserInfoToPersistentLayer(PrimaryUser primaryUser)
	{
		insertPrimaryUserMapper.insertPrimaryUser(primaryUser);
	}
}
