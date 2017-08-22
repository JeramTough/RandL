package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.EmailUserRegisterBusiness;
import com.jeramtough.action.component.PrimaryUserFactory;
import com.jeramtough.action.component.validator.EmailValidator;
import com.jeramtough.bean.RegisterInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.ConfigurationMapper;
import com.jeramtough.dao.mapper.InsertPrimaryUserMapper;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

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
			String rightEmailVerificationCode,String verifiedEmailAddress)
	{
		ResponseInfo responseInfo = new ResponseInfo(666, "用户成功注册");
		
		if (rightEmailVerificationCode == null)
		{
			responseInfo.setStatusCode(214);
			responseInfo.setMessage("邮箱验证码以失效，请重新发送验证码到邮箱上！");
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
		
		if(registerInfo.getEmail() == null)
		{
			return new ResponseInfo(218, "邮箱地址并未填写");
		}
		
		if(registerInfo.getEmail().equals(verifiedEmailAddress)==false)
		{
			responseInfo.setStatusCode(220);
			responseInfo.setMessage("注册的邮箱地址与验证的邮箱不是同一个！");
			return responseInfo;
		}
		
		//verify the information of register
		EmailValidator emailValidator=new EmailValidator();
		List<String> messages = emailValidator.validator(registerInfo.getEmail());
		if (messages.size() > 0)
		{
			StringBuilder responseMessage = new StringBuilder();
			for (String message : messages)
			{
				responseMessage.append("\n").append(message);
			}
			return new ResponseInfo(0, responseMessage);
		}
		
		
		if (selectPrimaryUserMapper.getTheSameValueCountForEmail(registerInfo.getEmail()) >
						0)
		{
			return new ResponseInfo(213, "邮箱已被注册，请换个未被注册过的");
		}
		
		return responseInfo;
	}
	
}
