package com.jeramtough.util;

import java.io.File;

public class PropertiesUtil
{

	public static void createPropertiesFiles(String directoryPath)
	{
		File file=new File(directoryPath);
		
		if(file.exists()==false)
		{
			boolean a=file.mkdirs();
		}
	}
	
}
