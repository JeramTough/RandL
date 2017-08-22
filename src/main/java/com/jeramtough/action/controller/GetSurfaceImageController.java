package com.jeramtough.action.controller;

import com.jeramtough.action.business.GetSurfaceImageBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 21:40.
 */
@RestController
public class GetSurfaceImageController
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public GetSurfaceImageController(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	
	@RequestMapping(value = "randl/surfaceImage", method = RequestMethod.GET)
	public void registerNewUser(@RequestParam String name, HttpServletResponse response)
	{
		response.setHeader("Content-Encoding", "UTF-8");
		response.setContentType("image/jpeg");
		
		GetSurfaceImageBusiness getSurfaceImageBusiness =
				(GetSurfaceImageBusiness) applicationContext.getBean("getSurfaceImageService");
		InputStream inputStream = getSurfaceImageBusiness.readSurfaceImageFileAsInputStream(name);
		
		try
		{
			int ch;
			while ((ch = inputStream.read()) != -1)
			{
				response.getOutputStream().write(ch);
			}
			inputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
