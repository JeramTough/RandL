package com.jeramtough.bean.responsebody;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 21:31.
 */
public class ResponseInfo
{
	private int statusCode;
	private Object message;
	
	public ResponseInfo()
	{
	}
	
	public ResponseInfo(int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public ResponseInfo(int statusCode, Object message)
	{
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}
	
	public Object getMessage()
	{
		return message;
	}
	
	public void setMessage(Object message)
	{
		this.message = message;
	}
}
