package com.jeramtough.action.component.inspector;

import com.jeramtough.bean.requestbody.ResetPasswordInfo;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 16:47.
 */
public class UserResetPasswordInfoInspector implements UserInfoInspector
{
	private ResetPasswordInfo resetPasswordInfo;
	private String message = "重置密码成功";
	
	public UserResetPasswordInfoInspector(ResetPasswordInfo resetPasswordInfo)
	{
		this.resetPasswordInfo = resetPasswordInfo;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public int checkIsSentVerificationCode(String rightSmsVerificationCode,
			String rightEmailVerificationCode)
	{
		if (rightSmsVerificationCode == null && rightEmailVerificationCode == null)
		{
			setMessage("未发送过短信验证码或者邮箱验证码");
			return 230;
		}
		return 666;
	}
	
	@Override
	public int checkFormat()
	{
		if (resetPasswordInfo.getNewPassword() == null)
		{
			setMessage("新密码未填写");
			return 229;
		}
		return 666;
	}
	
	public int checkPhoneNumber(int theSamePhoneNumberCount)
	{
		if (theSamePhoneNumberCount == 0)
		{
			message = "改手机号码未注册过";
			return 227;
		}
		
		return 666;
	}
	
	public int checkEmailAddress(int theSameEmailAddressCount)
	{
		if (theSameEmailAddressCount == 0)
		{
			message = "改邮箱地址未注册过";
			return 228;
		}
		return 666;
	}
	
	
	public int checkSmsVerificationCode(String rightSmsVerificationCode)
	{
		if (rightSmsVerificationCode == null)
		{
			setMessage("短信验证码以失效，用户请重新发送短信验证码到手机上！");
			return 209;
		}
		
		if (resetPasswordInfo.getSmsVerificationCode() == null)
		{
			setMessage("需要填写短信验证码");
			return 208;
		}
		
		
		if (rightSmsVerificationCode.equals(resetPasswordInfo.getSmsVerificationCode()) ==
				false)
		{
			setMessage("短信验证码填写不正确！");
			return 210;
		}
		return 0;
	}
	
	public int checkEmailVerificationCode(String rightEmailVerificationCode)
	{
		if (rightEmailVerificationCode == null)
		{
			setMessage("邮箱验证码以失效，用户请重新发送验证码到邮箱上！");
			return 214;
		}
		
		if (resetPasswordInfo.getEmailVerificationCode() == null)
		{
			setMessage("需要填写邮箱验证码");
			return 215;
		}
		
		
		if (rightEmailVerificationCode.equals(resetPasswordInfo.getEmailVerificationCode()) ==
				false)
		{
			setMessage("邮箱验证码填写不正确！");
			return 216;
		}
		return 666;
	}
	
	@Override
	@Deprecated
	public int checkSmsVerificationCode(String rightSmsVerificationCode,
			String verifiedPhoneNumber)
	{
		return 0;
	}
	
	@Override
	@Deprecated
	public int checkEmailVerificationCode(String rightEmailVerificationCode,
			String verifiedEmailAddress)
	{
		return 0;
	}
}
