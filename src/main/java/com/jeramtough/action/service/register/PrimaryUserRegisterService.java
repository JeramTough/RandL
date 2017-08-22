package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.PrimaryUserRegisterBusiness;
import com.jeramtough.action.component.PrimaryUserFactory;
import com.jeramtough.bean.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.action.component.validator.RegisterInfoValidator;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

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
		RegisterInfoValidator registerInfoValidator = new RegisterInfoValidator();
		
		ResponseInfo responseInfo = new ResponseInfo(666, "用户成功注册");
		
		//checking the Sms verification code
		if (getConfigurationMapper().getNeededSmsValidation())
		{
			if (rightSmsVerificationCode == null)
			{
				responseInfo.setStatusCode(209);
				responseInfo.setMessage("短信验证码以失效，用户请重新发送短信验证码到手机上！");
				return responseInfo;
			}
			
			if (registerInfo.getSmsVerificationCode() == null)
			{
				responseInfo.setStatusCode(208);
				responseInfo.setMessage("需要填写短信验证码");
				return responseInfo;
			}
			
			
			if (rightSmsVerificationCode.equals(registerInfo.getSmsVerificationCode()) ==
					false)
			{
				responseInfo.setStatusCode(210);
				responseInfo.setMessage("短信验证码填写不正确！");
				return responseInfo;
			}
			
			if(registerInfo.getPhoneNumber().equals(verifiedPhoneNumber)==false)
			{
				responseInfo.setStatusCode(219);
				responseInfo.setMessage("注册的电话号码与验证的号码不是同一个！");
				return responseInfo;
			}
		}
		
		//checking the email verification code
		if (getConfigurationMapper().getNeededEmailValidation() == true)
		{
			if (rightEmailVerificationCode == null)
			{
				responseInfo.setStatusCode(214);
				responseInfo.setMessage("邮箱验证码以失效，用户请重新发送验证码到邮箱上！");
				return responseInfo;
			}
			
			if (registerInfo.getEmailVerificationCode() == null)
			{
				responseInfo.setStatusCode(215);
				responseInfo.setMessage("需要填写邮箱验证码");
				return responseInfo;
			}
			
			
			if (rightEmailVerificationCode.equals(registerInfo.getEmailVerificationCode()) ==
					false)
			{
				responseInfo.setStatusCode(2116);
				responseInfo.setMessage("邮箱验证码填写不正确！");
				return responseInfo;
			}
			
			if(registerInfo.getEmail().equals(verifiedEmailAddress)==false)
			{
				responseInfo.setStatusCode(220);
				responseInfo.setMessage("注册的邮箱地址与验证的邮箱不是同一个！");
				return responseInfo;
			}
		}
		
		//verify the information of register
		List<String> messages = registerInfoValidator.validator(registerInfo);
		if (messages.size() > 0)
		{
			StringBuilder responseMessage = new StringBuilder();
			for (String message : messages)
			{
				responseMessage.append("\n").append(message);
			}
			return new ResponseInfo(0, responseMessage);
		}
		
		//verify whether the information of register are the same
		if (registerInfo.getUsername() != null && selectPrimaryUserMapper
				.getTheSameValueCountForUsername(registerInfo.getUsername()) > 0)
		{
			return new ResponseInfo(211, "用户名已被注册，请换个未被注册过的");
		}
		if (registerInfo.getPhoneNumber() != null && selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(registerInfo.getPhoneNumber()) > 0)
		{
			return new ResponseInfo(212, "手机号码已被注册，请换个未被注册过的");
		}
		if (registerInfo.getEmail() != null &&
				selectPrimaryUserMapper.getTheSameValueCountForEmail(registerInfo.getEmail()) >
						0)
		{
			return new ResponseInfo(213, "邮箱已被注册，请换个未被注册过的");
		}
		
		return responseInfo;
	}
}
