package com.jeramtough.action.business.register;

import com.jeramtough.bean.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 15:47.
 */
public interface PhoneUserRegisterBusiness extends RegisterBusiness
{
	
	PrimaryUser createUserWithPhoneNumber(String phoneNumber,String password);
	
	ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightSmsVerificationCode,String verifiedPhoneNumber);
}
