package com.jeramtough.component.jtsession;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author 11718
 */
public class JtSessionManager implements JtSessionLiveListener
{
	private static volatile JtSessionManager jtSessionManager;
	private HashMap<String, JtSession> jtSessionHashMap;
	private ExecutorService executorService;
	
	private JtSessionManager()
	{
		jtSessionHashMap = new HashMap<>();
		executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
	}
	
	public static JtSessionManager getJtSessionManager()
	{
		if (jtSessionManager == null)
		{
			synchronized (JtSessionManager.class)
			{
				if (jtSessionManager == null)
				{
					jtSessionManager = new JtSessionManager();
				}
			}
		}
		return jtSessionManager;
	}
	
	@Override
	public void end(String sessionId)
	{
		jtSessionHashMap.remove(sessionId);
	}
	
	public void addJtSession(JtSession jtSession)
	{
		jtSession.setJtSessionLiveListener(executorService, this);
		jtSessionHashMap.put(jtSession.getSessionId(), jtSession);
	}
	
	public JtSession getJtSession(String sessionId)
	{
		return jtSessionHashMap.get(sessionId);
	}
}
