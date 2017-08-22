package com.jeramtough.bean;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 14:16.
 */
public class EmailConfiguration
{
	private String host;
	private String username;
	private String password;
	private String defaultEncoding;
	private String fromEmail;
	private String subject;
	private String registeredText;
	private String passwordRetakedText;
	private int port;
	
	public String getHost()
	{
		return host;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public void setHost(String host)
	{
		this.host = host;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getDefaultEncoding()
	{
		return defaultEncoding;
	}
	
	public void setDefaultEncoding(String defaultEncoding)
	{
		this.defaultEncoding = defaultEncoding;
	}
	
	public String getFromEmail()
	{
		return fromEmail;
	}
	
	public void setFromEmail(String fromEmail)
	{
		this.fromEmail = fromEmail;
	}
	
	public String getRegisteredText()
	{
		return registeredText;
	}
	
	public void setRegisteredText(String registeredText)
	{
		this.registeredText = registeredText;
	}
	
	public String getPasswordRetakedText()
	{
		return passwordRetakedText;
	}
	
	public void setPasswordRetakedText(String passwordRetakedText)
	{
		this.passwordRetakedText = passwordRetakedText;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
}
