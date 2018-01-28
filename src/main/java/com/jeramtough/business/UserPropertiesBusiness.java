package com.jeramtough.business;

import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 20:34.
 */
public interface UserPropertiesBusiness
{
	public ResponseInfo checkModifiedProperties(PropertiesInfo propertiesInfo,String rightSmsVerificationCode, String rightEmailVerificationCode,
			String verifiedPhoneNumber, String verifiedEmailAddress);
	
	public void modifyProperties(PropertiesInfo propertiesInfo);
}
