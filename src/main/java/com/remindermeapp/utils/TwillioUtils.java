//package com.remindermeapp.utils;
//
//import com.remindermeapp.entity.Reminder;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class TwillioUtils {
//	public static final String ACCOUNT_SID = "AC66bbdfdfaba2b04b2702e10cee92ac4d";
//	//	System.getenv(TWILIO_ACCOUNT_SID);
//	public static final String AUTH_TOKEN = "dd2166b438c3e8ad3bdc7087b0c74b7d";
//	//	System.getenv(TWILIO_AUTH_TOKEN);
//	public static void sendReminderMessage(String toPhoneNumber, Reminder reminder) {
//		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber("+19705483552"),
//                						"Hey "+ reminder.getBusinessUser().getCustomUser().getFirstName()+"!\n"+  
//        "Your are being reminded for: '"+reminder.getTitle() + "' in context of : "+ reminder.getMessage() +" \n"
//        + "for more information head to <url>")
//        						.create();
//	}
//}
