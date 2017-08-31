package com.jeramtough.bean.user;

import com.jeramtough.bean.requestbody.RegisterInfo;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator
 * on 2017  August 10 Thursday 22:59.
 */
@Component
public class PrimaryUser extends RegisterInfo
{
	private String userId;
	private String nickname;
	private String surfaceImageUrl;
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public String getSurfaceImageUrl()
	{
		return surfaceImageUrl;
	}
	
	public void setSurfaceImageUrl(String surfaceImageUrl)
	{
		this.surfaceImageUrl = surfaceImageUrl;
	}
	
	@Override
	public String toString()
	{
		return "PrimaryUser{" + "userId='" + userId + '\'' + ", nickname='" + nickname + '\'' +
				", surfaceImageUrl='" + surfaceImageUrl + '\'' + "} " + super.toString();
	}
}
