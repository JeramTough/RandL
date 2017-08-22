package com.jeramtough.action.business;

import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 14:04.
 */
public interface EmailBusiness
{
	/**
	 * process a verification code to check availability of email.
	 * @return  the verification code
	 */
	String processVerificationCode();
	
	/**
	 * @param code the verification code
	 * @param toEmail the email address of user
	 * @return the message of response
	 */
	ResponseInfo sendVerificationCodeToEmail(String code,String toEmail);
}
