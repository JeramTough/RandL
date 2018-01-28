package com.jeramtough.component.inspector;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 0:57.
 */
public interface UserInfoInspector
{
	int checkFormat();
	
	int checkSmsVerificationCode(String rightSmsVerificationCode, String verifiedPhoneNumber);
	
	int checkEmailVerificationCode(String rightEmailVerificationCode, String
			verifiedEmailAddress);
}
