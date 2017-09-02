package com.jeramtough.action.controller;

import com.jeramtough.action.business.SurfaceImageBusiness;
import com.jeramtough.bean.responsebody.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator
 * on 2017  August 11 Friday 21:40.
 */
@RestController
public class SurfaceImageController
{
	private final WebApplicationContext applicationContext;
	
	@Autowired
	public SurfaceImageController(WebApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	
	@RequestMapping(value = "randl/surfaceImage", method = RequestMethod.GET)
	public void getUserSurfaceImage(@RequestParam String name, HttpServletResponse response)
	{
		response.setHeader("Content-Encoding", "UTF-8");
		response.setContentType("image/jpeg");
		
		SurfaceImageBusiness surfaceImageBusiness =
				(SurfaceImageBusiness) applicationContext.getBean("surfaceImageService");
		InputStream inputStream = surfaceImageBusiness.readSurfaceImageFileAsInputStream(name);
		
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
	
	
	@RequestMapping(value = "randl/surfaceImage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo uploadUserSurfaceImage(@RequestParam("file") MultipartFile file)
	{
		SurfaceImageBusiness surfaceImageBusiness =
				(SurfaceImageBusiness) applicationContext.getBean("surfaceImageService");
		
		String suffix=file.getContentType().split("/")[1].equals("png")?"png":
				"jpg";
		
		ResponseInfo responseInfo = null;
		try
		{
			responseInfo=surfaceImageBusiness.writeSurfaceImageFileToLocal(file
							.getInputStream(),
					suffix);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return responseInfo;
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ResponseInfo handleStorageFileNotFound() {
		return new ResponseInfo(107,"不存在该头像文件");
	}
}
