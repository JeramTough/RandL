package com.jeramtough.action.controller;

import com.jeramtough.Application;
import com.jeramtough.business.QQBusiness;
import com.jeramtough.business.register.EmailUserRegisterBusiness;
import com.jeramtough.business.register.PhoneUserRegisterBusiness;
import com.jeramtough.business.register.PrimaryUserRegisterBusiness;
import com.jeramtough.business.register.QQUserRegisterBusiness;
import com.jeramtough.component.jtsession.JtSession;
import com.jeramtough.component.jtsession.JtSessionManager;
import com.jeramtough.component.qqs.QQAccessToken;
import com.jeramtough.bean.requestbody.RegisterInfo;
import com.jeramtough.bean.requestbody.RequestInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.jtlog3.P;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator
 * on 2017  August 10 Thursday 22:37.
 */

@RestController
public class RegisterController
{
	private final WebApplicationContext applicationContext;
	
	public RegisterController(ApplicationContext applicationContext)
	{
		this.applicationContext = (WebApplicationContext) applicationContext;
	}
	
	@RequestMapping(value = "randl/register/users", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo registerNewUser(@RequestBody RequestInfo<RegisterInfo> requestInfo,
			HttpServletRequest request,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_CODE_KEY, required = false) String rightSmsVerificationCode,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_CODE_KEY, required = false) String rightEmailVerificationCode,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY, required = false) String verifiedPhoneNumber,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_FOR_ADDRESS_KEY, required = false) String verifiedEmailAddress)
	{
		//response body
		ResponseInfo responseInfo;
		
		RegisterInfo registerInfo = requestInfo.getMessage();
		
		//C businesses
		PrimaryUserRegisterBusiness primaryUserRegisterBusiness =
				(PrimaryUserRegisterBusiness) applicationContext
						.getBean("primaryUserRegisterService");
		
		responseInfo = primaryUserRegisterBusiness
				.checkRegisterInfo(registerInfo, rightSmsVerificationCode,
						rightEmailVerificationCode, verifiedPhoneNumber, verifiedEmailAddress);
		if (responseInfo.getStatusCode() == 666)
		{
			PrimaryUser primaryUser =
					primaryUserRegisterBusiness.createUserWithRegisterInfo(registerInfo);
			
			responseInfo =
					primaryUserRegisterBusiness.savePrimaryUserToPersistentLayer(primaryUser);
		}
		return responseInfo;
	}
	
	@RequestMapping(value = "randl/register/qqUsers")
	public void registerNewQQUser(HttpServletRequest request, HttpServletResponse response)
	{
		//C businesses
		QQUserRegisterBusiness qqUserRegisterBusiness =
				(QQUserRegisterBusiness) applicationContext.getBean("QQUserRegisterService");
		QQBusiness qqBusiness = (QQBusiness) applicationContext.getBean("QQService");
		
		QQAccessToken qqAccessToken = new QQAccessToken(request);
		String userOpenId = qqAccessToken.getUserOpenId();
		
		//create a new user if the qq user have not registered
		if (!qqBusiness.isHaveRegisteredForQQUser(userOpenId))
		{
			PrimaryUser primaryUser = qqUserRegisterBusiness.createUserForQQUser(userOpenId,
					qqAccessToken.getQQUserPrimaryInformation());
			
			//save the user id so that record register activities
			request.setAttribute(Application.Constants.NEW_QQ_USER_ID_KEY,
					primaryUser.getUserId());
			qqUserRegisterBusiness.savePrimaryUserToPersistentLayer(primaryUser);
		}
		
		try
		{
			String url =
					qqUserRegisterBusiness.getUserOpenIdUri() + "?qqUserOpenId=" + userOpenId;
			response.sendRedirect(url);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "randl/register/phoneUsers", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo registerNewPhoneUser(
			@RequestBody RequestInfo<RegisterInfo> requestInfo, HttpServletRequest request,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_CODE_KEY, required = false) String rightSmsVerificationCode,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY, required = false) String verifiedPhoneNumber,
			@RequestParam(value = "sessionId", required = false) String sessionId)
	{
		PhoneUserRegisterBusiness phoneUserRegisterBusiness =
				(PhoneUserRegisterBusiness) applicationContext
						.getBean("phoneUserRegisterService");
		
		if (sessionId != null)
		{
			JtSession jtSession =
					JtSessionManager.getJtSessionManager().getJtSession(sessionId);
			if (jtSession != null)
			{
				rightSmsVerificationCode = (String) jtSession.getValues()
						.get(Application.Constants.SMS_VERIFICATION_CODE_KEY);
				verifiedPhoneNumber = (String) jtSession.getValues()
						.get(Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY);
			}
			else
			{
			}
		}
		
		ResponseInfo responseInfo;
		
		responseInfo = phoneUserRegisterBusiness
				.checkRegisterInfo(requestInfo.getMessage(), rightSmsVerificationCode,
						verifiedPhoneNumber);
		if (responseInfo.getStatusCode() == 666)
		{
			PrimaryUser primaryUser = phoneUserRegisterBusiness
					.createUserWithPhoneNumber(requestInfo.getMessage().getPhoneNumber(),
							requestInfo.getMessage().getPassword());
			
			responseInfo =
					phoneUserRegisterBusiness.savePrimaryUserToPersistentLayer(primaryUser);
		}
		return responseInfo;
	}
	
	@RequestMapping(value = "randl/register/emailUsers", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo registerNewEmailUser(
			@RequestBody RequestInfo<RegisterInfo> requestInfo, HttpServletRequest request,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_CODE_KEY, required = false) String rightEmailVerificationCode,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_FOR_ADDRESS_KEY, required = false) String verifiedEmailAddress)
	{
		EmailUserRegisterBusiness emailUserRegisterBusiness =
				(EmailUserRegisterBusiness) applicationContext
						.getBean("emailUserRegisterService");
		
		ResponseInfo responseInfo;
		
		responseInfo = emailUserRegisterBusiness
				.checkRegisterInfo(requestInfo.getMessage(), rightEmailVerificationCode,
						verifiedEmailAddress);
		if (responseInfo.getStatusCode() == 666)
		{
			PrimaryUser primaryUser = emailUserRegisterBusiness
					.createUserWithEmail(requestInfo.getMessage().getEmail(),
							requestInfo.getMessage().getPassword());
			
			responseInfo =
					emailUserRegisterBusiness.savePrimaryUserToPersistentLayer(primaryUser);
		}
		return responseInfo;
	}
	
	
}
