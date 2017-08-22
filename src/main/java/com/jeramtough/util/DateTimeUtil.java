package com.jeramtough.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 22:37.
 */
public class DateTimeUtil
{
	
	public static String getCurrentDateTime(DateFormat format)
	{
		Date date = new Date();
		String time = format.format(date);
		return time;
	}
	
	public static String getCurrentDateTime()
	{
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	
	// compute date(日期时间的相加减)
	public static String dateTimeOperation(Date startDate, String operation, int second)
	{
		long t = startDate.getTime();
		
		if (operation.equals("+"))
		{
			t += second * 1000;
		}
		else if (operation.equals("-"))
		{
			t -= second * 1000;
		}
		Date date = new Date(t);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	
	/*
	 * Date类型，就使用equals(), before(), after()来计算 long类型，就使用==, <, >来计算
	 */
	public static int intervalTime(Date date1, Date date2)
	{
		long a = date1.getTime();
		long b = date2.getTime();
		long c = a - b;
		int second = (int) Double.valueOf(Double.toString(c / 1000D)).doubleValue();
		return second;
	}
	
}
