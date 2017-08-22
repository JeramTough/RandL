package com.jeramtough.action.business.record;

/**
 * Created by Administrator
 * on 2017  August 21 Monday 00:26.
 */
public interface RecordBusiness
{
	/**
	 * @param ip the ip of client that is registered
	 * @return the registerAddress realistic address of client that is registered
	 */
	String getAddressByUserIp(String ip);
	
	/**
	 * @return current time for yyyy-mm-dd HH:mm:ss
	 */
	String getRecordedTime();
	
	/**
	 * @param message the message is returned to client
	 * @return the userId in the message
	 */
	String getUserIdFromMessage(String message);
}
