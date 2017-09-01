package com.jeramtough.bean.requestbody;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 16:56.
 */
public class ResetPasswordInfo
{
	
	private String smsVerificationCode;
	
	private String emailVerificationCode;
	
	private String newPassword;
	
	
	public String getSmsVerificationCode()
	{
		return smsVerificationCode;
	}
	
	public void setSmsVerificationCode(String smsVerificationCode)
	{
		this.smsVerificationCode = smsVerificationCode;
	}
	
	public String getEmailVerificationCode()
	{
		return emailVerificationCode;
	}
	
	public void setEmailVerificationCode(String emailVerificationCode)
	{
		this.emailVerificationCode = emailVerificationCode;
	}
	
	public String getNewPassword()
	{
		return newPassword;
	}
	
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
}
