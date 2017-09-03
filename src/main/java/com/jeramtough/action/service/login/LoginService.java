package com.jeramtough.action.service.login;

import com.jeramtough.action.business.login.LoginBusiness;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.bean.user.PrimaryUser;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 15:39.
 */
@Service
public class LoginService implements LoginBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	
	@Autowired
	public LoginService(SelectPrimaryUserMapper selectPrimaryUserMapper)
	{
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
	}
	
	@Override
	public PrimaryUser getPrimaryUserByUepAndPassword(String uep, String password)
	{
		return selectPrimaryUserMapper.getPrimaryUser(uep,password);
	}
	
	@Override
	public ResponseInfo getResponseInfoByPrimaryUser(PrimaryUser primaryUser)
	{
		if (primaryUser!=null)
		{
			OkResponseInfo responseInfo=new OkResponseInfo();
			responseInfo.setMessage(primaryUser);
			return responseInfo;
		}
		else
		{
			ResponseInfo responseInfo=new ResponseInfo(301);
			responseInfo.setMessage("登录信息有误或者用户不存在");
			return responseInfo;
		}
	}
}
