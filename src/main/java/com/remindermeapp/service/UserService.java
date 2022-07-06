package com.remindermeapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.remindermeapp.dto.UserProfileForm;
import com.remindermeapp.dto.UserRegistrationForm;
import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.enums.AppRoles;
import com.remindermeapp.repository.BusinessUserRepository;
import com.remindermeapp.repository.UserRepository;
import com.remindermeapp.user.CustomUser;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BusinessUserRepository businessUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean registerUser(UserRegistrationForm userRegistrationForm) {
		boolean res = true;
		
		CustomUser user = new CustomUser();
		user.setEmailId(userRegistrationForm.getEmailId());
		user.setFirstName(userRegistrationForm.getFirstName());
		user.setLastName(userRegistrationForm.getFirstName());
		user.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		user.setPhoneNumber(userRegistrationForm.getPhoneNumber());
//		user.setPassword((userRegistrationForm.getPassword()));
		List<AppRoles> listOfRoles = new LinkedList<>();
		listOfRoles.add(AppRoles.USER);
		long rolesBit = AppRoles.getAppRolesBits(listOfRoles);
		
		user.setRole(rolesBit);
		BusinessUser businessUser = new BusinessUser();
		businessUser.setCustomUser(user);
		user.setBusinessUser(businessUser);
		try {
			userRepo.save(user);
		} catch(Exception e) {
			res = false;
		}
		return res;
	}
	
	public UserProfileForm getUserProfile(String userName) {
		List<CustomUser> customUsers= userRepo.findByEmailId(userName);
		UserProfileForm userProfileForm;
		if(!customUsers.isEmpty()) {
		userProfileForm = new UserProfileForm(customUsers.get(0).getFirstName(), customUsers.get(0).getLastName(), customUsers.get(0).getEmailId(), customUsers.get(0).getPhoneNumber());
		}else {
			userProfileForm = new UserProfileForm();
		}
		
		return userProfileForm;
	}

}
