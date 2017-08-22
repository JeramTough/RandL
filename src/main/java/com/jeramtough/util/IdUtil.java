package com.jeramtough.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by JeramTough
 * on 2017  January 01 Sunday 19:22.
 */
public class IdUtil
{
	public static String getUUID()
	{
		String uuid=UUID.randomUUID().toString();
		uuid=uuid.replace("-","");
		return uuid;
	}
	
	public static String randomNumber(int min,int max,int count){
		int len = max-min+1;
		
		if(max < min || count > len){
			return null;
		}
		
		//初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min+len; i++){
			source[i-min] = i;
		}
		
		int[] result = new int[count];
		StringBuilder stringBuilder=new StringBuilder();
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			//待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len--);
			//将随机到的数放入结果集
			result[i] = source[index];
			//将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
			stringBuilder.append(result[i]);
		}
		return stringBuilder.toString();
	}
}
