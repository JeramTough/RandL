package com.jeramtough.service.record;

import com.jeramtough.business.record.RecordRegisterBusiness;
import com.jeramtough.dao.mapper.UserActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 12:49.
 */
@Service
public class RecordRegisterService extends RecordService implements RecordRegisterBusiness
{
	private final UserActivityMapper userActivityMapper;
	
	@Autowired
	public RecordRegisterService(UserActivityMapper userActivityMapper)
	{
		this.userActivityMapper = userActivityMapper;
	}
	
	
	@Override
	public void reportRegister(String userId, String registerIp, String registerTime,
			String address)
	{
		new Thread(() -> {
			userActivityMapper
					.insertUserRegisterActivity(userId, registerIp, registerTime, address);
		}).start();
		
	}
}
