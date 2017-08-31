package com.jeramtough.action.component.inspector;

import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 21:29.
 */
public abstract class RegisterInfoInspector
{
	private RegisterInfo registerInfo;
	private int statusCode=666;
	private String message;
	
	public RegisterInfoInspector(RegisterInfo registerInfo)
	{
		this.registerInfo=registerInfo;
	}
	
	
	public RegisterInfo getRegisterInfo()
	{
		return registerInfo;
	}
	
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	/*public ResponseInfo inspect()
	{
		statusCode=checkFormat();
		if (statusCode!=666)
		{
			return new ResponseInfo(statusCode,message);
		}
		
		statusCode=checkSmsVerificationCode();
		if (statusCode!=666)
		{
			return new ResponseInfo(statusCode,message);
		}
		
		statusCode=checkEmailVerificationCode();
		if (statusCode!=666)
		{
			return new ResponseInfo(statusCode,message);
		}
		
		statusCode=checkTheSameRegisterInformation();
		if (statusCode!=666)
		{
			return new ResponseInfo(statusCode,message);
		}
		return new OkResponseInfo();
	}*/
	
	public abstract int checkFormat();
	
	public abstract int checkSmsVerificationCode(String rightSmsVerificationCode, String verifiedPhoneNumber);
	
	public abstract int checkEmailVerificationCode(String rightEmailVerificationCode, String
			verifiedEmailAddress);

	public abstract int checkTheSameRegisterInformation(int theSameUsernameCount,int
			theSamePhoneNumberCount,int theSameEmailCount);

	
}
