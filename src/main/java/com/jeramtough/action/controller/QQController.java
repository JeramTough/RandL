package com.jeramtough.action.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator
 * on 2017  August 15 Tuesday 16:34.
 */
@RestController
public class QQController extends MyController
{
	public QQController(WebApplicationContext applicationContext)
	{
		super(applicationContext);
	}
	
	@RequestMapping(value = "randl/openQQLoginPage", method = RequestMethod.GET)
	public void openQQUserLoginPage(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html;charset=utf-8");
		try
		{
			String url = new Oauth().getAuthorizeURL(request);
			response.sendRedirect(url);
		}
		catch (QQConnectException | IOException e)
		{
			e.printStackTrace();
		}
	}
}
