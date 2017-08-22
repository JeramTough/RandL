package com.jeramtough.action.component.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * on 2017  August 14 Monday 17:09.
 */
public abstract class Validator<T>
{
	private boolean isPass=true;
	
	
	private List<String> errorMessages=new ArrayList<>();
	
	public boolean isPassed()
	{
		return isPass;
	}
	
	public abstract List<String> validator(T target);
	
	
	public void setPass(boolean pass)
	{
		isPass = pass;
	}
	
	public List<String> getErrorMessages()
	{
		return errorMessages;
	}
}
