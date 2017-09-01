package com.jeramtough.action.controller;

import com.jeramtough.Application;
import com.jeramtough.action.business.ResetPasswordBusiness;
import com.jeramtough.bean.requestbody.RequestInfo;
import com.jeramtough.bean.requestbody.ResetPasswordInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 15:53.
 */
@RestController
public class ResetPasswordController extends MyController
{
	
	
	public ResetPasswordController(WebApplicationContext applicationContext)
	{
		super(applicationContext);
	}
	
	@RequestMapping(value = "randl/reset/password", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo resetPassword(@RequestBody RequestInfo<ResetPasswordInfo> requestInfo,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_CODE_KEY, required = false) String rightSmsVerificationCode,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_CODE_KEY, required = false) String rightEmailVerificationCode,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY, required = false) String verifiedPhoneNumber,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_FOR_ADDRESS_KEY, required = false) String verifiedEmailAddress)
	{
		//businesses
		ResetPasswordBusiness resetPasswordBusiness =
				(ResetPasswordBusiness) getApplicationContext()
						.getBean("resetPasswordService");
		
		ResponseInfo responseInfo = resetPasswordBusiness
				.checkVerificationCode(requestInfo.getMessage(), verifiedPhoneNumber,
						verifiedEmailAddress, rightSmsVerificationCode,
						rightEmailVerificationCode);
		
		if (responseInfo.getStatusCode() == 666)
		{
			resetPasswordBusiness.modifyPassword(requestInfo.getMessage(),
					verifiedPhoneNumber,verifiedEmailAddress);
		}
		
		return responseInfo;
	}
	
}

