package com.remindermeapp.entity;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.remindermeapp.user.CustomUser;

@Entity
public class BusinessUser {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Type(type="org.hibernate.type.UUIDCharType")
	public UUID businessUserId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	public CustomUser customUser;
	
	@OneToMany(mappedBy="businessUser", cascade = CascadeType.ALL)
	Collection<Reminder> reminders;

	public UUID getBusinessUserId() {
		return businessUserId;
	}

	public void setBusinessUserId(UUID businessUserId) {
		this.businessUserId = businessUserId;
	}

	public CustomUser getCustomUser() {
		return customUser;
	}

	public void setCustomUser(CustomUser customUser) {
		this.customUser = customUser;
	}

	public Collection<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(Collection<Reminder> reminders) {
		this.reminders = reminders;
	}

	@Override
	public String toString() {
		return "BusinessUser [businessUserId=" + businessUserId + ", customUser=" + customUser + ", reminders="
				+ reminders + "]";
	}
}
