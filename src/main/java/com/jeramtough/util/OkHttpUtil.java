package com.jeramtough.util;

import com.jtlog.user.command.P;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

/**
 * Created by Administrator
 * on 2017  February 18 Saturday 22:12.
 */
public class OkHttpUtil
{
	
	public static String get(String url)
	{
		OkHttpClient client = new OkHttpClient();
		okhttp3.Request request = new Request.Builder().url(url).build();
		okhttp3.Response response = null;
		try
		{
			response = client.newCall(request).execute();
			if (response.isSuccessful())
			{
				return response.body().string();
			}
			else
			{
				throw new IOException("Unexpected code " + response);
			}
		}
		catch (IOException e)
		{
			P.error(e.getMessage());
		}
		return null;
	}
}
