package com.jeramtough.action.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jeramtough.action.business.SMSBusiness;
import com.jeramtough.action.component.ali.AliCommunication;
import com.jeramtough.action.component.ali.AliCommunicationConfig;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.AliConfigurationMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 17:53.
 */
@Service
public class SMSService implements SMSBusiness
{
	private final AliConfigurationMapper aliConfigurationMapper;
	
	@Autowired
	public SMSService(AliConfigurationMapper aliConfigurationMapper)
	{
		this.aliConfigurationMapper = aliConfigurationMapper;
	}
	
	@Override
	public String processVerificationCode()
	{
		return RandomStringUtils.random(4, false, true);
	}
	
	@Override
	public ResponseInfo sendVerificationCodeToPhone(String code, String phoneNumber)
	{
		ResponseInfo responseInfo=null;
		
		AliCommunicationConfig config =
				aliConfigurationMapper.getAliCommunicationConfiguration();
		
		AliCommunication aliCommunication=new AliCommunication(config);
		try
		{
			SendSmsResponse sendSmsResponse=aliCommunication.sendSms(code,phoneNumber);
			if (sendSmsResponse.getCode().equals("OK"))
			{
				responseInfo = new OkResponseInfo("发送短信验证码成功~\n五分钟内有效");
			}
			else
			{
				responseInfo = new ResponseInfo(100, "发送短信验证码失败！" +sendSmsResponse.getMessage());
			}
		}
		catch (ClientException e)
		{
			e.printStackTrace();
		}
		return responseInfo;
	}
}
