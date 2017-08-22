package com.jeramtough.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2017  August 22 Tuesday 12:18.
 */
public class ServletUtil
{
	public static String getClientIp(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
		{
			ip = request.getRemoteAddr();
		}
		
		if (ip.equals("0:0:0:0:0:0:0:1"))
		{
			ip = "116.10.42.95";
		}
		
		return ip;
	}
}
