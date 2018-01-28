package com.jeramtough.action.controller;

import com.jeramtough.Application;
import com.jeramtough.business.SMSBusiness;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.component.jtsession.JtSession;
import com.jeramtough.component.jtsession.JtSessionManager;
import com.jeramtough.dao.mapper.AliConfigurationMapper;
import com.jeramtough.jtlog3.P;
import com.jeramtough.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 16:30.
 */
@RestController
public class SMSController
{
	private WebApplicationContext applicationContext;
	private int limitingSecond;
	
	@Autowired
	public SMSController(WebApplicationContext applicationContext,
			AliConfigurationMapper aliConfigurationMapper)
	{
		this.applicationContext = applicationContext;
		limitingSecond = aliConfigurationMapper.getLimitingSecond();
	}
	
	
	@RequestMapping(value = "randl/sendVerificationCode", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo sendVerificationCodeToPhone(
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam(value = "isTest", required = false, defaultValue = "false") boolean isTest,
			@RequestParam(value = "sessionId", required = false) String sessionId,
			@CookieValue(value = "canntSendCookie", required = false) String canntSendCookie,
			HttpServletResponse response, HttpServletRequest request)
	{
		//process a verification code and send is to client
		SMSBusiness smsBusiness = (SMSBusiness) applicationContext.getBean("SMSService");
		String verificationCode = smsBusiness.processVerificationCode();
		
		ResponseInfo responseInfo;
		if (isTest == false)
		{
			if (canntSendCookie == null)
			{
				//sending the verification code to the phone of user.
				responseInfo =
						smsBusiness.sendVerificationCodeToPhone(verificationCode, phoneNumber);
				if (responseInfo.getStatusCode() == 666)
				{
					//sign user have sent verification code in the some time.
					String time = new Date().getTime() + "";
					Cookie cookie = new Cookie("canntSendCookie", time);
					cookie.setPath("/randl");
					cookie.setMaxAge(limitingSecond);
					response.addCookie(cookie);
					
					this.saveVerifiedCodeAndPhoneNumberToSession(request, verificationCode,
							phoneNumber, sessionId);
				}
			}
			else
			{
				int second = DateTimeUtil
						.intervalTime(new Date(), new Date(Long.parseLong(canntSendCookie)));
				responseInfo = new ResponseInfo(103,
						"过于频繁发送短信验证码失败！请在" + (limitingSecond - second) + "秒后重试");
			}
		}
		else
		{
			this.saveVerifiedCodeAndPhoneNumberToSession(request, verificationCode,
					phoneNumber, sessionId);
			
			responseInfo = new OkResponseInfo("测试发送短信验证码成功：" + verificationCode + ",五分钟内有效");
		}
		
		return responseInfo;
	}
	
	//**********************************************
	private void saveVerifiedCodeAndPhoneNumberToSession(HttpServletRequest request,
			String verificationCode, String phoneNumber, String sessionId)
	{
		if (sessionId != null)
		{
			JtSession jtSession =
					JtSessionManager.getJtSessionManager().getJtSession(sessionId);
			if (jtSession == null)
			{
				jtSession = new JtSession(sessionId);
				JtSessionManager.getJtSessionManager().addJtSession(jtSession);
			}
			
			jtSession.getValues()
					.put(Application.Constants.SMS_VERIFICATION_CODE_KEY, verificationCode);
			jtSession.getValues()
					.put(Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY,
							phoneNumber);
		}
		else
		{
			//save the verification code to session
			HttpSession session = request.getSession();
			P.debug(session.hashCode());
			session.setMaxInactiveInterval(60 * 5);
			session.setAttribute(Application.Constants.SMS_VERIFICATION_CODE_KEY,
					verificationCode);
			session.setAttribute(Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY,
					phoneNumber);
		}
	}
}
