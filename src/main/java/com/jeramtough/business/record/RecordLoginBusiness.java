package com.jeramtough.business.record;

/**
 * Created by Administrator
 * on 2017  August 22 Tuesday 12:32.
 */
public interface RecordLoginBusiness extends RecordBusiness
{
	void reportLogin(String userId, String loginIp, String loginTime, String address);
}
