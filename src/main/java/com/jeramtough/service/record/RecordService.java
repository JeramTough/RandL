package com.jeramtough.service.record;

import com.jeramtough.business.record.RecordBusiness;
import com.jeramtough.util.DateTimeUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 00:41.
 */
@Service
public class RecordService implements RecordBusiness
{
	@Override
	public String getAddressByUserIp(String ip)
	{
		String url = "http://saip.market.alicloudapi.com/ip?ip=" + ip;
		OkHttpClient client = new OkHttpClient();
		okhttp3.Request request = new Request.Builder().get().url(url)
				.addHeader("Authorization", "APPCODE b333e8f85f3243c48fb3f1690d45f035")
				.build();
		
		String address = "no result";
		
		try
		{
			okhttp3.Response response = client.newCall(request).execute();
			if (response.isSuccessful())
			{
				String json = response.body().string();
				JSONObject jsonObject = new JSONObject(json).getJSONObject("showapi_res_body");
				String country = jsonObject.getString("country");
				String region = jsonObject.getString("region");
				String city = jsonObject.getString("city");
				String isp = jsonObject.getString("isp");
				
				address = country + "." + region + (region.equals(city) ? "" : "." + city) +
						"." + isp;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return address;
	}
	
	@Override
	public String getRecordedTime()
	{
		return DateTimeUtil.getCurrentDateTime();
	}
	
	@Override
	public String getUserIdFromMessage(String message)
	{
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(message);
		
		if (matcher.find())
		{
			return matcher.group();
		}
		return null;
	}
}
