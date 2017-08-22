package com.jeramtough.action.controller;

import com.jeramtough.Application;
import com.jeramtough.action.business.EmailBusiness;
import com.jeramtough.bean.responsebody.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator
 * on 2017  August 14 Monday 21:50.
 */
@RestController
public class EmailController
{
	private final ApplicationContext applicationContext;
	
	@Autowired
	public EmailController(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	@RequestMapping(value = "randl/sendVerificationCodeToEmail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo sendVerificationCodeToEmail(@RequestParam("toEmail") String toEmail,
			HttpServletRequest request)
	{
		EmailBusiness emailBusiness =
				(EmailBusiness) applicationContext.getBean("emailService");
		
		String verificationCode = emailBusiness.processVerificationCode();
		ResponseInfo responseInfo =
				emailBusiness.sendVerificationCodeToEmail(verificationCode, toEmail);
		
		if (responseInfo.getStatusCode() == 666)
		{
			//save the verification code of email to session
			HttpSession session = request.getSession();
			session.setAttribute(Application.Constants.EMAIL_VERIFICATION_CODE_KEY,
					verificationCode);
			session.setMaxInactiveInterval(5*60);
		}
		
		return responseInfo;
	}
	
}
