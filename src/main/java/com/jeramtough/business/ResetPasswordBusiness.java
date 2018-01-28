package com.jeramtough.business;

import com.jeramtough.bean.requestbody.ResetPasswordInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 16:20.
 */
public interface ResetPasswordBusiness
{
	ResponseInfo checkVerificationCode(ResetPasswordInfo resetPasswordInfo,
			String verifiedPhoneNumber, String verifiedEmailAddress,
			String rightSmsVerificationCode, String rightEmailVerificationCode);
	
	void modifyPassword(ResetPasswordInfo resetPasswordInfo,
			String phoneNumber, String emailAddress);
}
