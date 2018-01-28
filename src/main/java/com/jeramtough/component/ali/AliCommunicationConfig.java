package com.jeramtough.component.ali;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 17:44.
 */
@Component
public class AliCommunicationConfig
{
	private int limitingSecond = 120;
	//产品名称:云通信短信API产品,开发者无需替换
	private final String product = "Dysmsapi";
	//产品域名,开发者无需替换
	private final String domain = "dysmsapi.aliyuncs.com";
	
	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private String accessKeyId = "LTAInCwSExXo2Ib9";
	private String accessKeySecret = "IsujhXenc0Y8tfdEzeNdzOJEpH15SU";
	
	//必填:短信签名-可在短信控制台中找到
	private String signature = "每投网";
	//必填:短信模板-可在短信控制台中找到
	private String templateCode = "SMS_85570038";
	//验证码变量名
	private String sentCodeName = "number";
	
	//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	private String yourOutId;
	
	public int getLimitingSecond()
	{
		return limitingSecond;
	}
	
	public void setLimitingSecond(int limitingSecond)
	{
		this.limitingSecond = limitingSecond;
	}
	
	public String getProduct()
	{
		return product;
	}
	
	public String getDomain()
	{
		return domain;
	}
	
	public String getAccessKeyId()
	{
		return accessKeyId;
	}
	
	public void setAccessKeyId(String accessKeyId)
	{
		this.accessKeyId = accessKeyId;
	}
	
	public String getAccessKeySecret()
	{
		return accessKeySecret;
	}
	
	public void setAccessKeySecret(String accessKeySecret)
	{
		this.accessKeySecret = accessKeySecret;
	}
	
	public String getSignature()
	{
		return signature;
	}
	
	public void setSignature(String signature)
	{
		this.signature = signature;
	}
	
	public String getTemplateCode()
	{
		return templateCode;
	}
	
	public void setTemplateCode(String templateCode)
	{
		this.templateCode = templateCode;
	}
	
	public String getSentCodeName()
	{
		return sentCodeName;
	}
	
	public void setSentCodeName(String sentCodeName)
	{
		this.sentCodeName = sentCodeName;
	}
	
	public String getYourOutId()
	{
		return yourOutId;
	}
	
	public void setYourOutId(String yourOutId)
	{
		this.yourOutId = yourOutId;
	}
	
	@Override
	public String toString()
	{
		return "AliCommunicationConfig{" + "product='" + product + '\'' + ", domain='" +
				domain + '\'' + ", accessKeyId='" + accessKeyId + '\'' +
				", accessKeySecret='" + accessKeySecret + '\'' + ", signature='" + signature +
				'\'' + ", templateCode='" + templateCode + '\'' + ", sentCodeName='" +
				sentCodeName + '\'' + ", yourOutId='" + yourOutId + '\'' + '}';
	}
}
