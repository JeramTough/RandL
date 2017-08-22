package com.jeramtough.util;

import java.io.File;
import java.io.IOException;

public class FolderUtil
{

	public static void createFile(String fileUri,boolean deleteExisted)
	{
		File file=new File(fileUri);
		
		if(file.exists()==true)
		{
			if(deleteExisted==true)
			{
				file.delete();
				createFile(fileUri);
			}
		}
		else
		{
			createFile(fileUri);
		}
	}

	//===================================================
	private static void createFile(String fileUri)
	{
		File file=new File(fileUri);
		if(file.getParentFile().exists()==false)
		{
			file.getParentFile().mkdirs();
		}
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


