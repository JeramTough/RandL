package com.jeramtough.action.business.register;

import com.jeramtough.bean.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 23:47.
 */
public interface PrimaryUserRegisterBusiness extends RegisterBusiness
{
	/**
	 * @param registerInfo    the RegisterInfo Object about information of registering
	 */
	PrimaryUser createUserWithRegisterInfo(RegisterInfo registerInfo);
	
	ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightSmsVerificationCode,String rightEmailVerificationCode,String
			verifiedPhoneNumber,String verifiedEmailAddress);
}
