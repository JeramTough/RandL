package com.jeramtough.service.record;

import com.jeramtough.business.record.RecordLoginBusiness;
import com.jeramtough.service.record.RecordService;
import com.jeramtough.dao.mapper.UserActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 22 Tuesday 12:33.
 */
@Service
public class RecordLoginService extends RecordService implements RecordLoginBusiness
{
	private final UserActivityMapper userActivityMapper;
	
	@Autowired
	public RecordLoginService(UserActivityMapper userActivityMapper)
	{
		this.userActivityMapper = userActivityMapper;
	}
	
	@Override
	public void reportLogin(String userId, String loginIp, String loginTime,
			String address)
	{
		new Thread(() -> {
			userActivityMapper
					.updateUserLoginActivity(userId, loginIp, loginTime, address);
		}).start();
	}
}
