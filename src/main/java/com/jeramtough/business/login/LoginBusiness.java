package com.jeramtough.business.login;

import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 15:38.
 */
public interface LoginBusiness
{
	PrimaryUser getPrimaryUserByUepAndPassword(String uep,String password);
	
	ResponseInfo getResponseInfoByPrimaryUser(PrimaryUser primaryUser);
}
