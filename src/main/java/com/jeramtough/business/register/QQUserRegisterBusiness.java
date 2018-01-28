package com.jeramtough.business.register;

import com.jeramtough.component.qqs.QQAccessToken;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 15:46.
 */
public interface QQUserRegisterBusiness extends RegisterBusiness
{
	PrimaryUser createUserForQQUser(String qqUserOpenId,QQAccessToken
			.QQUserPrimaryInformation qqUserPrimaryInformation);
	
	String getUserOpenIdUri();
}
