//package com.remindermeapp.utils;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class Example {
//    // Find your Account SID and Auth Token at twilio.com/console
//    // and set the environment variables. See http://twil.io/secure
//	public static String  TWILIO_ACCOUNT_SID = "AC66bbdfdfaba2b04b2702e10cee92ac4d";
//	public static String  TWILIO_AUTH_TOKEN = "dd2166b438c3e8ad3bdc7087b0c74b7d";
//    public static final String ACCOUNT_SID = "AC66bbdfdfaba2b04b2702e10cee92ac4d";
////    		System.getenv(TWILIO_ACCOUNT_SID);
//    public static final String AUTH_TOKEN = "dd2166b438c3e8ad3bdc7087b0c74b7d";
////    		System.getenv(TWILIO_AUTH_TOKEN);
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(new PhoneNumber("+919730049154"), new PhoneNumber("+19705483552"),
//                						"Hi there first Message from your remindeme app")
//        						.create();
//
//        System.out.println(message.getSid());
//    }
//}