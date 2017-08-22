package com.jeramtough.bean.responsebody;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 23:03.
 */
public class OkResponseInfo extends ResponseInfo
{
	public OkResponseInfo()
	{
		super(666);
	}
	
	public OkResponseInfo( Object message)
	{
		super(666, message);
	}
}
