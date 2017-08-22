package com.jeramtough.action.business.record;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 00:17.
 */
public interface RecordRegisterBusiness extends RecordBusiness
{
	void reportRegister(String userId, String registerIp, String registerTime, String address);
}
