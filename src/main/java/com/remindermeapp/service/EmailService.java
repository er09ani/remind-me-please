package com.remindermeapp.service;

import com.remindermeapp.entity.EmailDetails;

//Interface
public interface EmailService {

	boolean sendSimpleMail(EmailDetails details);

	boolean sendMailWithAttachment(EmailDetails details);
}
