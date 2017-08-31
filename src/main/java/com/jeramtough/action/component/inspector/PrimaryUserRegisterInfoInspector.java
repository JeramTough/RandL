package com.jeramtough.action.component.inspector;

import com.jeramtough.bean.requestbody.RegisterInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 22:54.
 */
public class PrimaryUserRegisterInfoInspector extends RegisterInfoInspector
{
	
	public PrimaryUserRegisterInfoInspector(RegisterInfo registerInfo)
	{
		super(registerInfo);
	}
	
	@Override
	public int checkFormat()
	{
		if (getRegisterInfo().getPassword() == null)
		{
			setMessage("注册密码不能为空!");
			return 201;
		}
		if (!(getRegisterInfo().getGender() == null ||
				getRegisterInfo().getGender().equals("male") ||
				getRegisterInfo().getGender().equals("female")))
		{
			setMessage("性别只能为null，或者male，再或者female!");
			return 202;
		}
		if (getRegisterInfo().getBirthday() != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
			try
			{
				format.parse(getRegisterInfo().getBirthday());
			}
			catch (ParseException e)
			{
				setMessage("生日格式只能为 YYYY-MM-DD !");
				return 203;
			}
		}
		if (getRegisterInfo().getAge() != null)
		{
			try
			{
				Integer.parseInt(getRegisterInfo().getAge());
			}
			catch (Exception e)
			{
				setMessage("年龄只能是数字!");
				return 204;
			}
		}
		if (getRegisterInfo().getEmail() != null)
		{
			String pattern =
					"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
			//正则表达式的模式
			Pattern p = Pattern.compile(pattern);
			//正则表达式的匹配器
			Matcher m = p.matcher(getRegisterInfo().getEmail());
			//进行正则匹配
			if (m.matches() == false)
			{
				setMessage("邮箱格式不正确!");
				return 205;
			}
		}
		if (getRegisterInfo().getPhoneNumber() != null)
		{
			try
			{
				Long.parseLong(getRegisterInfo().getPhoneNumber());
				if (getRegisterInfo().getPhoneNumber().length() != 11)
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				setMessage("手机号码格式异常,而且长度必须为11位!");
				return 206;
			}
		}
		if (getRegisterInfo().getQqNumber() != null)
		{
			try
			{
				Long.parseLong(getRegisterInfo().getQqNumber());
			}
			catch (Exception e)
			{
				setMessage("QQ号码格式异常!");
				return 207;
			}
		}
		return 666;
	}
	
	
	@Override
	public int checkSmsVerificationCode(String rightSmsVerificationCode,
			String verifiedPhoneNumber)
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
		
		if (getRegisterInfo().getPhoneNumber().equals(verifiedPhoneNumber) == false)
		{
			setMessage("注册的电话号码与验证的号码不是同一个！");
			return 219;
		}
		return 666;
	}
	
	@Override
	public int checkEmailVerificationCode(String rightEmailVerificationCode,
			String verifiedEmailAddress)
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
		
		if (getRegisterInfo().getEmail().equals(verifiedEmailAddress) == false)
		{
			setMessage("注册的邮箱地址与验证的邮箱不是同一个！");
			return 220;
		}
		return 666;
	}
	
	@Override
	public int checkTheSameRegisterInformation(int theSameUsernameCount,
			int theSamePhoneNumberCount, int theSameEmailCount)
	{
		if (getRegisterInfo().getUsername() != null && theSameUsernameCount > 0)
		{
			setMessage("用户名已被注册，请换个未被注册过的");
			return 211;
		}
		if (getRegisterInfo().getPhoneNumber() != null && theSamePhoneNumberCount > 0)
		{
			setMessage("手机号码已被注册，请换个未被注册过的");
			return 212;
		}
		if (getRegisterInfo().getEmail() != null && theSameEmailCount > 0)
		{
			setMessage("邮箱已被注册，请换个未被注册过的");
			return 213;
		}
		return 666;
	}
}
