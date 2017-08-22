package com.jeramtough.action.aspect;

import com.jeramtough.Application;
import com.jeramtough.action.business.record.RecordRegisterBusiness;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.util.JoinPointUtil;
import com.jeramtough.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2017  August 20 Sunday 23:37.
 */
@Aspect
@Component
public class RegisterAspect
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public RegisterAspect(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	@AfterReturning(value =
			"execution(public com.jeramtough.bean.responsebody.ResponseInfo com.jeramtough" +
					".action" + ".controller" +
					".RegisterController.*(..))", returning = "result")
	public void reportRegisteredUsers(JoinPoint joinPoint, ResponseInfo result)
	{
		//Businesses
		RecordRegisterBusiness recordRegisterBusiness =
				(RecordRegisterBusiness) applicationContext.getBean("recordRegisterService");
		
		//if user success register
		if (result.getStatusCode() == 666)
		{
			String ip = "";
			HttpServletRequest request = JoinPointUtil.getRequest(joinPoint);
			ip = ServletUtil.getClientIp(request);
			
			//record user register
			String userId =
					recordRegisterBusiness.getUserIdFromMessage((String) result.getMessage());
			String registerTime = recordRegisterBusiness.getRecordedTime();
			String address = recordRegisterBusiness.getAddressByUserIp(ip);
			
			recordRegisterBusiness.reportRegister(userId, ip, registerTime, address);
		}
	}
	
	@AfterReturning(value = "execution(public * com.jeramtough.action.controller" +
			".RegisterController.registerNewQQUser(..))")
	public void reportRegisteredQQUsers(JoinPoint joinPoint)
	{
		//Businesses
		RecordRegisterBusiness recordRegisterBusiness =
				(RecordRegisterBusiness) applicationContext.getBean("recordRegisterService");
		
		String ip = "";
		String userId="";
		
		HttpServletRequest request = JoinPointUtil.getRequest(joinPoint);
		
		userId= (String) request.getAttribute(Application.Constants.NEW_QQ_USER_ID_KEY);
		if(userId!=null)
		{
			ip = ServletUtil.getClientIp(request);
			
			//record new qq user register
			String registerTime = recordRegisterBusiness.getRecordedTime();
			String address = recordRegisterBusiness.getAddressByUserIp(ip);
			
			recordRegisterBusiness.reportRegister(userId, ip, registerTime, address);
		}
		
	}
	
	
}
