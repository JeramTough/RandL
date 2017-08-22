package com.jeramtough.action.component.validator;

import com.jeramtough.bean.RegisterInfo;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator
 * on 2017  August 14 Monday 17:08.
 */
@Component
public class RegisterInfoValidator extends Validator<RegisterInfo>
{
	private boolean isPassed;
	
	@Override
	public boolean isPassed()
	{
		return isPassed;
	}
	
	@Override
	public List<String> validator(RegisterInfo target)
	{
		ArrayList<String> messages = new ArrayList<>();
		
		if (target.getPassword() == null)
		{
			messages.add("201:注册密码不能为空!");
		}
		if (!(target.getGender() == null || target.getGender().equals("male") ||
				target.getGender().equals("female")))
		{
			messages.add("202:性别只能为null，或者male，再或者female!");
		}
		if (target.getBirthday() != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
			try
			{
				format.parse(target.getBirthday());
			}
			catch (ParseException e)
			{
				messages.add("203:生日格式只能为 YYYY-MM-DD !");
			}
		}
		if (target.getAge() != null)
		{
			try
			{
				Integer.parseInt(target.getAge());
			}
			catch (Exception e)
			{
				messages.add("204:年龄只能是数字!");
			}
		}
		if (target.getEmail() != null)
		{
			String pattern =
					"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
			//正则表达式的模式
			Pattern p = Pattern.compile(pattern);
			//正则表达式的匹配器
			Matcher m = p.matcher(target.getEmail());
			//进行正则匹配
			if (m.matches() == false)
			{
				messages.add("205:邮箱格式不正确!");
			}
		}
		if (target.getPhoneNumber() != null)
		{
			try
			{
				Long.parseLong(target.getPhoneNumber());
				if (target.getPhoneNumber().length() != 11)
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				messages.add("206:手机号码格式异常,而且长度必须为11位!");
			}
		}
		if (target.getQqNumber() != null)
		{
			try
			{
				Long.parseLong(target.getQqNumber());
			}
			catch (Exception e)
			{
				messages.add("207:QQ号码格式异常!");
			}
		}
		
		if (messages.size() == 0)
		{
			isPassed = true;
		}
		else
		{
			isPassed = false;
		}
		return messages;
	}
	
	
}
