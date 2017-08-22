package com.jeramtough.action.aspect;

import com.jeramtough.action.business.record.RecordLoginBusiness;
import com.jeramtough.bean.LoginInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.util.JoinPointUtil;
import com.jeramtough.util.ServletUtil;
import com.jtlog.user.command.P;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2017  August 22 Tuesday 12:09.
 */
@Aspect
@Component
public class LoginAspect
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public LoginAspect(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	@AfterReturning(value =
			"execution(public com.jeramtough.bean.responsebody.ResponseInfo com.jeramtough" +
					".action" + ".controller" +
					".LoginController.*(..))", returning = "result")
	public void reportLoginedUsers(JoinPoint joinPoint, ResponseInfo result)
	{
		//businesses
		RecordLoginBusiness recordLoginBusiness=
				(RecordLoginBusiness) applicationContext.getBean("recordLoginService");
		
		//if user success login
		if (result.getStatusCode() == 666)
		{
			HttpServletRequest request = JoinPointUtil.getRequest(joinPoint);
			String userIp=((PrimaryUser)result.getMessage()).getUserId();
			String ip = ServletUtil.getClientIp(request);
			String address=recordLoginBusiness.getAddressByUserIp(ip);
			String time=recordLoginBusiness.getRecordedTime();
			
			recordLoginBusiness.reportLogin(userIp,ip,time,address);
		}
	}
}
