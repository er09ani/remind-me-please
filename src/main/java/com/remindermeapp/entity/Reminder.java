package com.remindermeapp.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.remindermeapp.dto.ReminderForm;
import com.remindermeapp.enums.ReminderStatus;
import com.remindermeapp.utils.DateUtil;

@Entity
public class Reminder {
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Type(type="org.hibernate.type.UUIDCharType")
	public UUID reminderId;
	
	public String title;
	
	public String Message;
	
	public Date sendTime;
	
	public Date createdOn;
	
	public Date modifiedOn;
	
	public boolean emailIdChecked;
	
	public boolean phoneNumberChecked;
	
	@Column(name="Status")
	public ReminderStatus remiderStatus;
	
	@ManyToOne
	@JoinColumn(name="business_user_id")
	public BusinessUser businessUser;
	

	public Reminder() {
		// TODO Auto-generated constructor stub
	}
	public Reminder(ReminderForm reminderForm) {
		this.title = reminderForm.getTitle();
		this.Message = reminderForm.getMessage();
		this.sendTime = DateUtil.getDateTime(reminderForm.getDate(), reminderForm.getTime());
		this.createdOn = reminderForm.getCreatedOn();
		this.modifiedOn = reminderForm.getModifiedOn();
	}

	public UUID getReminderId() {
		return reminderId;
	}

	public void setReminderId(UUID reminderId) {
		this.reminderId = reminderId;
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

	public ReminderStatus getRemiderStatus() {
		return remiderStatus;
	}

	public void setRemiderStatus(ReminderStatus remiderStatus) {
		this.remiderStatus = remiderStatus;
	}

	public BusinessUser getBusinessUser() {
		return businessUser;
	}

	public void setBusinessUser(BusinessUser businessUser) {
		this.businessUser = businessUser;
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
	@Override
	public String toString() {
		return "Reminder [reminderId=" + reminderId + ", title=" + title + ", Message=" + Message + ", sendTime="
				+ sendTime + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", emailIdChecked="
				+ emailIdChecked + ", phoneNumberChecked=" + phoneNumberChecked + ", remiderStatus=" + remiderStatus
				+ "]";
	}
	
}
