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
		
		fillWithReminders(businessUser1, "User");
		fillWithReminders(businessUser2, "Admin");
		try {
			userRepository.saveAll(users);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void fillWithReminders(BusinessUser businessUser, String user) {
		List<Reminder> businessUserReminders = new LinkedList<>();
		for(int i = 0; i < 3; i++) {
			getReminders(businessUser, i,businessUserReminders, user);
		}
		
		businessUser.setReminders(businessUserReminders);
	}

	private void getReminders(BusinessUser businessUser2, int index,List<Reminder> businessUserReminders,String user ) {
		Reminder reminder = new Reminder();
		reminder.setTitle("Reminder"+ index +" "+user);
		reminder.setMessage("Message "+ user);	
		int i = 1;
		if(index %2 == 0) i = 2;
		reminder.setSendTime(new Date(new Date().getTime() + 60 * 1000  * i));
		reminder.setCreatedOn(new Date(new Date().getTime() + 60 * 1000 * 1));
		reminder.setRemiderStatus(ReminderStatus.PENDING);
		reminder.setBusinessUser(businessUser2);
		
		businessUserReminders.add(reminder);
	}
    
}