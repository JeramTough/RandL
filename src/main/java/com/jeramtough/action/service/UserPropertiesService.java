package com.jeramtough.action.service;

import com.jeramtough.action.business.UserPropertiesBusiness;
import com.jeramtough.bean.requestbody.PropertiesInfo;
import com.jeramtough.bean.responsebody.OkResponseInfo;
import com.jeramtough.bean.responsebody.ResponseInfo;
import com.jeramtough.dao.mapper.SelectPrimaryUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 11718
 * on 2017  八月 31 星期四 20:37.
 */
@Service
public class UserPropertiesService implements UserPropertiesBusiness
{
	private final SelectPrimaryUserMapper selectPrimaryUserMapper;
	
	@Autowired
	public UserPropertiesService(SelectPrimaryUserMapper selectPrimaryUserMapper)
	{
		this.selectPrimaryUserMapper = selectPrimaryUserMapper;
	}
	
	@Override
	public ResponseInfo checkModifiedProperties(PropertiesInfo propertiesInfo)
	{
		if(propertiesInfo.getUserId()==null||selectPrimaryUserMapper.getUserCountByUserId
				(propertiesInfo.getUserId())==0)
		{
			ResponseInfo responseInfo=new ResponseInfo(222);
			responseInfo.setMessage("userId 未填写或者不存在");
			return responseInfo;
		}
		
		
		return new OkResponseInfo("修改成功");
	}
	
	@Override
	public void modifyProperties(PropertiesInfo propertiesInfo)
	{
	}
}
