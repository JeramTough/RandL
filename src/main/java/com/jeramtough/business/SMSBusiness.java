package com.jeramtough.business;

import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 17:48.
 */
public interface SMSBusiness
{
	/**
	 * process a verification code to check availability of phone number.
	 * @return  the verification code
	 */
	String processVerificationCode();
	
	/**
	 * @param code the verification code
	 * @param phoneNumber the phone number of user
	 * @return the message of response
	 */
	ResponseInfo sendVerificationCodeToPhone(String code,String phoneNumber);
	
	
}
