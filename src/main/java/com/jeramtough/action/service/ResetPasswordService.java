package com.jeramtough.action.service;

import com.jeramtough.action.business.ResetPasswordBusiness;
import com.jeramtough.action.component.inspector.UserPropertiesInfoInspectorImpl;
import com.jeramtough.action.component.inspector.UserResetPasswordInfoInspector;
import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.requestbody.ResetPasswordInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import com.jeramtough.dao.mapper.UpdatePrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 11718
 * on 2017  九月 01 星期五 16:22.
 */
@Service
public class ResetPasswordService implements ResetPasswordBusiness
{
	
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	private final UpdatePrimaryUserMapper updatePrimaryUserMapper;
	
	@Autowired
	public ResetPasswordService(SelectPrimaryUserMapper selectPrimaryUserMapper,
			UpdatePrimaryUserMapper updatePrimaryUserMapper)
	{
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
		this.updatePrimaryUserMapper = updatePrimaryUserMapper;
	}
	
	@Override
	public ResponseInfo checkVerificationCode(ResetPasswordInfo resetPasswordInfo,
			String verifiedPhoneNumber, String verifiedEmailAddress,
			String rightSmsVerificationCode, String rightEmailVerificationCode)
	{
		
		UserResetPasswordInfoInspector inspector =
				new UserResetPasswordInfoInspector(resetPasswordInfo);
		
		//get the same info from database
		int theSamePhoneNumberCount = selectPrimaryUserMapper
				.getTheSameValueCountForPhoneNumber(verifiedPhoneNumber);
		int theSameEmailCount =
				selectPrimaryUserMapper.getTheSameValueCountForEmail(verifiedEmailAddress);
		
		int statusCode;
		
		//verify the format of information of new password
		statusCode = inspector.checkFormat();
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		//verify which sent verification code by phone or email
		statusCode = inspector.checkIsSentVerificationCode(rightSmsVerificationCode,
				rightEmailVerificationCode);
		if (statusCode != 666)
		{
			return new ResponseInfo(statusCode, inspector.getMessage());
		}
		
		
		if (verifiedPhoneNumber != null && verifiedEmailAddress == null)
		{
			//verify verified phone number
			statusCode = inspector.checkPhoneNumber(theSamePhoneNumberCount);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
			
			//checking the Sms verification code
			statusCode = inspector.checkSmsVerificationCode(rightSmsVerificationCode);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		
		if (verifiedEmailAddress != null && verifiedPhoneNumber == null)
		{
			//verify verified email address
			statusCode = inspector.checkEmailAddress(theSameEmailCount);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
			
			//checking the email verification code
			statusCode = inspector.checkEmailVerificationCode(rightEmailVerificationCode);
			if (statusCode != 666)
			{
				return new ResponseInfo(statusCode, inspector.getMessage());
			}
		}
		
		return new OkResponseInfo("重置密码成功");
	}
	
	@Override
	public void modifyPassword(ResetPasswordInfo resetPasswordInfo, String phoneNumber,
			String emailAddress)
	{
		new Thread(() -> {
			if (phoneNumber != null)
			{
				updatePrimaryUserMapper
						.updateUserPasswordByPhoneNumber(resetPasswordInfo.getNewPassword(),
								phoneNumber);
			}
			else if (emailAddress != null)
			{
				updatePrimaryUserMapper
						.updateUserPasswordByEmail(resetPasswordInfo.getNewPassword(),
								emailAddress);
			}
		}).start();
	}
	
}
	
