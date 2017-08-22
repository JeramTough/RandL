package com.jeramtough.util;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 15:36.
 */
public class WordUtil
{
	public static String exLowerChange(String str)
	{
		StringBuffer sb = new StringBuffer();
		if (str != null)
		{
			for (int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				if (Character.isUpperCase(c))
				{
					sb.append(Character.toLowerCase(c));
				}
				else if (Character.isLowerCase(c))
				{
					sb.append(c);
				}
			}
		}
		
		return sb.toString();
	}
	
	public static String exUpperChange(String str)
	{
		StringBuffer sb = new StringBuffer();
		if (str != null)
		{
			for (int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				if (Character.isLowerCase(c))
				{
					sb.append(Character.toUpperCase(c));
				}
				else if (Character.isUpperCase(c))
				{
					sb.append(c);
				}
			}
		}
		
		return sb.toString();
	}
}
