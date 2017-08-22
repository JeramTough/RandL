package com.jeramtough.action.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 18:54.
 */
@RestController
public abstract class MyController
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public MyController(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	public WebApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
}
