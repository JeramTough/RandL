package com.jeramtough.bean.requestbody;


/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 19:32.
 */
public class RequestInfo<T>
{
	private String action;
	private T message;
	
	public String getAction()
	{
		return action;
	}
	
	public void setAction(String action)
	{
		this.action = action;
	}
	
	public T getMessage()
	{
		return message;
	}
	
	public void setMessage(T message)
	{
		this.message = message;
	}
}
