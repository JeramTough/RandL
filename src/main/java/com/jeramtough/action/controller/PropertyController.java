package com.jeramtough.action.controller;

import com.jeramtough.action.business.UserPropertiesBusiness;
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
	public ResponseInfo changeUserProperties(@RequestBody RequestInfo<PropertiesInfo>
			requestInfo)
	{
		//businesses
		UserPropertiesBusiness userPropertiesBusiness=
				(UserPropertiesBusiness) applicationContext.getBean("userPropertiesService");
		
		ResponseInfo responseInfo;
		responseInfo=userPropertiesBusiness.checkModifiedProperties(requestInfo.getMessage());
		
		if(responseInfo.getStatusCode()==666)
		{
			userPropertiesBusiness.modifyProperties(requestInfo.getMessage());
		}
		
		return responseInfo;
	}
}
