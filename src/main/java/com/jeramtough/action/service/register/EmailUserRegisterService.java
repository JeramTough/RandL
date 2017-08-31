package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.EmailUserRegisterBusiness;
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
 * on 2017  August 16 Wednesday 16:36.
 */
@Service
public class EmailUserRegisterService extends RegisterService
		implements EmailUserRegisterBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	
	@Autowired
	public EmailUserRegisterService(WebApplicationContext applicationContext,
			ConfigurationMapper configurationMapper,
			InsertPrimaryUserMapper insertPrimaryUserMapper,
			SelectPrimaryUserMapper selectPrimaryUserMapper)
	{
		super(applicationContext, configurationMapper, insertPrimaryUserMapper);
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
	}
	
	@Override
	public PrimaryUser createUserWithEmail(String email, String password)
	{
		String userId = selectPrimaryUserMapper.getSumOfUser() + "";
		PrimaryUser primaryUser = PrimaryUserFactory
				.processPrimaryUserWithEmail(userId, email, password,
						getConfigurationMapper().getDefaultSurfaceImageUrl());
		return primaryUser;
	}
	
	@Override
	public ResponseInfo checkRegisterInfo(RegisterInfo registerInfo,
			String rightEmailVerificationCode, String verifiedEmailAddress)
	{
		UserRegisterInfoInspectorImpl inspector =
				new UserRegisterInfoInspectorImpl(registerInfo);
		
		
		int theSameEmailCount =
				selectPrimaryUserMapper.getTheSameValueCountForEmail(registerInfo.getEmail());
		
		
		//verify the format of information of register
		int statusCode = inspector.checkFormat();
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		//checking the email verification code
		statusCode = inspector
				.checkEmailVerificationCode(rightEmailVerificationCode, verifiedEmailAddress);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		//verify whether the information of register are the same
		statusCode = inspector
				.checkTheSameRegisterInformation(0, 0,
						theSameEmailCount);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		return new OkResponseInfo();
	}
	
}
