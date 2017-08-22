package com.jeramtough.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator
 * on 2017  August 10 Thursday 23:38.
 */
public class RegisterInfo
{
/*	用户id user_id
	电话号码 phone_number,
	电子邮箱 email
	性别 gender
	年龄 age
	职业 career
	生日 birthday
	昵称 nickname
	用户名 username，
	电话号码 phone_number,
	电子邮箱 email
	登录密码 login_password
	头像url  surface imaged url
	用户是否在线 is online
	qq号 qq number
	注册ip register_ip
	注册地点 register_adddress
	ADD FOREIGN KEY (`user_id`) REFERENCES `primary_user_info_tb` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
	注册时间 register_time
	登录ip logined_id
	登录地点 logined_address
	最后一次登录时间last_logined_time*/
	private String username;
	
	private String password;
	
	private String phoneNumber;
	
	private String gender;
	
	private String age;
	
	private String email;
	
	private String birthday;
	
	private String qqNumber;
	
	private String smsVerificationCode;
	
	private String emailVerificationCode;
	
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
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String getAge()
	{
		return age;
	}
	
	public void setAge(String age)
	{
		this.age = age;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getBirthday()
	{
		return birthday;
	}
	
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	public String getQqNumber()
	{
		return qqNumber;
	}
	
	public void setQqNumber(String qqNumber)
	{
		this.qqNumber = qqNumber;
	}
	
	public String getSmsVerificationCode()
	{
		return smsVerificationCode;
	}
	
	public void setSmsVerificationCode(String smsVerificationCode)
	{
		this.smsVerificationCode = smsVerificationCode;
	}
	
	public String getEmailVerificationCode()
	{
		return emailVerificationCode;
	}
	
	public void setEmailVerificationCode(String emailVerificationCode)
	{
		this.emailVerificationCode = emailVerificationCode;
	}
	
	@Override
	public String toString()
	{
		return "RegisterInfo{" + "username='" + username + '\'' + ", password='" + password +
				'\'' + ", phoneNumber='" + phoneNumber + '\'' + ", gender='" + gender + '\'' +
				", age='" + age + '\'' + ", email='" + email + '\'' + ", birthday='" +
				birthday + '\'' + ", qqNumber='" + qqNumber + '\'' +
				", smsVerificationCode='" + smsVerificationCode + '\'' +
				", emailVerificationCode='" + emailVerificationCode + '\'' + '}';
	}
}
