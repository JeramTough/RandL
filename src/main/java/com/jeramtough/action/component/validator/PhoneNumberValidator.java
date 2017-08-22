package com.jeramtough.action.component.validator;

import java.util.List;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 17:06.
 */
public class PhoneNumberValidator extends Validator<String>
{
	@Override
	public List<String> validator(String target)
	{
		if (target != null)
		{
			try
			{
				Long.parseLong(target);
				if (target.length() != 11)
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				getErrorMessages().add("206:手机号码格式异常,而且长度必须为11位!");
			}
		}
		return getErrorMessages();
	}
}
