package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.PhoneUserRegisterBusiness;
import com.jeramtough.action.component.PrimaryUserFactory;
import com.jeramtough.action.component.inspector.UserRegisterInfoInspectorImpl;
import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 16 Wednesday 16:03.
 */
@Service
public class PhoneUserRegisterService extends RegisterService
		implements PhoneUserRegisterBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	
	@Autowired
	public PhoneUserRegisterService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper,
			SelectPrimaryUserMapper selectPrimaryUserMapper)
	{
		super(applicationContext, configurationMapper, insertPrimaryUserMapper);
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
	}
	
	@Override
	public PrimaryUser createUserWithPhoneNumber(String phoneNumber, String password)
	{
		String userId = selectPrimaryUserMapper.getSumOfUser() + "";
		PrimaryUser primaryUser = PrimaryUserFactory
				.processPrimaryUserWithPhone(userId, phoneNumber, password,
						getConfigurationMapper().getDefaultSurfaceImageUrl());
		return primaryUser;
	}
	
	@Override
	public ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightSmsVerificationCode,String verifiedPhoneNumber)
	{
	
		UserRegisterInfoInspectorImpl inspector =
				new UserRegisterInfoInspectorImpl(registerInfo);
		
		
		int theSamePhoneNumberCount = selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(registerInfo.getPhoneNumber());
		
		
		
		//verify the format of information of register
		int statusCode = inspector.checkFormat();
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		///checking the Sms verification code
			statusCode = inspector
					.checkSmsVerificationCode(rightSmsVerificationCode, verifiedPhoneNumber);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		
		
		//verify whether the information of register are the same
		statusCode = inspector
				.checkTheSameRegisterInformation(0, theSamePhoneNumberCount,
						0);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		return new OkResponseInfo();
	}
	
}
