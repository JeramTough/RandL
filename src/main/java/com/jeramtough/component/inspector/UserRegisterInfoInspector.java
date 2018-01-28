package com.jeramtough.component.inspector;

import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 21:29.
 */
public abstract class UserRegisterInfoInspector implements UserInfoInspector
{
	private RegisterInfo registerInfo;
	private int statusCode=666;
	private String message;
	
	
	public UserRegisterInfoInspector(RegisterInfo registerInfo)
	{
		this.registerInfo=registerInfo;
	}
	
	public void setRegisterInfo(RegisterInfo registerInfo)
	{
		this.registerInfo = registerInfo;
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
	
	public abstract int checkTheSameRegisterInformation(int theSameUsernameCount,int
			theSamePhoneNumberCount,int theSameEmailCount);

	
}
