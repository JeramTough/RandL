package com.jeramtough.action.component.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 16:54.
 */
public class EmailValidator extends Validator<String>
{
	private boolean isPass=true;
	
	@Override
	public boolean isPassed()
	{
		return isPass;
	}
	
	@Override
	public List<String> validator(String target)
	{
		String pattern =
				"^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		//正则表达式的模式
		Pattern p = Pattern.compile(pattern);
		//正则表达式的匹配器
		Matcher m = p.matcher(target);
		//进行正则匹配
		if (m.matches() == false)
		{
			getErrorMessages().add("205:邮箱格式不正确!");
			isPass=false;
		}
		return getErrorMessages();
	}
}
