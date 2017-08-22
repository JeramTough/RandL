package com.jeramtough.action.business;

import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 10:29.
 */
public interface WriteUserInfoBusiness
{
	/**
	 * write information of primary user to persistent layer
	 */
	void writePrimaryUserInfoToPersistentLayer(PrimaryUser primaryUser);
}
