package com.jeramtough.action.controller;

import com.jeramtough.action.business.login.LoginBusiness;
import com.jeramtough.bean.requestbody.LoginInfo;
import com.jeramtough.bean.requestbody.RequestInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 15:00.
 */
@RestController
public class LoginController
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public LoginController(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	
	@RequestMapping(value = "randl/login/users", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo login(HttpServletRequest request,
			@RequestBody RequestInfo<LoginInfo> requestInfo)
	{
		//Businesses
		LoginBusiness loginBusiness =
				(LoginBusiness) applicationContext.getBean("loginService");
		
		//get the PrimaryUser object
		LoginInfo loginInfo = requestInfo.getMessage();
		PrimaryUser primaryUser = loginBusiness
				.getPrimaryUserByUepAndPassword(loginInfo.getUep(), loginInfo.getPassword());
		
		return loginBusiness.getResponseInfoByPrimaryUser(primaryUser);
		
	}
	
}
