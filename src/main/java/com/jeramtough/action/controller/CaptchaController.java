package com.jeramtough.action.controller;

import com.jeramtough.component.MCage;
import com.jeramtough.bean.responsebody.ResponseInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 14:35.
 */
@RestController
public class CaptchaController
{
	private final String flag = "captchaText";
	
	@RequestMapping(value = "randl/captcha", method = RequestMethod.GET)
	public void generateCaptcha(HttpServletRequest request, HttpServletResponse response)
	{
		MCage cage = new MCage();
		
		//save token to session
		String captchaText = cage.getLowerText();
		request.getSession().setAttribute(flag, captchaText);
		
		//processing captcha image
		try
		{
			setResponseHeaders(response);
			cage.draw(response.getOutputStream());
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "randl/checkCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo checkCaptcha(@RequestParam("text") String text,
			HttpServletRequest request)
	{
		String captchaText = (String) request.getSession().getAttribute(flag);
		ResponseInfo responseInfo = new ResponseInfo();
		if (captchaText==null)
		{
			responseInfo.setStatusCode(101);
			responseInfo.setMessage("图片验证码已vvvvvvvvvvvvvvvvvv失效，请刷新验证码图片");
		}
		else if (captchaText.equals(text))
		{
			responseInfo.setStatusCode(666);
			responseInfo.setMessage("图片验证码正确");
		}
		else
		{
			responseInfo.setStatusCode(102);
			responseInfo.setMessage("图片验证码错误");
		}
		return responseInfo;
	}
	
	//*********************************************
	
	/**
	 * Helper method, disables HTTP caching.
	 *
	 * @param resp response object to be modified
	 */
	private void setResponseHeaders(HttpServletResponse resp)
	{
		resp.setContentType("image/jpeg");
		resp.setHeader("Cache-Control", "no-cache, no-store");
		resp.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		resp.setDateHeader("Last-Modified", time);
		resp.setDateHeader("Date", time);
		resp.setDateHeader("Expires", time);
	}
	
	
}
