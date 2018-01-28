package com.jeramtough.component.qqs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeramtough.util.DateTimeUtil;
import com.jeramtough.util.OkHttpUtil;
import com.jeramtough.util.TextUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator
 * on 2017  February 18 Saturday 14:37.
 */
public class QQAccessToken
{
	private AccessToken accessToken;
	private HttpServletRequest request;
	
	public QQAccessToken(HttpServletRequest request)
	{
		this.request = request;
		initResource();
	}
	
	protected void initResource()
	{
		try
		{
			accessToken = (new Oauth()).getAccessTokenByRequest(request);
		}
		catch (QQConnectException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getAccessToken()
	{
		String accessTokenString = null;
		if (accessToken.getAccessToken().equals(""))
		{
			/*我们的网站被CSRF攻击了或者用户取消了授权
			做一些数据统计工作*/
		}
		else
		{
			accessTokenString = accessToken.getAccessToken();
		}
		return accessTokenString;
	}
	
	public String getUserOpenId()
	{
		String userOpenId = null;
		// 利用获取到的accessToken 去获取当前用的openid -------- start
		OpenID openIDObj = new OpenID(accessToken.getAccessToken());
		try
		{
			userOpenId = openIDObj.getUserOpenID();
		}
		catch (QQConnectException e)
		{
			e.printStackTrace();
		}
		return userOpenId;
	}
	
	public QQUserPrimaryInformation getQQUserPrimaryInformation()
	{
		String url = "https://graph.qq.com/user/get_user_info?" +
				"oauth_consumer_key=%s&access_token=%s&openid=%s&format=json";
		url = TextUtil.replacedByParams(url, "101378587", this.getAccessToken(),
				this.getUserOpenId());
		
		String body = OkHttpUtil.get(url);
		QQUserPrimaryInformation qqUserPrimaryInformation = new QQUserPrimaryInformation(body);
		return qqUserPrimaryInformation;
	}
	
	//{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}
	public class QQUserPrimaryInformation
	{
		private String body;
		private String headerImageUrl;
		private String username;
		private String sex;
		private String age;
		private String address;
		
		private QQUserPrimaryInformation(String body)
		{
			this.body = body;
			initQQUserInformation();
		}
		
		protected void initQQUserInformation()
		{
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = null;
			try
			{
				node = mapper.readTree(body);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			username = node.get("nickname").textValue();
			headerImageUrl = node.get("figureurl_qq_1").textValue();
			sex = node.get("gender").textValue();
			age = this.getAge(node.get("year").textValue());
			address = node.get("province").textValue() + node.get("city").textValue();
		}
		
		public String getHeaderImageUrl()
		{
			return headerImageUrl;
		}
		
		public String getUsername()
		{
			return username;
		}
		
		public String getSex()
		{
			if (sex.equals("男"))
			{
				return "male";
			}
			else if (sex.equals("女"))
			{
				return "female";
			}
			return sex;
		}
		
		public String getAge()
		{
			return age;
		}
		
		public String getBody()
		{
			return body;
		}
		
		public String getAddress()
		{
			return address;
		}
		
		@Override
		public String toString()
		{
			return "QQUserPrimaryInformation{" + "headerImageUrl='" + headerImageUrl + '\'' +
					", username='" + username + '\'' + ", sex='" + sex + '\'' + ", age='" +
					age + '\'' + ", address='" + address + '\'' + '}';
		}
		
		//************************************
		private String getAge(String year)
		{
			int a = (Integer
					.valueOf(DateTimeUtil.getCurrentDateTime(new SimpleDateFormat("yyyy"))));
			int b = Integer.valueOf(year);
			int age = a - b;
			if (age < 0)
			{
				age = 0;
			}
			return age + "";
		}
		
	}
}

