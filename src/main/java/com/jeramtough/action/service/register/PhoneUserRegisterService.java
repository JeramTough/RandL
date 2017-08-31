package com.jeramtough.action.service.register;

import com.jeramtough.action.business.register.PhoneUserRegisterBusiness;
import com.jeramtough.action.component.PrimaryUserFactory;
import com.jeramtough.action.component.inspector.PrimaryUserRegisterInfoInspector;
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
		/*ResponseInfo responseInfo = new ResponseInfo(666, "用户成功注册");
		
		if (rightSmsVerificationCode == null)
		{
			responseInfo.setStatusCode(209);
			responseInfo.setMessage("短信验证码以失效，请重新发送短信验证码到手机上！");
			return responseInfo;
		}
		
		if (registerInfo.getSmsVerificationCode() == null)
		{
			responseInfo.setStatusCode(208);
			responseInfo.setMessage("需要填写短信验证码");
			return responseInfo;
		}
		
		if (rightSmsVerificationCode.equals(registerInfo.getSmsVerificationCode()) == false)
		{
			responseInfo.setStatusCode(210);
			responseInfo.setMessage("短信验证码填写不正确！");
			return responseInfo;
		}
		
		if(registerInfo.getPhoneNumber()==null)
		{
			responseInfo.setStatusCode(217);
			responseInfo.setMessage("手机号码并未填写！");
		}
		
		if(registerInfo.getPhoneNumber().equals(verifiedPhoneNumber)==false)
		{
			responseInfo.setStatusCode(219);
			responseInfo.setMessage("注册的电话号码与验证的号码不是同一个！");
			return responseInfo;
		}
		
		//verify the information of register
		PhoneNumberValidator phoneNumberValidator=new PhoneNumberValidator();
		List<String> messages = phoneNumberValidator.validator(registerInfo.getPhoneNumber());
		if (messages.size() > 0)
		{
			StringBuilder responseMessage = new StringBuilder();
			for (String message : messages)
			{
				responseMessage.append("\n").append(message);
			}
			return new ResponseInfo(0, responseMessage);
		}
		
		if ( selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(registerInfo.getPhoneNumber()) > 0)
		{
			return new ResponseInfo(212, "手机号码已被注册，请换个未被注册过的");
		}
		
		return responseInfo;
	}*/
		PrimaryUserRegisterInfoInspector inspector =
				new PrimaryUserRegisterInfoInspector(registerInfo);
		
		
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
