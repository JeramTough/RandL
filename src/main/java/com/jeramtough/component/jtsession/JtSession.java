package com.jeramtough.component.jtsession;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class JtSession
{
	private String sessionId;
	private int maxLiveTime = 5 * 60 * 1000;
	private HashMap<String, Object> values;
	
	public JtSession(String sessionId)
	{
		this.sessionId = sessionId;
		values = new HashMap<>();
	}
	
	public JtSession(String sessionId, int maxLiveTime)
	{
		this.sessionId = sessionId;
		this.maxLiveTime = maxLiveTime;
	}
	
	public String getSessionId()
	{
		return sessionId;
	}
	
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
	
	public HashMap<String, Object> getValues()
	{
		return values;
	}
	
	public void setValues(HashMap<String, Object> values)
	{
		this.values = values;
	}
	
	public void setJtSessionLiveListener(ExecutorService executorService,
			JtSessionLiveListener jtSessionLiveListener)
	{
		executorService.submit(() ->
		{
			try
			{
				Thread.sleep(maxLiveTime);
				jtSessionLiveListener.end(sessionId);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		});
	}
}
