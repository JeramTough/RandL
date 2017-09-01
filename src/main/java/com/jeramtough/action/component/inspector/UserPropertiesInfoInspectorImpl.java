package com.jeramtough.action.component.inspector;

import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 0:52.
 */
public class UserPropertiesInfoInspectorImpl extends UserRegisterInfoInspectorImpl
		implements UserInfoInspector
{
	public UserPropertiesInfoInspectorImpl(PropertiesInfo propertiesInfo)
	{
		super(propertiesInfo);
	}
	
	private UserPropertiesInfoInspectorImpl(RegisterInfo registerInfo)
	{
		super(registerInfo);
	}
	
	public int checkUserId(String userId, int theSameUserIdCount)
	{
		if (userId == null || theSameUserIdCount == 0)
		{
			setMessage("userId 未填写或者不存在");
			return 222;
		}
		return 666;
	}
	
	public int checkSmsVerificationCode(String rightSmsVerificationCode,
			String verifiedPhoneNumber, String currentUserPhoneNumber,
			String inspectedPhoneNumber)
	{
		if (rightSmsVerificationCode == null)
		{
			setMessage("短信验证码以失效，用户请重新发送短信验证码到手机上！");
			return 209;
		}
		
		if (getRegisterInfo().getSmsVerificationCode() == null)
		{
			setMessage("需要填写短信验证码");
			return 208;
		}
		
		
		if (rightSmsVerificationCode.equals(getRegisterInfo().getSmsVerificationCode()) ==
				false)
		{
			setMessage("短信验证码填写不正确！");
			return 210;
		}
		
		if (currentUserPhoneNumber.equals(inspectedPhoneNumber))
		{
			setMessage("即将修改的手机号码与当前用户注册的手机号码相同");
			return 223;
		}
		
		if (verifiedPhoneNumber.equals(currentUserPhoneNumber) == false)
		{
			setMessage("发送验证码的手机号码不是当前用户注册的手机号码");
			return 224;
		}
		
		return 666;
	}
	
	public int checkEmailVerificationCode(String rightEmailVerificationCode,
			String verifiedEmailAddress, String currentUserEmailAddress,
			String inspectedEmailAddress)
	{
		if (rightEmailVerificationCode == null)
		{
			setMessage("邮箱验证码以失效，用户请重新发送验证码到邮箱上！");
			return 214;
		}
		
		if (getRegisterInfo().getEmailVerificationCode() == null)
		{
			setMessage("需要填写邮箱验证码");
			return 215;
		}
		
		
		if (rightEmailVerificationCode.equals(getRegisterInfo().getEmailVerificationCode()) ==
				false)
		{
			setMessage("邮箱验证码填写不正确！");
			return 216;
		}
		
		if (currentUserEmailAddress.equals(inspectedEmailAddress))
		{
			setMessage("即将修改的邮箱地址与当前用户注册的邮箱地址相同");
			return 225;
		}
		
		if (verifiedEmailAddress.equals(currentUserEmailAddress) == false)
		{
			setMessage("发送验证码的邮箱地址不是当前用户注册的邮箱地址");
			return 226;
		}
		return 666;
	}
	
	@Override
	@Deprecated
	public int checkSmsVerificationCode(String rightSmsVerificationCode,
			String verifiedPhoneNumber)
	{
		return super.checkSmsVerificationCode(rightSmsVerificationCode, verifiedPhoneNumber);
	}
	
	
	@Override
	@Deprecated
	public int checkEmailVerificationCode(String rightEmailVerificationCode,
			String verifiedEmailAddress)
	{
		return super
				.checkEmailVerificationCode(rightEmailVerificationCode, verifiedEmailAddress);
	}
}
