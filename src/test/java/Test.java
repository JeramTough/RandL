import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeramtough.util.PropertiesUtil;
import com.jtlog.user.command.P;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.boot.env.PropertiesPropertySourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 21:04.
 */
public class Test
{
	@org.junit.Test
	public void test1()
	{
		// 要验证的字符串
		String str = "用户2注册5成功3";
		
		// 正则表达式
		Pattern pattern = Pattern.compile("[0-9]+");
		
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		
		// 当字符串与正则表达式相匹配时
		while (matcher.find())
		{
			//得到匹配结果
			P.info(matcher.group());
		}
		
		
	}
	
	@org.junit.Test
	public void test2()
	{
		P.info(getAddressByUserIp("116.10.42.95"));
	}
	
	
	
	public String getAddressByUserIp(String ip)
	{
		String url = "http://saip.market.alicloudapi.com/ip?ip=" + ip;
		OkHttpClient client = new OkHttpClient();
		okhttp3.Request request = new Request.Builder().get().url(url)
				.addHeader("Authorization", "APPCODE b333e8f85f3243c48fb3f1690d45f035")
				.build();
		
		String address="no result";
		
		try
		{
			okhttp3.Response response = client.newCall(request).execute();
			P.info(response.isSuccessful());
			if (response.isSuccessful())
			{
				String json=response.body().string();
				P.info(json);
				JSONObject jsonObject=new JSONObject(json).getJSONObject("showapi_res_body");
				String country=jsonObject.getString("country");
				String region=jsonObject.getString("region");
				String city=jsonObject.getString("city");
				String isp=jsonObject.getString("isp");
				
				address=country+"."+region+(region.equals(city)?"":"."+city)+"."+isp;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return address;
	}
	
}
