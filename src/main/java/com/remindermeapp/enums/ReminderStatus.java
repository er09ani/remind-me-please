package com.remindermeapp.enums;

public enum ReminderStatus {
	CREATED(1, "Created"),
	PENDING(2, "Pending"),
	COMPLETED(3, "Completed"),
	SNOOZED(4, "Snoozed");
	String sval;
	ReminderStatus(int i, String string) {
		this.sval = string;
	}
	
	public String getSval() {
		return this.sval;
	}
}
