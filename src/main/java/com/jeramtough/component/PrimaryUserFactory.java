package com.jeramtough.component;

import com.jeramtough.component.qqs.QQAccessToken;
import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.user.PrimaryUser;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 22:07.
 */
public class PrimaryUserFactory
{
	public static PrimaryUser processPrimaryUser(RegisterInfo registerInfo, String userId,
			String defaultSurfaceImageUrl)
	{
		
		if (registerInfo.getGender() == null)
		{
			defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=default.jpg";
		}
		else
		{
			switch (registerInfo.getGender())
			{
				case "male":
					defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=male.jpg";
					break;
				case "female":
					defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=female.jpg";
					break;
				default:
					defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=default.jpg";
					break;
			}
		}
		
		PrimaryUser primaryUser = new PrimaryUser();
		primaryUser.setUserId(userId);
		primaryUser.setSurfaceImageUrl(defaultSurfaceImageUrl);
		
		primaryUser.setNickname(processNick(userId));
		
		primaryUser.setUsername(registerInfo.getUsername());
		primaryUser.setPassword(registerInfo.getPassword());
		primaryUser.setPhoneNumber(registerInfo.getPhoneNumber());
		primaryUser.setGender(registerInfo.getGender());
		primaryUser.setAge(registerInfo.getAge());
		primaryUser.setEmail(registerInfo.getEmail());
		primaryUser.setBirthday(registerInfo.getBirthday());
		primaryUser.setQqNumber(registerInfo.getQqNumber());
		primaryUser.setSmsVerificationCode(registerInfo.getSmsVerificationCode());
		return primaryUser;
	}
	
	
	public static PrimaryUser processPrimaryUser(String userId, String qqUserOpenId,
			QQAccessToken.QQUserPrimaryInformation qqUserPrimaryInformation)
	{
		PrimaryUser primaryUser = new PrimaryUser();
		primaryUser.setUserId(userId);
		primaryUser.setUsername(qqUserOpenId);
		primaryUser.setPassword(qqUserOpenId);
		primaryUser.setNickname(qqUserPrimaryInformation.getUsername());
		primaryUser.setAge(qqUserPrimaryInformation.getAge());
		primaryUser.setGender(qqUserPrimaryInformation.getSex());
		primaryUser.setSurfaceImageUrl(qqUserPrimaryInformation.getHeaderImageUrl());
		
		primaryUser.setNickname(processNick(userId));
		
		return primaryUser;
	}
	
	public static PrimaryUser processPrimaryUserWithPhone(String userId, String phoneNumber,
			String password, String defaultSurfaceImageUrl)
	{
		PrimaryUser primaryUser = new PrimaryUser();
		primaryUser.setUserId(userId);
		primaryUser.setUsername(phoneNumber);
		primaryUser.setPhoneNumber(phoneNumber);
		primaryUser.setPassword(password);
		
		defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=default.jpg";
		primaryUser.setSurfaceImageUrl(defaultSurfaceImageUrl);
		
		primaryUser.setNickname(processNick(userId));
		
		return primaryUser;
	}
	
	public static PrimaryUser processPrimaryUserWithEmail(String userId, String email,
			String password, String defaultSurfaceImageUrl)
	{
		PrimaryUser primaryUser = new PrimaryUser();
		primaryUser.setUserId(userId);
		primaryUser.setUsername(email);
		primaryUser.setEmail(email);
		primaryUser.setPassword(password);
		
		defaultSurfaceImageUrl = defaultSurfaceImageUrl + "?name=default.jpg";
		primaryUser.setSurfaceImageUrl(defaultSurfaceImageUrl);
		
		primaryUser.setNickname(processNick(userId));
		
		return primaryUser;
	}
	
	
	//************************************
	private static String processNick(String userId)
	{
		return "用户"+userId;
	}
}
