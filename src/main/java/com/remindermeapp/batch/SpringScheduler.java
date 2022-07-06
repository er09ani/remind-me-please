package com.remindermeapp.batch;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.remindermeapp.entity.Reminder;
import com.remindermeapp.enums.ReminderStatus;
import com.remindermeapp.repository.ReminderRepository;
import com.remindermeapp.utils.TwillioUtils;

@Component
public class SpringScheduler {
	
	@Autowired
	ReminderRepository remindRepository;
	
	@Scheduled(fixedRate = 60000)
	public void scheduleFixedRateTask() {
		OffsetDateTime osdt;
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		offsetDateTime.plusMinutes(1);
	    List<Reminder> reminders = remindRepository.findAllWithSendTimeAfter(new Date(), new Date(new Date().getTime() + 60*1000* 1));
	    
	    for(Reminder r : reminders) {
	    	r.setRemiderStatus(ReminderStatus.COMPLETED);
	    	if(r.getBusinessUser().getCustomUser().getPhoneNumber() != null)
	    	TwillioUtils.sendReminderMessage(r.getBusinessUser().getCustomUser().getPhoneNumber(), r);
	    }
	    remindRepository.saveAll(reminders);
	    
	}
}
