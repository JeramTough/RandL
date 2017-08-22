package com.jeramtough;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator
 * on 2017  August 06 Sunday 23:24.
 */
@SpringBootApplication
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
	
	
	public static class Constants
	{
		public static final String SMS_VERIFICATION_CODE_KEY = "sms_verification_code";
		public static final String EMAIL_VERIFICATION_CODE_KEY = "email_verification_code";
		
		public static final String SMS_VERIFICATION_FOR_PHONE_NUMBER_KEY =
				"sms_verification_for_phone_number_code";
		public static final String EMAIL_VERIFICATION_FOR_ADDRESS_KEY =
				"email_verification_address_code";
		
		public static final String NEW_QQ_USER_ID_KEY = "new_qq_user_id_key";
		/*public static final String QQ_USER_OPEN_ID_KEY = "qq_user_open_id_key";
		public static final String QQ_USER_PRIMARY_INFORMATION__KEY =
				"qq_user_primary_information_key";
		public static final String QQ_USER_OBJECT_KEY="qq_user_object_key";*/
		
		public static final String ALL_QQ_USER_PASSWORD = "their_passwords_are_qquser";
	}
}
