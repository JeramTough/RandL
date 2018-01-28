package com.jeramtough.business.register;

import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 23:49.
 */
public interface RegisterBusiness
{
	ResponseInfo savePrimaryUserToPersistentLayer(PrimaryUser primaryUser);
}
