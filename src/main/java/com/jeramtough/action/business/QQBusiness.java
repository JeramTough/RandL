package com.jeramtough.action.business;

import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 18:34.
 */
public interface QQBusiness
{
	/**
	 * whether user have registered
	 *
	 * @param userOpenId the open id of user
	 * @return  return true if qq user have registered
	 */
	boolean isHaveRegisteredForQQUser(String userOpenId);
	
	/**
	 * register a qq user to persistent layer
	 * @param primaryUser the primary user object is create by information of qq user.
	 */
	void registerAQQUser(PrimaryUser primaryUser);
}
