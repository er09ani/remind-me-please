package com.remindermeapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date getDateTime(String dateString, String timeString) {
		// TODO Auto-generated method stub
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString +" "+ timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDateStringFromDateObject(Date date) {
		StringBuilder dateString = new StringBuilder();
		dateString.append(date.toString().split(" ")[0]);
		return dateString.toString();
	}
	
	public static String getTimeStringFromDateObject(Date date) {
		StringBuilder timeString = new StringBuilder();
		timeString.append(date.toString().split(" ")[1]);
		return timeString.toString();
	}
}
