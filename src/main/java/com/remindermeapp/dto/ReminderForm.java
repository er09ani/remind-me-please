package com.remindermeapp.dto;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.remindermeapp.entity.Reminder;
import com.remindermeapp.utils.DateUtil;

public class ReminderForm {
	
	public String title;
	
	public String Message;
	
	public Date sendTime;
	
	public Date createdOn;
	
	public Date modifiedOn;
	
	public String date;
	
	public String time;
	
	public String emailId;
	
	public String phoneNumber;
	
	public boolean emailIdChecked;
	
	public boolean phoneNumberChecked;
	
	private String timeInRegularFormat;

	private String dateInRegularFormat;

	public ReminderForm() {
		
	}
	
	

	public ReminderForm(Reminder reminder) {
		this.title = reminder.getTitle();
		this.Message = reminder.getMessage();
		
		this.date = DateUtil.getDateStringFromDateObject(reminder.getSendTime());
		this.time = DateUtil.getTimeStringFromDateObject(reminder.getSendTime());
		this.modifiedOn = reminder.getModifiedOn();
		
		this.emailIdChecked = reminder.isEmailIdChecked();
		this.phoneNumberChecked = reminder.isPhoneNumberChecked();
		this.sendTime = reminder.getSendTime();
		this.setTimeInRegularFormat(DateUtil.getTimeInRegularFormat(sendTime));
		this.setDateInRegularFormat(DateUtil.getDateInRegularFormat(sendTime));
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getMessage() {
		return Message;
	}



	public void setMessage(String message) {
		Message = message;
	}



	public Date getSendTime() {
		return sendTime;
	}



	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}



	public Date getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}



	public Date getModifiedOn() {
		return modifiedOn;
	}



	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public boolean isEmailIdChecked() {
		return emailIdChecked;
	}



	public void setEmailIdChecked(boolean emailIdChecked) {
		this.emailIdChecked = emailIdChecked;
	}



	public boolean isPhoneNumberChecked() {
		return phoneNumberChecked;
	}



	public void setPhoneNumberChecked(boolean phoneNumberChecked) {
		this.phoneNumberChecked = phoneNumberChecked;
	}



	public String getTimeInRegularFormat() {
		return timeInRegularFormat;
	}



	public void setTimeInRegularFormat(String timeInRegularFormat) {
		this.timeInRegularFormat = timeInRegularFormat;
	}



	public String getDateInRegularFormat() {
		return dateInRegularFormat;
	}



	public void setDateInRegularFormat(String dateInRegularFormat) {
		this.dateInRegularFormat = dateInRegularFormat;
	}



	@Override
	public String toString() {
		return "ReminderForm [title=" + title + ", Message=" + Message + ", sendTime=" + sendTime + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", date=" + date + ", time=" + time + "]";
	}
	
}
