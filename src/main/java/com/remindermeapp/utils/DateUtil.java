package com.remindermeapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	
	public static String getTimeInRegularFormat(Date time){
		String pattern = "K:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String date1 = simpleDateFormat.format(time);
//		System.out.println(date1);
		
		return date1;
	}

	public static String getDateInRegularFormat(Date date) {
		
		
		Locale locale = new Locale("en", "");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String date1 = dateFormat.format(new Date());
//		System.out.print(date);
		return date1;
		
	}
}
