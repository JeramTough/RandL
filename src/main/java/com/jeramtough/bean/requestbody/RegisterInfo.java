package com.jeramtough.bean.requestbody;

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
