package com.jeramtough.service;

import com.jeramtough.business.EmailBusiness;
import com.jeramtough.bean.EmailConfiguration;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.EmailConfigurationMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 14:06.
 */
@Service
public class EmailService implements EmailBusiness
{
	private final EmailConfiguration emailConfigurationn;
	private JavaMailSenderImpl sender;
	
	@Autowired
	public EmailService(EmailConfigurationMapper emailConfigurationMapper)
	{
		sender = new JavaMailSenderImpl();
		emailConfigurationn = emailConfigurationMapper.selectEmailConfiguration();
		
		sender.setHost(emailConfigurationn.getHost());
		sender.setUsername(emailConfigurationn.getUsername());
		sender.setPassword(emailConfigurationn.getPassword());
		sender.setDefaultEncoding(emailConfigurationn.getDefaultEncoding());
		sender.setPort(emailConfigurationn.getPort());
	}
	
	@Override
	public String processVerificationCode()
	{
		return RandomStringUtils.random(6, false, true);
	}
	
	@Override
	public ResponseInfo sendVerificationCodeToEmail(String code, String toEmail)
	{
		ResponseInfo responseInfo;
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(emailConfigurationn.getFromEmail());
		mailMessage.setSubject(emailConfigurationn.getSubject());
		
		String registeredText = String.format(emailConfigurationn.getRegisteredText(), code);
		
		mailMessage.setText(registeredText);
		mailMessage.setTo(toEmail);
		
		try
		{
			sender.send(mailMessage);
			responseInfo = new OkResponseInfo("发送邮件成功,验证码五分钟有效！");
		}
		catch (Exception e)
		{
			responseInfo = new ResponseInfo(105, "发送邮件失败! " + e.getMessage());
		}
		return responseInfo;
	}
}
