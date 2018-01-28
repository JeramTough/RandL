package com.jeramtough.component.ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator
 * on 2017  August 13 Sunday 18:34.
 */
public class AliCommunication
{
	private AliCommunicationConfig config;
	
	public AliCommunication(AliCommunicationConfig config)
	{
		this.config = config;
	}
	
	public SendSmsResponse sendSms(String sentCode, String sentPhoneNumber) throws ClientException
	{
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		
		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile
				.getProfile("cn-hangzhou", config.getAccessKeyId(),
						config.getAccessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", config.getProduct(),
				config.getDomain());
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(sentPhoneNumber);
		request.setSignName(config.getSignature());
		request.setTemplateCode(config.getTemplateCode());
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(
				"{\"" + config.getSentCodeName() + "\":\"" + sentCode + "\"}");
		
		//选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		//request.setSmsUpExtendCode("90997");
		
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(config.getYourOutId());
		
		//开始发送
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + sendSmsResponse.getCode());
		System.out.println("Message=" + sendSmsResponse.getMessage());
		System.out.println("RequestId=" + sendSmsResponse.getRequestId());
		System.out.println("BizId=" + sendSmsResponse.getBizId());
		return sendSmsResponse;
	}
	
	private QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException
	{
		//可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		
		//初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile
				.getProfile("cn-hangzhou", config.getAccessKeyId(),
						config.getAccessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", config.getProduct(),
				config.getDomain());
		IAcsClient acsClient = new DefaultAcsClient(profile);
		
		//组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		//必填-号码
		request.setPhoneNumber("15000000000");
		//可选-流水号
		request.setBizId(bizId);
		//必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		//必填-页大小
		request.setPageSize(10L);
		//必填-当前页码从1开始计数
		request.setCurrentPage(1L);
		
		//hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
		
		return querySendDetailsResponse;
	}
}
