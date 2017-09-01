package com.jeramtough.action.service;

import com.jeramtough.action.business.UserPropertiesBusiness;
import com.jeramtough.action.component.inspector.UserPropertiesInfoInspectorImpl;
import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import com.jeramtough.dao.mapper.UpdatePrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 20:37.
 */
@Service
public class UserPropertiesService extends MyService implements UserPropertiesBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	private final UpdatePrimaryUserMapper updatePrimaryUserMapper;
	
	@Autowired
	public UserPropertiesService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			SelectPrimaryUserMapper selectPrimaryUserMapper,
			UpdatePrimaryUserMapper updatePrimaryUserMapper)
	{
		super(applicationContext, configurationMapper);
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
		this.updatePrimaryUserMapper = updatePrimaryUserMapper;
	}
	
	
	@Override
	public ResponseInfo checkModifiedProperties(PropertiesInfo propertiesInfo,
			String rightSmsVerificationCode, String rightEmailVerificationCode,
			String verifiedPhoneNumber, String verifiedEmailAddress)
	{
		UserPropertiesInfoInspectorImpl inspector =
				new UserPropertiesInfoInspectorImpl(propertiesInfo);
		
		//get the same info from database
		int theSameUsernameCount = selectPrimaryUserMapper
				.getTheSameValueCountForUsername(propertiesInfo.getUsername());
		int theSamePhoneNumberCount = selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(propertiesInfo.getPhoneNumber());
		int theSameEmailCount = selectPrimaryUserMapper
				.getTheSameValueCountForEmail(propertiesInfo.getEmail());
		int theSameUserIdCount =
				selectPrimaryUserMapper.getUserCountByUserId(propertiesInfo.getUserId());
		
		
		//verify the user id
		int statusCode = inspector.checkUserId(propertiesInfo.getUserId(), theSameUserIdCount);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		//get user of current the phone number and the email address
		String currentUserPhoneNumber =
				selectPrimaryUserMapper.getPhoneNumberByUserId(propertiesInfo.getUserId());
		String currentUserEmailAddress =
				selectPrimaryUserMapper.getEmailAddressByUserId(propertiesInfo.getUserId());
		
		//verify the format of information of register
		statusCode = inspector.checkFormat();
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		///checking the Sms verification code
		if (propertiesInfo.getPhoneNumber() != null)
		{
			statusCode = inspector
					.checkSmsVerificationCode(rightSmsVerificationCode, verifiedPhoneNumber,
							currentUserPhoneNumber, propertiesInfo.getPhoneNumber());
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		//checking the email verification code
		if (propertiesInfo.getEmail() != null)
		{
			statusCode = inspector.checkEmailVerificationCode(rightEmailVerificationCode,
					verifiedEmailAddress, currentUserEmailAddress, propertiesInfo.getEmail());
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		//verify whether the information of register are the same
		if (propertiesInfo.getUsername() == null)
		{
			theSameUsernameCount = 0;
		}
		if (propertiesInfo.getPhoneNumber() == null)
		{
			theSamePhoneNumberCount = 0;
		}
		if (propertiesInfo.getEmail() == null)
		{
			theSameEmailCount = 0;
		}
		statusCode = inspector
				.checkTheSameRegisterInformation(theSameUsernameCount, theSamePhoneNumberCount,
						theSameEmailCount);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		return new OkResponseInfo("修改成功");
	}
	
	@Override
	public void modifyProperties(PropertiesInfo propertiesInfo)
	{
		new Thread(() -> {
			
			updatePrimaryUserMapper.updatePrimaryUser(Long.parseLong(propertiesInfo.getUserId()),
					propertiesInfo.getUsername(), propertiesInfo.getNickname(),
					propertiesInfo.getPassword(), propertiesInfo.getGender(),
					Integer.parseInt(propertiesInfo.getAge()), propertiesInfo.getPhoneNumber(),
					propertiesInfo.getEmail(), propertiesInfo.getQqNumber(),
					propertiesInfo.getSurfaceImageUrl());
		}).start();
	}
}
