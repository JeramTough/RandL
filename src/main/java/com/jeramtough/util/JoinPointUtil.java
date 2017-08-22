package com.jeramtough.util;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2017  August 22 Tuesday 12:15.
 */
public class JoinPointUtil
{
	public static HttpServletRequest getRequest(JoinPoint joinPoint)
	{
		HttpServletRequest request = null;
		for (Object object : joinPoint.getArgs())
		{
			if (object instanceof HttpServletRequest)
			{
				request = (HttpServletRequest) object;
				break;
			}
		}
		return request;
	}
}
