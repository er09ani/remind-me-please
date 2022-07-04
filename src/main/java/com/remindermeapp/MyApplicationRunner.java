package com.remindermeapp;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.entity.Reminder;
import com.remindermeapp.enums.AppRoles;
import com.remindermeapp.enums.ReminderStatus;
import com.remindermeapp.repository.UserRepository;
import com.remindermeapp.user.CustomUser;

@Component
public class MyApplicationRunner implements ApplicationRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<CustomUser> users = new LinkedList<>();
		List<AppRoles> appRoles = new LinkedList<>();
		appRoles.add(AppRoles.USER);
		CustomUser user1 = new CustomUser("amnaik98@gmail.com","Aniruddha","Naik", passwordEncoder.encode("password"), AppRoles.getAppRolesBits(appRoles));                 // USER
		appRoles.clear();
		appRoles.add(AppRoles.ADMIN);
		CustomUser user2 = new CustomUser("er09ani@gmail.com","admin","admin", passwordEncoder.encode("admin"), AppRoles.getAppRolesBits(appRoles));                 // ADMIN
		BusinessUser businessUser1 = new BusinessUser();
		businessUser1.setCustomUser(user1);
		BusinessUser businessUser2 = new BusinessUser();
		businessUser2.setCustomUser(user2);
		user1.setBusinessUser(businessUser1);
		user2.setBusinessUser(businessUser2);
		//user2.setBusinessUser(businessUser2);
		users.add(user1);
		users.add(user2);
		
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 
		
		List<Reminder> reminders = new LinkedList<>();
		Reminder reminder1 = new Reminder();
		reminder1.setMessage("reminder1");
		
		
		Date date1 = new Date();
		System.out.println(date1);
		reminder1.setSendTime(new Date(date1.getTime() + 60 * 1000 * 5));
		reminder1.setCreatedOn(date1);
		reminder1.setRemiderStatus(ReminderStatus.PENDING);

		System.out.println("reminder1 sendTime:" + reminder1.getSendTime());
		reminder1.setBusinessUser(businessUser2);
		//reminder1.setCreatedOn(Date.valueOf(localDate.toString()));
		reminder1.setTitle("REMINDER1:TITLE");
		List<Reminder> remindersOfB2 = new LinkedList<Reminder>();
		remindersOfB2.add(reminder1);
		businessUser2.setReminders(remindersOfB2);
		try {
			
			userRepository.saveAll(users);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
    
}