package com.jeramtough.service;

import com.jeramtough.business.QQBusiness;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 18:48.
 */
@Service
public class QQService implements QQBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	private final InsertPrimaryUserMapper insertPrimaryUserMapper;
	
	@Autowired
	public QQService(SelectPrimaryUserMapper selectPrimaryUserMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper)
	{
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
		this.insertPrimaryUserMapper = insertPrimaryUserMapper;
	}
	
	@Override
	public boolean isHaveRegisteredForQQUser(String userOpenId)
	{
		return selectPrimaryUserMapper.getEspecialQQUserCountIfExisted(userOpenId) > 0;
	}
	
	@Override
	public void registerAQQUser(PrimaryUser primaryUser)
	{
		primaryUser.setUserId(selectPrimaryUserMapper.getSumOfUser()+"");
		insertPrimaryUserMapper.insertPrimaryUser(primaryUser);
	}
}
