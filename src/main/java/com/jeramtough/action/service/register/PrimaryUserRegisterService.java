package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.PrimaryUserRegisterBusiness;
import com.jeramtough.action.component.PrimaryUserFactory;
import com.jeramtough.action.component.inspector.UserRegisterInfoInspectorImpl;
import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 23:52.
 */
@Service
public class PrimaryUserRegisterService extends RegisterService
		implements PrimaryUserRegisterBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	
	public PrimaryUserRegisterService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper,
			SelectPrimaryUserMapper selectPrimaryUserMapper)
	{
		super(applicationContext, configurationMapper, insertPrimaryUserMapper);
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
	}
	
	@Override
	public PrimaryUser createUserWithRegisterInfo(RegisterInfo registerInfo)
	{
		String userId = selectPrimaryUserMapper.getSumOfUser() + "";
		PrimaryUser primaryUser = PrimaryUserFactory.processPrimaryUser(registerInfo, userId,
				getConfigurationMapper().getDefaultSurfaceImageUrl());
		
		return primaryUser;
	}
	
	@Override
	public ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightSmsVerificationCode, String rightEmailVerificationCode,
			String verifiedPhoneNumber, String verifiedEmailAddress)
	{
		UserRegisterInfoInspectorImpl inspector =
				new UserRegisterInfoInspectorImpl(registerInfo);
		
		//get configuration info
		boolean isCheckSmsVerificationCode = getConfigurationMapper().getNeededSmsValidation();
		boolean isCheckEmailVerificationCode =
				getConfigurationMapper().getNeededEmailValidation();
		
		//get the same info from database
		int theSameUsernameCount = selectPrimaryUserMapper
				.getTheSameValueCountForUsername(registerInfo.getUsername());
		int theSamePhoneNumberCount = selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(registerInfo.getPhoneNumber());
		int theSameEmailCount =
				selectPrimaryUserMapper.getTheSameValueCountForEmail(registerInfo.getEmail());
		
		
		
		//verify the format of information of register
		int statusCode = inspector.checkFormat();
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		///checking the Sms verification code
		if(isCheckSmsVerificationCode==true)
		{
			statusCode = inspector
					.checkSmsVerificationCode(rightSmsVerificationCode, verifiedPhoneNumber);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		//checking the email verification code
		if(isCheckEmailVerificationCode==true)
		{
			statusCode = inspector
					.checkEmailVerificationCode(rightEmailVerificationCode, verifiedEmailAddress);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		//check is needed username
		if(isCheckEmailVerificationCode==false&&isCheckSmsVerificationCode==false)
		{
			if (registerInfo.getUsername()==null)
			{
				return new ResponseInfo(221,"用户名未填写");
			}
		}
		
		//verify whether the information of register are the same
		statusCode = inspector
				.checkTheSameRegisterInformation(theSameUsernameCount, theSamePhoneNumberCount,
						theSameEmailCount);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		return new OkResponseInfo();
	}
}
