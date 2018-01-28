package com.jeramtough.action.controller;

import com.jeramtough.Application;
import com.jeramtough.business.UserPropertiesBusiness;
import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.requestbody.RequestInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 18:16.
 */
@RestController
public class PropertyController
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public PropertyController(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	@RequestMapping(value = "randl/user/properties", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo changeUserProperties(
			@RequestBody RequestInfo<PropertiesInfo> requestInfo,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_CODE_KEY, required = false) String rightSmsVerificationCode,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_CODE_KEY, required = false) String rightEmailVerificationCode,
			@SessionAttribute(value = Application.Constants.SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY, required = false) String verifiedPhoneNumber,
			@SessionAttribute(value = Application.Constants.EMAIL_VERIFICATION_FOR_ADDRESS_KEY, required = false) String verifiedEmailAddress)
	{
		//businesses
		UserPropertiesBusiness userPropertiesBusiness =
				(UserPropertiesBusiness) applicationContext.getBean("userPropertiesService");
		
		ResponseInfo responseInfo;
		responseInfo = userPropertiesBusiness
				.checkModifiedProperties(requestInfo.getMessage(), rightSmsVerificationCode,
						rightEmailVerificationCode, verifiedPhoneNumber, verifiedEmailAddress);
		
		if (responseInfo.getStatusCode() == 666)
		{
			userPropertiesBusiness.modifyProperties(requestInfo.getMessage());
		}
		
		return responseInfo;
	}
}
