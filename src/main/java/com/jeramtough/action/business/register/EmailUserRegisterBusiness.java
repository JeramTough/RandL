package com.jeramtough.action.business.register;

import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 16:34.
 */
public interface EmailUserRegisterBusiness extends RegisterBusiness
{
	PrimaryUser createUserWithEmail(String email,String password);
	
	ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightEmailVerificationCode,String verifiedEmailAddress);
}
